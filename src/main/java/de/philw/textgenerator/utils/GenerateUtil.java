package de.philw.textgenerator.utils;

import de.philw.textgenerator.manager.ConfigManager;
import org.bukkit.Location;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class GenerateUtil {

    public static boolean[][] getBlocks(ArrayList<BufferedImage> linesAsBufferedImages, TextInstance textInstance) {
        ArrayList<boolean[][]> lines = new ArrayList<>();
        // find out the biggest width
        int width = 0;
        for (BufferedImage bufferedImage : linesAsBufferedImages) {
            if (bufferedImage.getWidth() > width) width = bufferedImage.getWidth();
        }
        boolean[][] biggest = null;
        // loop through all linesAsBufferedImages, make 2D-Boolean arrays out of it (what block has to be placed and
        // what not)
        // and reduce them
        for (BufferedImage bufferedImage : linesAsBufferedImages) {
            boolean[][] tempLine = new boolean[bufferedImage.getHeight()][width];
            for (int heightIndex = 0; heightIndex < bufferedImage.getHeight(); heightIndex++) {
                for (int widthIndex = 0; widthIndex < bufferedImage.getWidth(); widthIndex++) {
                    int rgb = bufferedImage.getRGB(widthIndex, heightIndex);
                    tempLine[heightIndex][widthIndex] = rgb != -1;
                }
            }
            int alreadyRemoved = 0;
            for (int columnIndex : getToRemoveColumns(tempLine)) {
                tempLine = removeColumn(tempLine, columnIndex - alreadyRemoved);
                alreadyRemoved++;
            }
            alreadyRemoved = 0;
            for (int rowIndex : getToRemoveRows(tempLine)) {
                removeRow(tempLine, rowIndex - alreadyRemoved);
                alreadyRemoved++;
            }
            if (bufferedImage.getWidth() == width) {
                biggest = tempLine;
            }
            lines.add(tempLine);
        }
        // make all 2D-Boolean arrays the same width
        for (int arrayIndex = 0; arrayIndex < lines.size(); arrayIndex++) {
            boolean[][] oldLine = lines.get(arrayIndex);
            if (oldLine == biggest) continue;
            boolean[][] doneLine = new boolean[oldLine.length][width];
            for (int column = 0; column < oldLine.length; column++) {
                System.arraycopy(oldLine[column], 0, doneLine[column], 0, oldLine[0].length);
            }
            lines.set(arrayIndex, doneLine);
        }
        // merge the 2D-Boolean arrays to a big 2D-Boolean considering the in the config given spaceBetweenEachLine
        int height = 0;
        int spaceBetweenEachLine = textInstance.getLineSpacing();
        for (boolean[][] doneLine : lines) {
            height += doneLine.length + spaceBetweenEachLine;
        }
        height -= spaceBetweenEachLine;
        boolean[][] blocks = new boolean[height][width];
        int currentLineIndexCount = 0;
        for (boolean[][] doneLine : lines) {
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
        for (int column = blocks.length - 1; column >= 0; column--) {
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
        for (int row = blocks[0].length - 1; row >= 0; row--) {
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
        System.arraycopy(blocks, columnIndex + 1, newBlocks, columnIndex, blocks.length - columnIndex - 1);
        return newBlocks;
    }

    private static void removeRow(boolean[][] blocks, int rowIndex) {
        for (int columnIndex = 0; columnIndex < blocks.length; columnIndex++) {
            boolean[] row = new boolean[blocks[columnIndex].length - 1];
            System.arraycopy(blocks[columnIndex], 0, row, 0, rowIndex);
            System.arraycopy(blocks[columnIndex], rowIndex + 1, row, rowIndex,
                    blocks[columnIndex].length - rowIndex - 1);
            blocks[columnIndex] = row;
        }
    }

    public static boolean areLocationsEqual(Location a, Location b) {
        return a.getWorld() == b.getWorld() && a.getY() == b.getY() && a.getX() == b.getX() && a.getZ() == b.getZ();
    }

//    public static void setBlockInNativeDataPalette(World world, int x, int y, int z, int blockId, byte data, boolean applyPhysics) {
//        org.bukkit.nmsWorld = ((CraftWorld) world).getHandle();
//        net.minecraft.server.v1_14_R1.Chunk nmsChunk = nmsWorld.getChunkAt(x >> 4, z >> 4);
//        IBlockData ibd = net.minecraft.server.v1_14_R1.Block.getByCombinedId(blockId + (data << 12));
//
//        LevelChunkSection cs = nmsChunk.getSections()[y >> 4];
//        if (cs == nmsChunk.a()) {
//            cs = new ChunkSection(y >> 4 << 4);
//            nmsChunk.getSections()[y >> 4] = cs;
//        }
//
//        if (applyPhysics)
//            cs.getBlocks().setBlock(x & 15, y & 15, z & 15, ibd);
//        else
//            cs.getBlocks().b(x & 15, y & 15, z & 15, ibd);
//    }

}