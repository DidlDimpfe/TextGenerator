package de.philw.textgenerator.utils;

import de.philw.textgenerator.letters.CurrentEditedText;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.manager.GeneratedTextsManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.util.*;

public class GenerateUtil {

    public static ArrayList<BufferedImage> stringToBufferedImages(TextInstance textInstance) {
        String text = textInstance.getText();
        boolean underline = textInstance.isUnderline();
        String fontName = textInstance.getFontName();
        int fontStyle = textInstance.getFontStyle();
        int fontSize = textInstance.getFontSize();

        String[] lines = text.split("\\\\n");

        BufferedImage createGraphicsImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphicsTool = createGraphicsImage.createGraphics();

        Map<TextAttribute, Integer> fontAttributes = new HashMap<>();
        if (underline) fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        Font font = new Font(fontName, fontStyle, fontSize).deriveFont(fontAttributes);

        graphicsTool.setFont(font);
        FontMetrics fontMetrics = graphicsTool.getFontMetrics();
        graphicsTool.dispose();

        ArrayList<BufferedImage> linesAsBufferedImages = new ArrayList<>();

        for (String line : lines) {
            int width = fontMetrics.stringWidth(line);
            int height = fontMetrics.getHeight();
            BufferedImage letters = new BufferedImage(fontMetrics.stringWidth(line), fontMetrics.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            graphicsTool = letters.createGraphics();
            graphicsTool.setColor(Color.WHITE);
            graphicsTool.fillRect(0, 0, width, height);
            graphicsTool.setFont(font);
            fontMetrics = graphicsTool.getFontMetrics();
            graphicsTool.setColor(Color.BLACK);
            graphicsTool.drawString(line, 0, fontMetrics.getAscent());
            linesAsBufferedImages.add(letters);
        }

        graphicsTool.dispose();
        return linesAsBufferedImages;
    }

    public static boolean[][] getBlocks(TextInstance textInstance) {
        if (textInstance.getFontSize() < 9) {

        }
        ArrayList<BufferedImage> linesAsBufferedImages = stringToBufferedImages(textInstance);
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
        // merge the 2D-Boolean arrays to a big 2D-Boolean considering the in the textInstance given
        // spaceBetweenEachLine
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

    public static boolean areLocationsEqual(Location l1, Location l2) {
        return l1.getWorld() == l2.getWorld() && l1.getY() == l2.getY() && l1.getX() == l2.getX() && l1.getZ() == l2.getZ();
    }

    public static Location editLocation(TextInstance textInstance, Location location, double toRight, double toBottom,
                                        double toLeft, double toTop, double toFront, double toBack) {
        if (textInstance.getDirection() == Direction.NORTH)
            return location.clone().subtract(toBack, toBottom, toRight).add(toFront, toTop, toLeft);
        if (textInstance.getDirection() == Direction.EAST)
            return location.clone().subtract(toLeft, toBottom, toBack).add(toRight, toTop, toFront);
        if (textInstance.getDirection() == Direction.SOUTH)
            return location.clone().subtract(toFront, toBottom, toLeft).add(toBack, toTop, toRight);
        if (textInstance.getDirection() == Direction.WEST)
            return location.clone().subtract(toRight, toBottom, toFront).add(toLeft, toTop, toBack);
        return null;
    }

    public static CurrentEditedText getPlayersWantedTextToEdit(Player player) {
        // Get all Cuboids from previously generated Texts
        Map<UUID, Cuboid> possibleCuboidsFromPreviousGeneratedText = new HashMap<>();
        for (UUID uuid : GeneratedTextsManager.getUUIDs()) {
            TextInstance textInstance = GeneratedTextsManager.getTextInstance(uuid);
            possibleCuboidsFromPreviousGeneratedText.put(uuid, new Cuboid(textInstance.getTopLeftLocation(),
                    textInstance.getBottomRightLocation()));
        }

        if (possibleCuboidsFromPreviousGeneratedText.isEmpty()) return null;

        // Check if a Cuboid from previously generated Texts contains possible Locations the player wants

        int playerLocationX = (int) (player.getLocation().add(0, player.getEyeHeight(), 0).getX());
        int playerLocationY = (int) (player.getLocation().add(0, player.getEyeHeight(), 0).getY());
        int playerLocationZ = (int) (player.getLocation().add(0, player.getEyeHeight(), 0).getZ());

        Vector normalVector = player.getLocation().getDirection().normalize();

        for (int i = 1; i <= ConfigManager.getPlaceRange(); i++) {
            int x = playerLocationX + (int) normalVector.clone().multiply(i).getX();
            int y = playerLocationY + (int) normalVector.clone().multiply(i).getY();
            int z = playerLocationZ + (int) normalVector.clone().multiply(i).getZ();

            Location possibleLocation = new Location(player.getWorld(), x, y, z);
            for (UUID uuid : possibleCuboidsFromPreviousGeneratedText.keySet()) {
                if (possibleCuboidsFromPreviousGeneratedText.get(uuid).contains(possibleLocation)) {
                    return new CurrentEditedText(player, GeneratedTextsManager.getTextInstance(uuid));
                }
            }
        }

        return null;
    }

}