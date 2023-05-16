package de.philw.textgenerator.utils;

import de.philw.textgenerator.letters.small.Block;

import java.awt.*;

public class Validator {

    public static boolean isValidFont(String font) {
        GraphicsEnvironment graphicsEnvironment;
        graphicsEnvironment= GraphicsEnvironment.getLocalGraphicsEnvironment();
        String []fonts=graphicsEnvironment.getAvailableFontFamilyNames();
        for (int i = 0; i < fonts.length; i++) {
            System.out.println(fonts[i]);
            if(fonts[i].equals(font)){
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
        if (!isInteger(size)) return false;
        int integer = Integer.parseInt(size);
        if (integer > 5 && integer < 250) {
            return true;
        }
        return false;
    }

    public static boolean isInteger(String integer) {
        try {
            Integer.parseInt(integer);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
