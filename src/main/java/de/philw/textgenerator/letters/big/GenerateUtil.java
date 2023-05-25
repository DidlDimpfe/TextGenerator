package de.philw.textgenerator.letters.big;

import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.utils.Direction;
import de.philw.textgenerator.utils.TextInstance;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

public class GenerateUtil {

    private static TextInstance textInstance;
    private static boolean[][] blocks;

    public static void setTextInstance(TextInstance textInstance) {
        GenerateUtil.textInstance = textInstance;
    }

    public static void setBlocks(boolean[][] blocks) {
        GenerateUtil.blocks = blocks;
    }

    public static void buildBlocks() {
        for (int heightIndex = 0; heightIndex < blocks.length; heightIndex++) {
            for (int widthIndex = 0; widthIndex < blocks[0].length; widthIndex++) {
                try {
                    if (blocks[heightIndex][widthIndex]) {
                        Objects.requireNonNull(
                                        textInstance.getStartLocation().getWorld()).getBlockAt(Objects.requireNonNull(editStartLocation(textInstance.getStartLocation(), widthIndex, heightIndex, textInstance.getDirection()))).
                                setBlockData(Bukkit.createBlockData(textInstance.getBlock().getNormalBlockData()));
                    }
                } catch (IndexOutOfBoundsException ignored) {}
            }
        }
    }

    public static HashMap<Location, BlockData> getAffectedBlocks() {
        HashMap<Location, BlockData> affectedBlocks = new HashMap<>();
        for (int heightIndex = 0; heightIndex < blocks.length; heightIndex++) {
            for (int widthIndex = 0; widthIndex < blocks[0].length; widthIndex++) {
                Block block = Objects.requireNonNull(textInstance.getStartLocation().getWorld()).getBlockAt(
                        Objects.requireNonNull(editStartLocation(textInstance.getStartLocation(), widthIndex, heightIndex, textInstance.getDirection())));
                        affectedBlocks.put(block.getLocation(), block.getBlockData());
            }
        }
        return affectedBlocks;
    }

    public static boolean[][] getBlocks(ArrayList<BufferedImage> linesAsBufferedImages) {
        ArrayList<boolean[][]> lines = new ArrayList<>();
        // find out the biggest width
        int width = 0;
        for (BufferedImage bufferedImage: linesAsBufferedImages) {
            if (bufferedImage.getWidth() > width) width = bufferedImage.getWidth();
        }
        boolean[][] biggest = null;
        // loop through all linesAsBufferedImages, create the 2D-Array for each column and reduce the arrays
        for (BufferedImage bufferedImage: linesAsBufferedImages) {
            boolean[][] tempLine = new boolean[bufferedImage.getHeight()][width];
            for (int heightIndex = 0; heightIndex < bufferedImage.getHeight(); heightIndex++) {
                for (int widthIndex = 0; widthIndex < bufferedImage.getWidth(); widthIndex++) {
                    int rgb = bufferedImage.getRGB(widthIndex, heightIndex);
                    tempLine[heightIndex][widthIndex] = rgb != -1;
                }
            }
            int alreadyRemoved = 0;
            for (int columnIndex: getToRemoveColumns(tempLine)) {
                tempLine = removeColumn(tempLine, columnIndex - alreadyRemoved);
                alreadyRemoved++;
            }
            alreadyRemoved = 0;
            for (int rowIndex: getToRemoveRows(tempLine)) {
                removeRow(tempLine, rowIndex - alreadyRemoved);
                alreadyRemoved++;
            }
            if (bufferedImage.getWidth() == width) {
                biggest = tempLine;
            }
            lines.add(tempLine);
        }
        // make the arrays the same size as the biggest array
        for (int arrayIndex = 0; arrayIndex < lines.size(); arrayIndex++) {
            boolean[][] oldLine = lines.get(arrayIndex);
            if (oldLine == biggest) continue;
            boolean[][] doneLine = new boolean[oldLine.length][width];
            for (int column = 0; column < oldLine.length; column++) {
                System.arraycopy(oldLine[column], 0, doneLine[column], 0, oldLine[0].length);
            }
            lines.set(arrayIndex, doneLine);
        }

        // make the final 2D-Boolean array for all the lines with considering the in the config give space between each line.
        int height = 0;
        int spaceBetweenEachLine = ConfigManager.getSpaceBetweenEachLine();
        for (boolean[][] doneLine: lines) {
            height += doneLine.length + spaceBetweenEachLine;
        }
        boolean[][] blocks = new boolean[height][width];
        int currentLineIndexCount = 0;
        for (boolean[][] doneLine: lines) {
            for (boolean[] booleans : doneLine) {
                blocks[currentLineIndexCount] = booleans;
                currentLineIndexCount++;
            }
            currentLineIndexCount += spaceBetweenEachLine;
        }
        return blocks;
    }

