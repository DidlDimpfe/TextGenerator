package de.philw.textgenerator.letters;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.listener.NoMoveWhileGenerateListener;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.manager.GeneratedTextsManager;
import de.philw.textgenerator.manager.MessagesManager;
import de.philw.textgenerator.utils.Direction;
import de.philw.textgenerator.utils.FastBlockUpdate;
import de.philw.textgenerator.utils.GenerateUtil;
import de.philw.textgenerator.utils.TextInstance;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Objects;

public class CurrentEditedText {

    // That variables are for the specific text, so we know the reason
    public static int BLOCK_HAS_NO_SLAB_OR_STAIR = 0;
    public static int TEXT_IS_NOT_VALID_FOR_SPECIFIC_FONT_SIZE = 1;
    public static int VALID = 2;
    public static int WOULD_OVERRIDE_OTHER_BLOCKS = 3;

    private final Player player;
    private TextInstance textInstance;
    private final ArrayList<BukkitTask> dragToMoveTasks;
    private final ArrayList<Location> currentlyPreviewedBlocks;
    private String[][] blocks;
    private FastBlockUpdate blockBuilder;
    private int toUpdateBlocks;
    private int generateSuccess;


    // Utility for Constructors
    private BukkitTask generateTask;

    public void checkFirstGenerateDone() {
        generateTask = Bukkit.getScheduler().runTaskTimer(TextGenerator.getInstance(), () -> {
            if (!blockBuilder.isRunning()) {
                blinkAroundTheEdge();
                playSound();
                NoMoveWhileGenerateListener.remove(player.getUniqueId());
                generateTask.cancel();
            }
        }, 1, 1);
    }

    private boolean notTheSameAsItWas;

    public boolean isNotTheSameAsItWas() {
        return notTheSameAsItWas;
    }

    public CurrentEditedText(Player player, String wantedText) { // For first Generate
        this.player = player;
        this.dragToMoveTasks = new ArrayList<>();
        this.currentlyPreviewedBlocks = new ArrayList<>();
        this.previosPlayerLocation = player.getLocation();
        int placementRange = ConfigManager.getPlacementRange();
        this.textInstance = TextInstance.getTextInstanceBuilder()
                .withBlock(ConfigManager.getBlock())
                .withFontSize(ConfigManager.getFontSize())
                .withFontName(ConfigManager.getFontName())
                .withFontStyle(ConfigManager.getFontStyle())
                .withUnderline(ConfigManager.isUnderline())
                .withLineSpacing(ConfigManager.getLineSpacing())
                .withMiddleLocation(getMiddleLocationFromPlayersSight(player, placementRange))
                .withText(wantedText)
                .withDirection(Direction.valueOf(player.getFacing().toString()).getRightDirection())
                .withPlacementRange(placementRange)
                .withDragToMove(ConfigManager.isDragToMove())
                .build();
        if (textInstance.getFontSize() < 9) {
            if (GenerateUtil.isNotTextValidForSpecificFontSize(wantedText)) {
                generateSuccess = TEXT_IS_NOT_VALID_FOR_SPECIFIC_FONT_SIZE;
                return;
            }
            if (textInstance.getBlock().getSlabAndStairsID() == null) {
                generateSuccess = BLOCK_HAS_NO_SLAB_OR_STAIR;
                return;
            }
        }
        generateSuccess = VALID;
        updateBlockArray();
        updateBlocksInWorld(false);
        if (textInstance.isDragToMove()) {
            addDragToMoveTasks();
        }
        NoMoveWhileGenerateListener.add(player.getUniqueId());
        checkFirstGenerateDone();
    }

