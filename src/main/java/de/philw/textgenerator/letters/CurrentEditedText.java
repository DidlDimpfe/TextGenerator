package de.philw.textgenerator.letters;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.manager.GeneratedTextsManager;
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
    private TextInstance textInstance;
    private final ArrayList<BukkitTask> dragToMoveTasks;
    private final ArrayList<Location> currentlyPreviewedBlocks;
    private boolean[][] blocks;
    private FastBlockUpdate blockBuilder;
    private int toUpdateBlocks;

    public CurrentEditedText(Player player, String wantedText) { // For first Generate
        this.player = player;
        this.dragToMoveTasks = new ArrayList<>();
        this.currentlyPreviewedBlocks = new ArrayList<>();
        this.previosPlayerLocation = player.getLocation();
        int placeRange = ConfigManager.getPlaceRange();
        this.textInstance = TextInstance.getTextInstanceBuilder()
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
                .withDragToMove(ConfigManager.isDragToMove(true))
                .build();
        updateBlockArray();
        updateBlocksInWorld();
        if (textInstance.isDragToMove()) {
            addDragToMoveTasks();
        }
    }

    public CurrentEditedText(Player player, TextInstance textInstance) { // For Edit
        GeneratedTextsManager.removeTextInstance(textInstance.getUuid());
        this.player = player;
        this.dragToMoveTasks = new ArrayList<>();
        this.currentlyPreviewedBlocks = new ArrayList<>();
        this.previosPlayerLocation = player.getLocation();
        this.blockBuilder = new FastBlockUpdate(TextGenerator.getInstance(), 100000);
        textInstance.setPlaceRange(ConfigManager.getPlaceRange());
        textInstance.setDragToMove(false);
        this.textInstance = textInstance;
        updateBlockArray();
        for (int heightIndex = 0; heightIndex < blocks.length; heightIndex++) {
            for (int widthIndex = 0; widthIndex < blocks[0].length; widthIndex++) {
                try {
                    if (!blocks[heightIndex][widthIndex]) {
                        continue;
                    }
                    Location hereIsBlockLocation = GenerateUtil.editLocation(textInstance,
                            textInstance.getTopLeftLocation(), widthIndex, heightIndex, 0, 0, 0, 0);
                    Objects.requireNonNull(hereIsBlockLocation).getBlock().setMetadata(FastBlockUpdate.metaDataKey,
                            FastBlockUpdate.metaDataValue);
                    currentlyPreviewedBlocks.add(hereIsBlockLocation);
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        }
    }

    private Location previosPlayerLocation;

    private void addDragToMoveTasks() {
        dragToMoveTasks.add(Bukkit.getScheduler().runTaskTimer(TextGenerator.getInstance(), () -> {
            // For big texts (protection for lags)
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
            refreshBlocksByPlayersSight();
        }, 1, 1));
    }

    private void stopDragToMoveTasks() {
        for (BukkitTask bukkitTask : dragToMoveTasks) {
            bukkitTask.cancel();
        }
    }

    private void save() {
        textInstance.setBottomRightLocation(GenerateUtil.editLocation(textInstance, textInstance.getTopLeftLocation(),
                blocks[0].length - 1, blocks.length - 1, 0, 0, 0, 0));
        GeneratedTextsManager.saveTextInstance(textInstance);
    }

    public void refreshBlocksByPlayersSight() {
        Location newMiddleLocation = getMiddleLocationFromPlayersSight(player, textInstance.getPlaceRange());
        if (newMiddleLocation == null) {
            return;
        }
        if (GenerateUtil.areLocationsEqual(newMiddleLocation, textInstance.getMiddleLocation())) {
            return;
        }
        Direction playerWantsTextToBeDirection = Direction.valueOf(player.getFacing().toString()).getRightDirection();
        if (textInstance.getDirection() != playerWantsTextToBeDirection) {
            textInstance.setDirection(playerWantsTextToBeDirection);
        }
        textInstance.setMiddleLocation(newMiddleLocation);
        updateBlocksInWorld();
    }

    public void updateBlocksInWorld() {
        updateTopLeftLocation();
        if (blockBuilder != null) {
            if (blockBuilder.isRunning() && toUpdateBlocks > 10000) {
                blockBuilder.cancel();
            }
        }
        if (!isEnoughSpaceForText()) {
            FastBlockUpdate airBuilder = new FastBlockUpdate(TextGenerator.getInstance(), 100000);
            airBuilder.addBlock(textInstance.getMiddleLocation(), Material.AIR.createBlockData());
            for (Location location : currentlyPreviewedBlocks) {
                if (!location.equals(textInstance.getMiddleLocation())) {
                    airBuilder.addBlock(location, Material.AIR.createBlockData());
                }
            }
            airBuilder.run();
            FastBlockUpdate blockBuilder = new FastBlockUpdate(TextGenerator.getInstance(), 100000);
            blockBuilder.addBlock(textInstance.getMiddleLocation(), Material.REDSTONE_BLOCK.createBlockData());
            blockBuilder.run();
            this.blockBuilder = blockBuilder;

            currentlyPreviewedBlocks.clear();
            currentlyPreviewedBlocks.add(textInstance.getMiddleLocation());
            return;
        }

        FastBlockUpdate airBuilder = new FastBlockUpdate(TextGenerator.getInstance(), 100000);
        for (Location location : currentlyPreviewedBlocks) {
            airBuilder.addBlock(location, Material.AIR.createBlockData());
        }
        currentlyPreviewedBlocks.clear();
        airBuilder.run();


        FastBlockUpdate blockBuilder = new FastBlockUpdate(TextGenerator.getInstance(), 100000);
        for (int heightIndex = 0; heightIndex < blocks.length; heightIndex++) {
            for (int widthIndex = 0; widthIndex < blocks[0].length; widthIndex++) {
                try {
                    if (!blocks[heightIndex][widthIndex]) {
                        continue;
                    }
                    Location toPlaceBlockLocation = GenerateUtil.editLocation(textInstance,
                            textInstance.getTopLeftLocation(), widthIndex, heightIndex, 0, 0, 0, 0);
                    blockBuilder.addBlock(toPlaceBlockLocation,
                            Bukkit.createBlockData(textInstance.getBlock().toString().toLowerCase()));
                    currentlyPreviewedBlocks.add(toPlaceBlockLocation);
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        }
        blockBuilder.run();
        this.blockBuilder = blockBuilder;
    }

    public void move(int fromViewX, int fromViewY, int fromViewZ) {
        int toRight = Math.max(fromViewX, 0);
        int toLeft = Math.abs(Math.min(fromViewX, 0));
        int toTop = Math.max(fromViewY, 0);
        int toBottom = Math.abs(Math.min(fromViewY, 0));
        int toFront = Math.max(fromViewZ, 0);
        int toBack = Math.abs(Math.min(fromViewZ, 0));
        textInstance.setMiddleLocation(GenerateUtil.editLocation(textInstance, textInstance.getMiddleLocation(),
                toRight, toBottom, toLeft, toTop, toFront, toBack));
        updateTopLeftLocation();
        updateBlocksInWorld();
    }

    public void setToPreviousLocation() {
        textInstance = textInstance.getPreviousTextInstance();
        updateBlockArray();
        updateTopLeftLocation();
        updateBlocksInWorld();
        confirm();
    }

    private boolean isEnoughSpaceForText() {
        for (int heightIndex = 0; heightIndex < blocks.length; heightIndex++) {
            for (int widthIndex = 0; widthIndex < blocks[0].length; widthIndex++) {
                Block block = Objects.requireNonNull(textInstance.getTopLeftLocation().getWorld()).getBlockAt(
                        Objects.requireNonNull(GenerateUtil.editLocation(textInstance,
                                textInstance.getTopLeftLocation(), widthIndex,
                                heightIndex, 0, 0, 0, 0)));
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

        int x = 0;
        int y = 0;
        int z = 0;
        for (int i = 1; i <= range; i++) {
            int nextX = playerLocationX + (int) normalVector.clone().multiply(i).getX();
            int nextY = playerLocationY + (int) normalVector.clone().multiply(i).getY();
            int nextZ = playerLocationZ + (int) normalVector.clone().multiply(i).getZ();

            if (nextY > 319 || nextY < -64) {
                return null;
            }
            if (i == range) {
                return new Location(player.getWorld(), x, y, z);
            }
            Location possibleLocation = new Location(player.getWorld(), nextX, nextY, nextZ);
            if (possibleLocation.getBlock().getBlockData().getMaterial() != Material.AIR) {
                if (!possibleLocation.getBlock().hasMetadata(FastBlockUpdate.metaDataKey)) {
                    return new Location(player.getWorld(), x, y, z);
                }
            }

            x = nextX;
            y = nextY;
            z = nextZ;
        }
        return null;
    }

    private void updateTopLeftLocation() {
        textInstance.setTopLeftLocation(GenerateUtil.editLocation(textInstance, textInstance.getMiddleLocation(), 0,
                0, blocks[0].length / 2, blocks.length / 2, 0, 0));
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
        stopDragToMoveTasks();
        destroyTask = Bukkit.getScheduler().runTaskTimer(TextGenerator.getInstance(), () -> {
            if (!blockBuilder.isRunning()) {
                FastBlockUpdate fastBlockUpdate = new FastBlockUpdate(TextGenerator.getInstance(), 100000);
                for (Location location : currentlyPreviewedBlocks) {
                    fastBlockUpdate.addBlock(location, Material.AIR.createBlockData());
                }
                fastBlockUpdate.run();
                destroyTask.cancel();
            }
        }, 1, 1);
    }

    private BukkitTask confirmTask;

    public boolean confirm() {
        stopDragToMoveTasks();
        confirmTask = Bukkit.getScheduler().runTaskTimer(TextGenerator.getInstance(), () -> {
            if (!blockBuilder.isRunning()) {
                if (currentlyPreviewedBlocks.size() > 1) {
                    for (Location location : currentlyPreviewedBlocks) {
                        location.getBlock().removeMetadata(FastBlockUpdate.metaDataKey, TextGenerator.getInstance());
                    }
                    save();
                } else {
                    currentlyPreviewedBlocks.get(0).getBlock().setType(Material.AIR);
                    currentlyPreviewedBlocks.get(0).getBlock().removeMetadata(FastBlockUpdate.metaDataKey,
                            TextGenerator.getInstance());
                }
                confirmTask.cancel();
            }
        }, 1, 1);
        for (Location location : currentlyPreviewedBlocks) {
            if (location.getBlock().getType() == Material.REDSTONE_BLOCK) return false;
        }
        return true;
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

    public void setDragToMoveTasks(boolean dragToMoveTasks) {
        this.textInstance.setDragToMove(dragToMoveTasks);
        this.textInstance.setDirection(Direction.valueOf(player.getFacing().toString()).getRightDirection());
        if (dragToMoveTasks) addDragToMoveTasks();
        else stopDragToMoveTasks();
    }

    public boolean isFirstGenerate() {
        return textInstance.getPreviousTextInstance() == null;
    }

    public TextInstance getTextInstance() {
        return textInstance;
    }

}
