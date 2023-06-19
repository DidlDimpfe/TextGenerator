package de.philw.textgenerator.letters;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.utils.Direction;
import de.philw.textgenerator.utils.FastBlockUpdate;
import de.philw.textgenerator.utils.GenerateUtil;
import de.philw.textgenerator.utils.TextInstance;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class CurrentEditedText {

    private final Player player;
    private final TextInstance textInstance;
    private boolean[][] blocks;
    private final ArrayList<BukkitTask> tasks;
    private final ArrayList<Location> currentlyPreviewedBlocks;
    private final ArrayList<FastBlockUpdate> currentUpdates; // TO WORK ON: Vielleicht nur buildUpdates
    private int toUpdateBlocks;
    private boolean firstGenerate;

    public CurrentEditedText(Player player, String wantedText, boolean firstGenerate) {
        this.player = player;
        this.tasks = new ArrayList<>();
        this.currentlyPreviewedBlocks = new ArrayList<>();
        this.currentUpdates = new ArrayList<>();
        this.previosPlayerLocation = player.getLocation();
        this.firstGenerate = firstGenerate;
        textInstance = TextInstance.getTextInstanceBuilder()
                .withBlock(ConfigManager.getBlock())
                .withFontSize(ConfigManager.getFontSize())
                .withFontName(ConfigManager.getFontName())
                .withFontStyle(ConfigManager.getFontStyle())
                .withUnderline(ConfigManager.isUnderline())
                .withLineSpacing(ConfigManager.getLineSpacing())
                .withMiddleLocation(getMiddleLocationFromPlayersSight(player, 40))
                .withText(wantedText)
                .withDirection(Direction.valueOf(player.getFacing().toString()).getRightDirection())
                .build();
        this.updateBlocks();
        updatePreviewBlocks();
        addTasks();
    }

    private void updatePreviewBlocks() {
        updateTopLeftLocation();
        try {
            FastBlockUpdate previousBlockBuilder = currentUpdates.get(1);
            if (previousBlockBuilder.isRunning() && toUpdateBlocks > 10000) {
                previousBlockBuilder.cancel();
            }
        } catch (IndexOutOfBoundsException ignored) {}
        currentUpdates.clear();


        FastBlockUpdate airBuilder = new FastBlockUpdate(TextGenerator.getInstance(), 100000);
        for (Location location: currentlyPreviewedBlocks) {
            airBuilder.addBlock(location, Material.AIR.createBlockData());
        }
        currentlyPreviewedBlocks.clear();
        airBuilder.run();


        FastBlockUpdate blockBuilder = new FastBlockUpdate(TextGenerator.getInstance(),100000);
        for (int heightIndex = 0; heightIndex < blocks.length; heightIndex++) {
            for (int widthIndex = 0; widthIndex < blocks[0].length; widthIndex++) {
                try {
                    if (!blocks[heightIndex][widthIndex]) {
                        continue;
                    }
                    Location toPlaceBlockLocation = editLocation(textInstance.getTopLeftLocation(), widthIndex, heightIndex, 0, 0);
                    blockBuilder.addBlock(toPlaceBlockLocation, Bukkit.createBlockData(textInstance.getBlock().toString().toLowerCase()));
                    currentlyPreviewedBlocks.add(toPlaceBlockLocation);
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        }
        blockBuilder.run();
        currentUpdates.add(airBuilder);
        currentUpdates.add(blockBuilder);
    }

    private Location previosPlayerLocation;

    private void addTasks() {
        tasks.add(Bukkit.getScheduler().runTaskTimer(TextGenerator.getInstance(), () -> {
            if (toUpdateBlocks > 10000) {
                if (!player.getLocation().equals(previosPlayerLocation)) {
                    previosPlayerLocation = player.getLocation();
                    return;
                }
            } else if (toUpdateBlocks > 7000) {
                if (!player.getLocation().getDirection().equals(previosPlayerLocation.getDirection())) {
                    previosPlayerLocation = player.getLocation();
                    return;
                }
                if (player.getLocation().distance(previosPlayerLocation) > 1) {
                    previosPlayerLocation = player.getLocation();
                    return;
                }
            }
            Location newMiddleLocation = getMiddleLocationFromPlayersSight(player, 40);
            if (GenerateUtil.areLocationsEqual(newMiddleLocation, textInstance.getMiddleLocation())) {
                return;
            }
            textInstance.setMiddleLocation(newMiddleLocation);
            updatePreviewBlocks();
        }, 1, 1));
        tasks.add(Bukkit.getScheduler().runTaskTimer(TextGenerator.getInstance(), () -> {
            if (textInstance.getDirection() != Direction.valueOf(player.getFacing().toString()).getRightDirection()) {
                textInstance.setDirection(Direction.valueOf(player.getFacing().toString()).getRightDirection());
            }
        }, 1, 1));
    }

    public void stopTasks() {
        for (BukkitTask bukkitTask: tasks) {
            bukkitTask.cancel();
        }
    }

    public HashMap<Location, BlockData> getAffectedBlocks() {
        HashMap<Location, BlockData> affectedBlocks = new HashMap<>();
        for (int heightIndex = 0; heightIndex < blocks.length; heightIndex++) {
            for (int widthIndex = 0; widthIndex < blocks[0].length; widthIndex++) {
                Block block = Objects.requireNonNull(textInstance.getTopLeftLocation().getWorld()).getBlockAt(
                        Objects.requireNonNull(editLocation(textInstance.getTopLeftLocation(), widthIndex,
                                heightIndex, 0, 0)));
                affectedBlocks.put(block.getLocation(), block.getBlockData());
            }
        }
        return affectedBlocks;
    }

    public boolean isEnoughSpaceForText(boolean[][] blocks, TextInstance textInstance) {
        for (int heightIndex = 0; heightIndex < blocks.length; heightIndex++) {
            for (int widthIndex = 0; widthIndex < blocks[0].length; widthIndex++) {
                Block block = Objects.requireNonNull(textInstance.getTopLeftLocation().getWorld()).getBlockAt(
                        Objects.requireNonNull(editLocation(textInstance.getTopLeftLocation(), widthIndex,
                                heightIndex, 0, 0)));
                if (block.getBlockData().getMaterial() != Material.AIR) return false;
            }
        }
        return true;
    }

    private Location editLocation(Location location, int toRight, int toBottom, int toLeft, int toTop) {
        if (textInstance.getDirection() == Direction.NORTH) return location.clone().subtract(0, toBottom, toRight).add(0, toTop, toLeft);
        if (textInstance.getDirection() == Direction.EAST) return location.clone().subtract(toLeft, toBottom, 0).add(toRight, toTop, 0);
        if (textInstance.getDirection() == Direction.SOUTH) return location.clone().subtract(0, toBottom, toLeft).add(0, toTop, toRight);
        if (textInstance.getDirection() == Direction.WEST) return location.clone().subtract(toRight, toBottom, 0).add(toLeft, toTop, 0);
        return null;
    }

    public Location getMiddleLocationFromPlayersSight(Player player, int range) {
        int playerLocationX = (int) (player.getLocation().add(0, player.getEyeHeight(), 0).getX());
        int playerLocationY = (int) (player.getLocation().add(0, player.getEyeHeight(), 0).getY());
        int playerLocationZ = (int) (player.getLocation().add(0, player.getEyeHeight(), 0).getZ()) -1;

        Vector normalVector = player.getLocation().getDirection().normalize();
        Location actualLocation = null;

        int x = 0;
        int y = 0;
        int z = 0;
        for (int i = 1; i <= range; i++) {
            int nextX = playerLocationX + (int) normalVector.clone().multiply(i).getX();
            int nextY = playerLocationY + (int) normalVector.clone().multiply(i).getY();
            int nextZ = playerLocationZ + (int) normalVector.clone().multiply(i).getZ();

            Location possibleLocation = new Location(player.getWorld(), nextX, nextY, nextZ);
//            if (possibleLocation.getBlock().getBlockData().getMaterial() != Material.AIR) { TO WORK ON
//                if (!currentlyPreviewedBlocks.contains(possibleLocation)) {
//                    actualLocation = new Location(player.getWorld(), x, y, z);
//                    break;
//                }
//            }
            if (i == range) {
                actualLocation = new Location(player.getWorld(), x, y, z);
            }
            x = nextX;
            y = nextY;
            z = nextZ;
        }
        return actualLocation;
    }

    private void updateTopLeftLocation() {
        textInstance.setTopLeftLocation(editLocation(textInstance.getMiddleLocation(), 0, 0, blocks[0].length/2, blocks.length/2));
    }

    public Player getPlayer() {
        return player;
    }

    public TextInstance getTextInstance() {
        return textInstance;
    }

    public void setBlock(de.philw.textgenerator.ui.value.Block block) {
        this.textInstance.setBlock(block);
        updatePreviewBlocks();
    }

    private void updateBlocks() {
        this.blocks = GenerateUtil.getBlocks(textInstance);
        toUpdateBlocks = 0;
        for (int heightIndex = 0; heightIndex < blocks.length; heightIndex++) {
            for (int widthIndex = 0; widthIndex < blocks[0].length; widthIndex++) {
                toUpdateBlocks++;
            }
        }
    }

    public void setFontSize(int size) {
        textInstance.setFontSize(size);
        updateBlocks();
        updatePreviewBlocks();
    }

    public void setLineSpacing(int lineSpacing) {
        textInstance.setLineSpacing(lineSpacing);
        updateBlocks();
        updatePreviewBlocks();
    }

    public void save() {
        // TO WORK ON
    }

    private BukkitTask destroyTask;

    public void destroy() {
        stopTasks();
        destroyTask = Bukkit.getScheduler().runTaskTimer(TextGenerator.getInstance(), () -> {
                FastBlockUpdate previousBlockBuilder = currentUpdates.get(1);
                if (!previousBlockBuilder.isRunning()) {
                    FastBlockUpdate fastBlockUpdate = new FastBlockUpdate(TextGenerator.getInstance(), 100000);
                    for (Location location: currentlyPreviewedBlocks) {
                        fastBlockUpdate.addBlock(location, Material.AIR.createBlockData());
                    }
                    fastBlockUpdate.run();
                    destroyTask.cancel();
                }
        }, 1, 1);
    }

    public boolean isFirstGenerate() {
        return firstGenerate;
    }
}
