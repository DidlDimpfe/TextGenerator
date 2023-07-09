package de.philw.textgenerator.utils;

import de.philw.textgenerator.font.CurrentEditedText;
import de.philw.textgenerator.font.specificFontSize.SpecificFontSize;
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

    public static String[][] getBlocks(TextInstance textInstance) {
        if (textInstance.getFontSize() < 9) {
            return getBlocksFromSpecificFontSize(textInstance);
        }
        ArrayList<BufferedImage> linesAsBufferedImages = stringToBufferedImages(textInstance);
        ArrayList<String[][]> lines = new ArrayList<>();
        // find out the biggest width
        int width = 0;
        for (BufferedImage bufferedImage : linesAsBufferedImages) {
            if (bufferedImage.getWidth() > width) width = bufferedImage.getWidth();
        }
        String[][] biggest = null;
        // loop through all linesAsBufferedImages, make 2D-String arrays out of it (If block should be placed, then what block data)
        // and reduce them
        for (BufferedImage bufferedImage : linesAsBufferedImages) {
            String[][] tempLine = new String[bufferedImage.getHeight()][width];
            for (int heightIndex = 0; heightIndex < bufferedImage.getHeight(); heightIndex++) {
                for (int widthIndex = 0; widthIndex < bufferedImage.getWidth(); widthIndex++) {
                    int rgb = bufferedImage.getRGB(widthIndex, heightIndex);
                    tempLine[heightIndex][widthIndex] = rgb != -1 ? textInstance.getBlock().getNormalBlock() : null;
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
        return combineLines(lines, textInstance, width, biggest);
    }

    private static String[][] combineLines(ArrayList<String[][]> lines, TextInstance textInstance, int width, String[][] biggest) {
        // make all 2D-String arrays the same width
        for (int arrayIndex = 0; arrayIndex < lines.size(); arrayIndex++) {
            String[][] oldLine = lines.get(arrayIndex);
            if (oldLine == biggest) continue;
            String[][] doneLine = new String[oldLine.length][width];
            for (int column = 0; column < oldLine.length; column++) {
                System.arraycopy(oldLine[column], 0, doneLine[column], 0, oldLine[0].length);
            }
            lines.set(arrayIndex, doneLine);
        }
        // merge the 2D-Boolean arrays to a big 2D-String considering the in the textInstance given
        // spaceBetweenEachLine
        int height = 0;
        int spaceBetweenEachLine = textInstance.getLineSpacing();
        for (String[][] doneLine : lines) {
            height += doneLine.length + spaceBetweenEachLine;
        }
        height -= spaceBetweenEachLine;
        String[][] blocks = new String[height][width];
        int currentLineIndexCount = 0;
        for (String[][] doneLine : lines) {
            for (String[] booleans : doneLine) {
                blocks[currentLineIndexCount] = booleans;
                currentLineIndexCount++;
            }
            currentLineIndexCount += spaceBetweenEachLine;
        }
        return blocks;
    }

    private static ArrayList<Integer> getToRemoveColumns(String[][] blocks) {
        ArrayList<Integer> toRemoveColumns = new ArrayList<>();

        // from top to bottom
        boolean start = true;
        for (int column = 0; column < blocks.length; column++) {
            boolean reduce = true;
            for (int row = 0; row < blocks[0].length; row++) {
                if (blocks[column][row] != null) {
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
                if (blocks[column][row] != null) {
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

    private static ArrayList<Integer> getToRemoveRows(String[][] blocks) {
        ArrayList<Integer> toRemoveRows = new ArrayList<>();

        // from left to right
        boolean start = true;
        for (int row = 0; row < blocks[0].length; row++) {
            boolean reduce = true;
            for (String[] block : blocks) {
                if (block[row] != null) {
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
            for (String[] block : blocks) {
                if (block[row] != null) {
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

    private static String[][] removeColumn(String[][] blocks, int columnIndex) {
        String[][] newBlocks = new String[blocks.length - 1][];
        System.arraycopy(blocks, 0, newBlocks, 0, columnIndex);
        System.arraycopy(blocks, columnIndex + 1, newBlocks, columnIndex, blocks.length - columnIndex - 1);
        return newBlocks;
    }

    private static void removeRow(String[][] blocks, int rowIndex) {
        for (int columnIndex = 0; columnIndex < blocks.length; columnIndex++) {
            String[] row = new String[blocks[columnIndex].length - 1];
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

        for (int i = 1; i <= ConfigManager.getPlacementRange(); i++) {
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

    private static String[][] getBlocksFromSpecificFontSize(TextInstance textInstance) {
        SpecificFontSize specificFontSize = SpecificFontSize.getClassFromFontSize(textInstance.getFontSize());
        String[] linesAsNormalText = textInstance.getText().split("\\\\n");

        ArrayList<String[][]> lines = new ArrayList<>();
        for (String line: linesAsNormalText) {
            lines.add(getLine(textInstance, line, specificFontSize));
        }
        // find out the biggest width
        int width = 0;
        String[][] biggest = null;
        for (String[][] line: lines) {
            if (line[0].length > width) {
                width = line[0].length;
                biggest = line;
            }
        }
        return combineLines(lines, textInstance, width, biggest);
    }


    private static String[][] getLine(TextInstance textInstance, String text, SpecificFontSize specificFontSize) {
        int height = Objects.requireNonNull(getLetter('a', textInstance, specificFontSize)).length;
        if (textInstance.isUnderline()) height++;
        int width = 0;
        for (char character: text.toLowerCase().toCharArray()) {
            if (character == ' ') {
                width += 3;
                continue;
            }
            width += (Objects.requireNonNull(getLetter(character, textInstance, specificFontSize))[0].length + 1);
        }
        width--;
        String[][] line = new String[height][width];
        int currentWidth = 0;
        for (char character: text.toLowerCase().toCharArray()) {
            if (character == ' ') {
                currentWidth += 3;
                continue;
            }
            String[][] letter =  getLetter(character, textInstance, specificFontSize);
            // Loop through the letter
            for (int heightIndex = 0; heightIndex < Objects.requireNonNull(letter).length; heightIndex++) {
                System.arraycopy(letter[heightIndex], 0, line[heightIndex], currentWidth, letter[0].length);
            }
            currentWidth += (letter[0].length + 1);
        }
        int lowestRow = height - 1;
        if (textInstance.isUnderline()) {
            for (int i = 0; i < width; i++) {
                line[lowestRow][i] = textInstance.getBlock().getBottomSlap();
            }
        }
        return line;
    }

    // Currently allowed a-z 1-9 .,;:?!'\
    public static boolean isNotTextValidForSpecificFontSize(String text) {
        return !text.matches("[ .,;:?!'\\\\a-zA-Z0-9]+");
    }

    private static String[][] getLetter(Character character, TextInstance textInstance, SpecificFontSize specificFontSize) {
        switch (character) {
            case 'a':
                return specificFontSize.getA(textInstance.getBlock(), textInstance.getDirection());
            case 'b':
                return specificFontSize.getB(textInstance.getBlock(), textInstance.getDirection());
            case 'c':
                return specificFontSize.getC(textInstance.getBlock(), textInstance.getDirection());
            case 'd':
                return specificFontSize.getD(textInstance.getBlock(), textInstance.getDirection());
            case 'e':
                return specificFontSize.getE(textInstance.getBlock(), textInstance.getDirection());
            case 'f':
                return specificFontSize.getF(textInstance.getBlock(), textInstance.getDirection());
            case 'g':
                return specificFontSize.getG(textInstance.getBlock(), textInstance.getDirection());
            case 'h':
                return specificFontSize.getH(textInstance.getBlock(), textInstance.getDirection());
            case 'i':
                return specificFontSize.getI(textInstance.getBlock(), textInstance.getDirection());
            case 'j':
                return specificFontSize.getJ(textInstance.getBlock(), textInstance.getDirection());
            case 'k':
                return specificFontSize.getK(textInstance.getBlock(), textInstance.getDirection());
            case 'l':
                return specificFontSize.getL(textInstance.getBlock(), textInstance.getDirection());
            case 'm':
                return specificFontSize.getM(textInstance.getBlock(), textInstance.getDirection());
            case 'n':
                return specificFontSize.getN(textInstance.getBlock(), textInstance.getDirection());
            case 'o':
                return specificFontSize.getO(textInstance.getBlock(), textInstance.getDirection());
            case 'p':
                return specificFontSize.getP(textInstance.getBlock(), textInstance.getDirection());
            case 'q':
                return specificFontSize.getQ(textInstance.getBlock(), textInstance.getDirection());
            case 'r':
                return specificFontSize.getR(textInstance.getBlock(), textInstance.getDirection());
            case 's':
                return specificFontSize.getS(textInstance.getBlock(), textInstance.getDirection());
            case 't':
                return specificFontSize.getT(textInstance.getBlock(), textInstance.getDirection());
            case 'u':
                return specificFontSize.getU(textInstance.getBlock(), textInstance.getDirection());
            case 'v':
                return specificFontSize.getV(textInstance.getBlock(), textInstance.getDirection());
            case 'w':
                return specificFontSize.getW(textInstance.getBlock(), textInstance.getDirection());
            case 'x':
                return specificFontSize.getX(textInstance.getBlock(), textInstance.getDirection());
            case 'y':
                return specificFontSize.getY(textInstance.getBlock(), textInstance.getDirection());
            case 'z':
                return specificFontSize.getZ(textInstance.getBlock(), textInstance.getDirection());
            case '0':
                return specificFontSize.get0(textInstance.getBlock(), textInstance.getDirection());
            case '1':
                return specificFontSize.get1(textInstance.getBlock(), textInstance.getDirection());
            case '2':
                return specificFontSize.get2(textInstance.getBlock(), textInstance.getDirection());
            case '3':
                return specificFontSize.get3(textInstance.getBlock(), textInstance.getDirection());
            case '4':
                return specificFontSize.get4(textInstance.getBlock(), textInstance.getDirection());
            case '5':
                return specificFontSize.get5(textInstance.getBlock(), textInstance.getDirection());
            case '6':
                return specificFontSize.get6(textInstance.getBlock(), textInstance.getDirection());
            case '7':
                return specificFontSize.get7(textInstance.getBlock(), textInstance.getDirection());
            case '8':
                return specificFontSize.get8(textInstance.getBlock(), textInstance.getDirection());
            case '9':
                return specificFontSize.get9(textInstance.getBlock(), textInstance.getDirection());
            case '.':
                return specificFontSize.getFullStop(textInstance.getBlock(), textInstance.getDirection());
            case ',':
                return specificFontSize.getComma(textInstance.getBlock(), textInstance.getDirection());
            case ';':
                return specificFontSize.getSemicolon(textInstance.getBlock(), textInstance.getDirection());
            case ':':
                return specificFontSize.getColon(textInstance.getBlock(), textInstance.getDirection());
            case '?':
                return specificFontSize.getQuestionMark(textInstance.getBlock(), textInstance.getDirection());
            case '!':
                return specificFontSize.getExclamationMark(textInstance.getBlock(), textInstance.getDirection());
            case '\'':
                return specificFontSize.getApostrophe(textInstance.getBlock(), textInstance.getDirection());
            case '\\':
                return specificFontSize.getBackslash(textInstance.getBlock(), textInstance.getDirection());
        }
        return null;
    }

}