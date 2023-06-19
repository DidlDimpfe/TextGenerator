package de.philw.textgenerator.utils;

import de.philw.textgenerator.ui.value.Block;

import java.awt.*;

public class Validator {

    public static boolean isValidFont(String font) {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = graphicsEnvironment.getAvailableFontFamilyNames();
        for (String string : fonts) {
            if (string.equals(font)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidBlock(String block) {
        try {
            Block.valueOf(block.toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidSize(String size) {
        if (isNoInteger(size)) return false;
        int integer = Integer.parseInt(size);
        return integer > 5 && integer < 250;
    }

    public static boolean isNoInteger(String integer) {
        try {
            Integer.parseInt(integer);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    public static boolean isValidFontStyle(String fontStyle) {
        return fontStyle.equalsIgnoreCase("Bold") || fontStyle.equalsIgnoreCase("Italic")
                || fontStyle.equalsIgnoreCase("BoldItalic") || fontStyle.equalsIgnoreCase("Plain");
    }

    public static boolean isValidBoolean(String bool) {
        return bool.equalsIgnoreCase("true") || bool.equalsIgnoreCase("false");
    }

    public static boolean isValidSpaceBetweenEachLine(String spaceBetweenEachLine) {
        if (isNoInteger(spaceBetweenEachLine)) return false;
        return Integer.parseInt(spaceBetweenEachLine) > 0;
    }

}