    public CurrentEditedText(Player player, TextInstance textInstance) { // For Edit
        GeneratedTextsManager.removeTextInstance(textInstance.getUuid());
        this.player = player;
        this.dragToMoveTasks = new ArrayList<>();
        this.currentlyPreviewedBlocks = new ArrayList<>();
        this.previosPlayerLocation = player.getLocation();
        this.blockBuilder = new FastBlockUpdate(TextGenerator.getInstance(), 100000);
        textInstance.setPlacementRange(ConfigManager.getPlacementRange());
        textInstance.setDragToMove(false);
        this.textInstance = textInstance;
        updateBlockArray();
        this.notTheSameAsItWas = false;
        for (int heightIndex = 0; heightIndex < blocks.length; heightIndex++) {
            for (int widthIndex = 0; widthIndex < blocks[0].length; widthIndex++) {
                try {
                    if (blocks[heightIndex][widthIndex] == null) {
                        Location previewedLocation = GenerateUtil.editLocation(textInstance,
                                textInstance.getTopLeftLocation(), widthIndex, heightIndex, 0, 0, 0, 0);
                        assert previewedLocation != null;
                        if (previewedLocation.getBlock().getType() != Material.AIR) {
                            currentlyPreviewedBlocks.add(previewedLocation);
                            notTheSameAsItWas = true;
                            previewedLocation.getBlock().setMetadata(FastBlockUpdate.metaDataKey, FastBlockUpdate.metaDataValue);
                        }
                        continue;
                    }
                    Location hereIsBlockLocation = GenerateUtil.editLocation(textInstance,
                            textInstance.getTopLeftLocation(), widthIndex, heightIndex, 0, 0, 0, 0);

                    Objects.requireNonNull(hereIsBlockLocation).getBlock().setMetadata(FastBlockUpdate.metaDataKey,
                            FastBlockUpdate.metaDataValue);
                    currentlyPreviewedBlocks.add(hereIsBlockLocation);
                    if (!hereIsBlockLocation.getBlock().getBlockData().getAsString().contains(blocks[heightIndex][widthIndex])) {
                        if (hereIsBlockLocation.getBlock().getBlockData().getMaterial() == Material.AIR) {
                            currentlyPreviewedBlocks.remove(hereIsBlockLocation);
                            hereIsBlockLocation.getBlock().removeMetadata(FastBlockUpdate.metaDataKey, TextGenerator.getInstance());
                        }
                        notTheSameAsItWas = true;
                    }
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        }
        blinkAroundTheEdge();
        playSound();
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

    public void save() {
        textInstance.setBottomRightLocation(GenerateUtil.editLocation(textInstance, textInstance.getTopLeftLocation(),
                blocks[0].length - 1, blocks.length - 1, 0, 0, 0, 0));
        GeneratedTextsManager.saveTextInstance(textInstance);
    }

    public void refreshBlocksByPlayersSight() {
        Location newMiddleLocation = getMiddleLocationFromPlayersSight(player, textInstance.getPlacementRange());
        if (newMiddleLocation == null) {
            return;
        }
        if (GenerateUtil.areLocationsEqual(newMiddleLocation, textInstance.getMiddleLocation())) {
            return;
        }
        Direction playerWantsTextToBeDirection = Direction.valueOf(player.getFacing().toString()).getRightDirection();
        if (textInstance.getDirection() != playerWantsTextToBeDirection) {
            textInstance.setDirection(playerWantsTextToBeDirection);
            // This is for the block data of the stairs. They need to be updated for the direction.
            if (textInstance.getFontSize() < 9) {
                updateBlockArray();
            }
        }
        textInstance.setMiddleLocation(newMiddleLocation);
        updateBlocksInWorld(true);
    }

    public void updateBlocksInWorld(boolean sendMessage) {
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
            if (sendMessage) {
                player.sendMessage(MessagesManager.getMessage("textCannotBeBuildBecauseItWouldOverrideOtherBlocks"));
            } else {
                generateSuccess = WOULD_OVERRIDE_OTHER_BLOCKS;
            }
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
                    if (blocks[heightIndex][widthIndex] == null) {
                        continue;
                    }
                    Location toPlaceBlockLocation = GenerateUtil.editLocation(textInstance,
                            textInstance.getTopLeftLocation(), widthIndex, heightIndex, 0, 0, 0, 0);
                    blockBuilder.addBlock(toPlaceBlockLocation,
                            Bukkit.createBlockData(blocks[heightIndex][widthIndex]));
                    currentlyPreviewedBlocks.add(toPlaceBlockLocation);
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        }
        blockBuilder.run();
        this.blockBuilder = blockBuilder;
    }

    public void removeMetaDataFromPreviewedBlocks() {
        for (Location location : currentlyPreviewedBlocks) {
            location.getBlock().removeMetadata(FastBlockUpdate.metaDataKey, TextGenerator.getInstance());
        }
    }

    private FastBlockUpdate deleteAllPreviousBlocks() {
        currentlyPreviewedBlocks.clear();
        FastBlockUpdate airBuilder = new FastBlockUpdate(TextGenerator.getInstance(), 100000);
        for (int heightIndex = 0; heightIndex < blocks.length; heightIndex++) {
            for (int widthIndex = 0; widthIndex < blocks[0].length; widthIndex++) {
                try {
                    airBuilder.addBlock(GenerateUtil.editLocation(textInstance,
                            textInstance.getTopLeftLocation(), widthIndex, heightIndex, 0, 0, 0, 0), Material.AIR.createBlockData());
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        }
        airBuilder.run();
        return airBuilder;
    }

    public boolean move(int fromViewX, int fromViewY, int fromViewZ) {
        int toRight = Math.max(fromViewX, 0);
        int toLeft = Math.abs(Math.min(fromViewX, 0));
        int toTop = Math.max(fromViewY, 0);
        int toBottom = Math.abs(Math.min(fromViewY, 0));
        int toFront = Math.max(fromViewZ, 0);
        int toBack = Math.abs(Math.min(fromViewZ, 0));
        Location wantedMiddleLocation = GenerateUtil.editLocation(textInstance, textInstance.getMiddleLocation(),
                toRight, toBottom, toLeft, toTop, toFront, toBack);
        if (wantedMiddleLocation == null) { // If it's outside the minecraft range
            return false;
        }
        if (wantedMiddleLocation.getBlock().getBlockData().getMaterial() != Material.AIR && !wantedMiddleLocation.getBlock().hasMetadata(FastBlockUpdate.metaDataKey)) {
            return false;
        }
        textInstance.setMiddleLocation(GenerateUtil.editLocation(textInstance, textInstance.getMiddleLocation(),
                toRight, toBottom, toLeft, toTop, toFront, toBack));
        updateTopLeftLocation();
        updateBlocksInWorld(true);
        return true;
    }

    public void moveToPreviousState() {
        textInstance = textInstance.getPreviousTextInstance();
        updateBlockArray();
        updateTopLeftLocation();
        updateBlocksInWorld(true);
        confirm(true);
    }

    private boolean isEnoughSpaceForText() {
        for (int heightIndex = 0; heightIndex < blocks.length; heightIndex++) {
            for (int widthIndex = 0; widthIndex < blocks[0].length; widthIndex++) {
                try {
                    if (blocks[heightIndex][widthIndex] == null) continue;
                    Block block = Objects.requireNonNull(textInstance.getTopLeftLocation().getWorld()).getBlockAt(
                            Objects.requireNonNull(GenerateUtil.editLocation(textInstance,
                                    textInstance.getTopLeftLocation(), widthIndex,
                                    heightIndex, 0, 0, 0, 0)));
                    if (block.getBlockData().getMaterial() != Material.AIR) {
                        if (!block.hasMetadata(FastBlockUpdate.metaDataKey)) {
                            return false;
                    }
                }
            } catch (IndexOutOfBoundsException ignored) {}
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

    private void blinkAroundTheEdge() {
        if (currentlyPreviewedBlocks.size() <= 1) return;
        ArrayList<Location> toSpawnParticleLocation = new ArrayList<>();
        Location topLeft = null;
        if (textInstance.getDirection() == Direction.WEST) {
            topLeft = textInstance.getTopLeftLocation().clone().add(1.1, 1.1, 0).subtract(0, 0, 0.1);
        } else if (textInstance.getDirection() == Direction.NORTH) {
            topLeft = textInstance.getTopLeftLocation().clone().add(1.1, 1.1, 1.1);
        } else if (textInstance.getDirection() == Direction.SOUTH) {
            topLeft = textInstance.getTopLeftLocation().clone().add(0, 1.1, 0).subtract(0.1, 0, 0.1);
        } else if (textInstance.getDirection() == Direction.EAST) {
            topLeft = textInstance.getTopLeftLocation().clone().add(0, 1.1, 1.1).subtract(0.1, 0, 0);
        }
        toSpawnParticleLocation.add(topLeft);

        double distance = 0.15;

        // Top left to top right
        for (double count = 0; count <= blocks[0].length + 0.2; count += distance) {
            toSpawnParticleLocation.add(GenerateUtil.editLocation(textInstance, topLeft,
                    count, 0, 0, 0, 0, 0));
        }
        // Bottom left to bottom right
        for (double count = 0; count <= blocks[0].length + 0.2; count += distance) {
            toSpawnParticleLocation.add(GenerateUtil.editLocation(textInstance, topLeft,
                    count, blocks.length + 0.2, 0, 0, 0, 0));
        }
        // Top left to bottom left
        for (double count = 0; count <= blocks.length + 0.2; count += distance) {
            toSpawnParticleLocation.add(GenerateUtil.editLocation(textInstance, topLeft,
                    0, count, 0, 0, 0, 0));
        }
        // Top right to bottom right
        for (double count = 0; count <= blocks.length + 0.2; count += distance) {
            toSpawnParticleLocation.add(GenerateUtil.editLocation(textInstance, topLeft,
                    blocks[0].length + 0.2, count, 0, 0, 0, 0));
        }

        for (Location loc : toSpawnParticleLocation) {
            player.spawnParticle(Particle.REDSTONE, loc, 2, 0, 0, 0, 0,
                    new Particle.DustOptions(Color.LIME, 1F));
        }
    }

    private void playSound() {
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 2F);
    }

    private void updateTopLeftLocation() {
        int toLeft = blocks[0].length / 2;
        int toTop = blocks.length / 2;
        textInstance.setTopLeftLocation(GenerateUtil.editLocation(textInstance, textInstance.getMiddleLocation(), 0,
                0, toLeft, toTop, 0, 0));
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
                playSound();
            }
        }, 1, 1);
    }

    private BukkitTask confirmTask;

    public boolean confirm(boolean save) {
        stopDragToMoveTasks();
        confirmTask = Bukkit.getScheduler().runTaskTimer(TextGenerator.getInstance(), () -> {
            if (!blockBuilder.isRunning()) {
                if (currentlyPreviewedBlocks.size() > 1) {
                    removeMetaDataFromPreviewedBlocks();
                    if (save) {
                        save();
                    }
                    blinkAroundTheEdge();
                    playSound();
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

    private BukkitTask resetTask;

    public void reset() {
        FastBlockUpdate airBuilder = deleteAllPreviousBlocks();
        resetTask = Bukkit.getScheduler().runTaskTimer(TextGenerator.getInstance(), () -> {
            if (!airBuilder.isRunning()) {
                updateBlocksInWorld(true);
                resetTask.cancel();
            }
        }, 1, 1);
    }

    public int setFontSize(int size) {
        if (size < 9) {
            if (textInstance.getBlock().getSlabAndStairsID() == null) return BLOCK_HAS_NO_SLAB_OR_STAIR;
            if (GenerateUtil.isNotTextValidForSpecificFontSize(textInstance.getText())) return TEXT_IS_NOT_VALID_FOR_SPECIFIC_FONT_SIZE;
        }
        textInstance.setFontSize(size);
        updateBlockArray();
        updateBlocksInWorld(true);
        return VALID;
    }

    public void setLineSpacing(int lineSpacing) {
        textInstance.setLineSpacing(lineSpacing);
        updateBlockArray();
        updateBlocksInWorld(true);
    }

    public void setPlacementRange(int placementRange) {
        textInstance.setPlacementRange(placementRange);
        refreshBlocksByPlayersSight();
    }

    public boolean setFontStyle(int fontStyle) {
        if (textInstance.getFontSize() < 9) return false;
        textInstance.setFontStyle(fontStyle);
        updateBlockArray();
        updateBlocksInWorld(true);
        return true;
    }

    public boolean setBlock(de.philw.textgenerator.ui.value.Block block) {
        if (textInstance.getFontSize() < 9 && block.getSlabAndStairsID() == null) return false;
        this.textInstance.setBlock(block);
        updateBlockArray();
        updateBlocksInWorld(true);
        return true;
    }

    public boolean setText(String newText) {
        if (textInstance.getFontSize() < 9) {
            if (GenerateUtil.isNotTextValidForSpecificFontSize(newText)) return false;
        }
        this.textInstance.setText(newText);
        updateBlockArray();
        updateBlocksInWorld(true);
        return true;
    }

    public void setDragToMoveTasks(boolean dragToMoveTasks) {
        this.textInstance.setDragToMove(dragToMoveTasks);
        this.textInstance.setDirection(Direction.valueOf(player.getFacing().toString()).getRightDirection());
        if (dragToMoveTasks) addDragToMoveTasks();
        else stopDragToMoveTasks();
    }

    public void setUnderline(boolean underline) {
        this.textInstance.setUnderline(underline);
        updateBlockArray();
        updateBlocksInWorld(true);
    }

    public boolean isFirstGenerate() {
        return textInstance.getPreviousTextInstance() == null;
    }

    public TextInstance getTextInstance() {
        return textInstance;
    }

    public int getGenerateSuccess() {
        return generateSuccess;
    }
}
