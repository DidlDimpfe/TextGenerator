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
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Objects;

public class CurrentEditedText {

    private final Player player;
    private final TextInstance textInstance;
    private final ArrayList<BukkitTask> tasks;
    private final ArrayList<Location> currentlyPreviewedBlocks;
    private final boolean firstGenerate;
    private boolean[][] blocks;
    private FastBlockUpdate blockBuilder;
    private int toUpdateBlocks;

    public CurrentEditedText(Player player, String wantedText, boolean firstGenerate) {
        this.player = player;
        this.tasks = new ArrayList<>();
        this.currentlyPreviewedBlocks = new ArrayList<>();
        this.previosPlayerLocation = player.getLocation();
        this.firstGenerate = firstGenerate;
        int placeRange = ConfigManager.getPlaceRange();
        textInstance = TextInstance.getTextInstanceBuilder()
                .withBlock(ConfigManager.getBlock())
                .withFontSize(ConfigManager.getFontSize())
                .withFontName(ConfigManager.getFontName())
                .withFontStyle(ConfigManager.getFontStyle())
                .withUnderline(ConfigManager.isUnderline())
                .withLineSpacing(ConfigManager.getLineSpacing())
                .withMiddleLocation(getMiddleLocationFromPlayersSight(player, placeRange))
                .withText(wantedText)
                .withDirection(Direction.valueOf(player.getFacing().toString()).getRightDirection())
                .withPlaceRange(placeRange)
                .build();
        updateBlockArray();
        updateBlocksInWorld();
        addTasks();
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
            Location newMiddleLocation = getMiddleLocationFromPlayersSight(player, textInstance.getPlaceRange());
            if (GenerateUtil.areLocationsEqual(newMiddleLocation, textInstance.getMiddleLocation())) {
                return;
            }
            textInstance.setMiddleLocation(newMiddleLocation);
            updateBlocksInWorld();
        }, 1, 1));
        tasks.add(Bukkit.getScheduler().runTaskTimer(TextGenerator.getInstance(), () -> {
            if (textInstance.getDirection() != Direction.valueOf(player.getFacing().toString()).getRightDirection()) {
                textInstance.setDirection(Direction.valueOf(player.getFacing().toString()).getRightDirection());
            }
        }, 1, 1));
    }

    private void stopTasks() {
        for (BukkitTask bukkitTask: tasks) {
            bukkitTask.cancel();
        }
    }

    private void save() {
        // TO WORK ON
    }

    private void updateBlocksInWorld() {
        updateTopLeftLocation();
        if (!isEnoughSpaceForText()) {
            FastBlockUpdate errorBuilder = new FastBlockUpdate(TextGenerator.getInstance(), 100000);
            errorBuilder.addBlock(textInstance.getMiddleLocation(), Material.AIR.createBlockData());
            for (Location location: currentlyPreviewedBlocks) {
                errorBuilder.addBlock(location, Material.AIR.createBlockData());
            }
            errorBuilder.addBlock(textInstance.getMiddleLocation(), Material.REDSTONE_BLOCK.createBlockData());
            errorBuilder.run();
            currentlyPreviewedBlocks.clear();
            currentlyPreviewedBlocks.add(textInstance.getMiddleLocation());
            return;
        }
        if (blockBuilder != null) {
            if (blockBuilder.isRunning() && toUpdateBlocks > 10000) {
                blockBuilder.cancel();
            }
        }

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
                    Location toPlaceBlockLocation = GenerateUtil.editLocation(textInstance, textInstance.getTopLeftLocation(), widthIndex, heightIndex, 0, 0);
                    blockBuilder.addBlock(toPlaceBlockLocation, Bukkit.createBlockData(textInstance.getBlock().toString().toLowerCase()));
                    currentlyPreviewedBlocks.add(toPlaceBlockLocation);
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        }
        blockBuilder.run();
        this.blockBuilder = blockBuilder;
    }

    private boolean isEnoughSpaceForText() {
        for (int heightIndex = 0; heightIndex < blocks.length; heightIndex++) {
            for (int widthIndex = 0; widthIndex < blocks[0].length; widthIndex++) {
                Block block = Objects.requireNonNull(textInstance.getTopLeftLocation().getWorld()).getBlockAt(
                        Objects.requireNonNull(GenerateUtil.editLocation(textInstance, textInstance.getTopLeftLocation(), widthIndex,
                                heightIndex, 0, 0)));
                if (block.getBlockData().getMaterial() != Material.AIR) {
                    if (!block.hasMetadata(FastBlockUpdate.metaDataKey)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private Location getMiddleLocationFromPlayersSight(Player player, int range) {
        int playerLocationX = (int) (player.getLocation().add(0, player.getEyeHeight(), 0).getX());
        int playerLocationY = (int) (player.getLocation().add(0, player.getEyeHeight(), 0).getY());
        int playerLocationZ = (int) (player.getLocation().add(0, player.getEyeHeight(), 0).getZ());

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
            if (possibleLocation.getBlock().getBlockData().getMaterial() != Material.AIR) {
                if (!possibleLocation.getBlock().hasMetadata(FastBlockUpdate.metaDataKey)) {
                    actualLocation = new Location(player.getWorld(), x, y, z);
                    break;
                }
            }
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
        textInstance.setTopLeftLocation(GenerateUtil.editLocation(textInstance, textInstance.getMiddleLocation(), 0, 0, blocks[0].length/2, blocks.length/2));
    }

    private void updateBlockArray() {
        this.blocks = GenerateUtil.getBlocks(textInstance);
        toUpdateBlocks = 0;
        for (int heightIndex = 0; heightIndex < blocks.length; heightIndex++) {
            for (int widthIndex = 0; widthIndex < blocks[0].length; widthIndex++) {
                toUpdateBlocks++;
            }
        }
    }

    private BukkitTask destroyTask;

    public void destroy() {
        stopTasks();
        destroyTask = Bukkit.getScheduler().runTaskTimer(TextGenerator.getInstance(), () -> {
            if (!blockBuilder.isRunning()) {
                FastBlockUpdate fastBlockUpdate = new FastBlockUpdate(TextGenerator.getInstance(), 100000);
                for (Location location: currentlyPreviewedBlocks) {
                    fastBlockUpdate.addBlock(location, Material.AIR.createBlockData());
                }
                fastBlockUpdate.run();
                destroyTask.cancel();
            }
        }, 1, 1);
    }

    private BukkitTask confirmTask;

    public void confirm() {
        stopTasks();
        confirmTask = Bukkit.getScheduler().runTaskTimer(TextGenerator.getInstance(), () -> {
            if (!blockBuilder.isRunning()) {
                if (currentlyPreviewedBlocks.size() > 1) {
                    for (Location location : currentlyPreviewedBlocks) {
                        location.getBlock().removeMetadata(FastBlockUpdate.metaDataKey, TextGenerator.getInstance());
                    }
                } else {
                    currentlyPreviewedBlocks.get(0).getBlock().setType(Material.AIR);
                    currentlyPreviewedBlocks.get(0).getBlock().removeMetadata(FastBlockUpdate.metaDataKey, TextGenerator.getInstance());
                }
                confirmTask.cancel();
            }
        }, 1, 1);
        save();
    }

    public void setFontSize(int size) {
        textInstance.setFontSize(size);
        updateBlockArray();
        updateBlocksInWorld();
    }

    public void setLineSpacing(int lineSpacing) {
        textInstance.setLineSpacing(lineSpacing);
        updateBlockArray();
        updateBlocksInWorld();
    }

    public void setBlock(de.philw.textgenerator.ui.value.Block block) {
        this.textInstance.setBlock(block);
        updateBlocksInWorld();
    }

    public boolean isFirstGenerate() {
        return firstGenerate;
    }

    public TextInstance getTextInstance() {
        return textInstance;
    }

}
