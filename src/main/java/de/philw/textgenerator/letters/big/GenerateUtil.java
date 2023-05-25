package de.philw.textgenerator.letters.big;

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

    public static void setTextInstance(TextInstance textInstance) {
        GenerateUtil.textInstance = textInstance;
    }

    public static void buildBlocks(BufferedImage textInPicture) {
        boolean[][] blocks = getBlocks(textInPicture);
        for (int heightIndex = 0; heightIndex < blocks.length; heightIndex++) {
            for (int widthIndex = 0; widthIndex < blocks[0].length; widthIndex++) {
                if (blocks[heightIndex][widthIndex]) {
                    Objects.requireNonNull(
                            textInstance.getStartLocation().getWorld()).getBlockAt(Objects.requireNonNull(editStartLocation(textInstance.getStartLocation(), widthIndex, heightIndex, textInstance.getDirection()))).
                            setBlockData(Bukkit.createBlockData(textInstance.getBlock().getNormalBlockData()));
                }
            }
        }
    }

    public static HashMap<Location, BlockData> getAffectedBlocks(BufferedImage textInPicture) {
        HashMap<Location, BlockData> affectedBlocks = new HashMap<>();
        boolean[][] blocks = getBlocks(textInPicture);
        for (int heightIndex = 0; heightIndex < blocks.length; heightIndex++) {
            for (int widthIndex = 0; widthIndex < blocks[0].length; widthIndex++) {
                Block block = Objects.requireNonNull(textInstance.getStartLocation().getWorld()).getBlockAt(
                        Objects.requireNonNull(editStartLocation(textInstance.getStartLocation(), widthIndex, heightIndex, textInstance.getDirection())));
                        affectedBlocks.put(block.getLocation(), block.getBlockData());
            }
        }
        return affectedBlocks;
    }

    private static boolean[][] getBlocks(BufferedImage textInPicture) {
        boolean[][] blocks = new boolean[textInPicture.getHeight()][textInPicture.getWidth()];

        for (int heightIndex = 0; heightIndex < textInPicture.getHeight(); heightIndex++) {
            for (int widthIndex = 0; widthIndex < textInPicture.getWidth(); widthIndex++) {
                int rgb = textInPicture.getRGB(widthIndex, heightIndex);
                if (rgb == -1)  {
                    blocks[heightIndex][widthIndex] = false;
                } else if (rgb == -16777216){
                    blocks[heightIndex][widthIndex] = true;
                }
            }
        }

        int alreadyRemoved = 0;
        for (int columnIndex: getToRemoveColumns(blocks)) {
            blocks = removeColumn(blocks, columnIndex-alreadyRemoved);
            alreadyRemoved++;
        }
        alreadyRemoved = 0;
        for (int rowIndex: getToRemoveRows(blocks)) {
            removeRow(blocks, rowIndex - alreadyRemoved);
            alreadyRemoved++;
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
        // Remove the spaces between each line
        int spaceLineCount = 0;
        for (int column = 0; column < blocks.length; column++) {
            boolean reduce = true;
            for (int row = 0; row < blocks[0].length; row++) {
                if (blocks[column][row]) {
                    reduce = false;
                    spaceLineCount = 0;
                    break;
                }
            }
            if (!reduce) continue;
            // to here the column is empty
            if (toRemoveColumns.contains(column)) continue;
            // to here the column is not already removed
            spaceLineCount++;
            if (spaceLineCount > textInstance.getSpaceBetweenEachLine()) {
                toRemoveColumns.add(column);
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