    private static ArrayList<Integer> getToRemoveColumns(boolean[][] blocks) {
        ArrayList<Integer> toRemoveColumns = new ArrayList<>();

        // from top to bottom
        boolean start = true;
        for (int column = 0; column < blocks.length; column++) {
            boolean reduce = true;
            for (int row = 0; row < blocks[0].length; row++) {
                if (blocks[column][row]) {
                    reduce = false;
                    break;
                }
            }
            if (reduce && start) {
                toRemoveColumns.add(column);
            } else {
                start = false;
            }
        }

        // from bottom to top
        start = true;
        for (int column = blocks.length-1; column >= 0; column--) {
            boolean reduce = true;
            for (int row = 0; row < blocks[0].length; row++) {
                if (blocks[column][row]) {
                    reduce = false;
                    break;
                }
            }
            if (reduce && start) {
                toRemoveColumns.add(column);
            } else {
                start = false;
            }
        }
        Collections.sort(toRemoveColumns);
        return toRemoveColumns;
    }

    private static ArrayList<Integer> getToRemoveRows(boolean[][] blocks) {
        ArrayList<Integer> toRemoveRows = new ArrayList<>();

        // from left to right
        boolean start = true;
        for (int row = 0; row < blocks[0].length; row++) {
            boolean reduce = true;
            for (boolean[] block : blocks) {
                if (block[row]) {
                    reduce = false;
                    break;
                }
            }
            if (reduce && start) {
                toRemoveRows.add(row);
            } else {
                start = false;
            }
        }

        // from right to left
        start = true;
        for (int row = blocks[0].length-1; row >= 0; row--) {
            boolean reduce = true;
            for (boolean[] block : blocks) {
                if (block[row]) {
                    reduce = false;
                    break;
                }
            }
            if (reduce && start) {
                toRemoveRows.add(row);
            } else {
                start = false;
            }
        }
        Collections.sort(toRemoveRows);
        return toRemoveRows;
    }

    private static boolean[][] removeColumn(boolean[][] blocks, int columnIndex) {
        boolean[][] newBlocks = new boolean[blocks.length - 1][];
        System.arraycopy(blocks, 0, newBlocks, 0, columnIndex);
        System.arraycopy(blocks, columnIndex+1, newBlocks, columnIndex, blocks.length - columnIndex - 1);
        return newBlocks;
    }

    private static void removeRow(boolean[][] blocks, int rowIndex) {
        for (int columnIndex = 0; columnIndex < blocks.length; columnIndex++) {
            boolean[] row = new boolean[blocks[columnIndex].length - 1];
            System.arraycopy(blocks[columnIndex], 0, row, 0, rowIndex);
            System.arraycopy(blocks[columnIndex], rowIndex+1, row, rowIndex, blocks[columnIndex].length - rowIndex - 1);
            blocks[columnIndex] = row;
        }
    }

    private static Location editStartLocation(Location start, int toRight, int toBottom, Direction direction) {
        if (direction == Direction.NORTH) return start.clone().subtract(0, toBottom, toRight);
        if (direction == Direction.EAST) return start.clone().subtract(0, toBottom, 0).add(toRight, 0, 0);
        if (direction == Direction.SOUTH) return start.clone().subtract(0, toBottom, 0).add(0, 0, toRight);
        if (direction == Direction.WEST) return start.clone().subtract(toRight, toBottom, 0);
        return null;
    }

}