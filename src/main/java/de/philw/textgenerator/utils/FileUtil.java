package de.philw.textgenerator.utils;

public class FileUtil {

    public static String fromFontStyleIntToString(int fontStyle) {
        if (fontStyle == 1) return "Bold";
        else if (fontStyle == 2) return "Italic";
        else if (fontStyle == 3) return "BoldItalic";
        else return "Plain";
    }

    public static int fromFontStyleStringToInt(String fontStyle) {
        if (fontStyle.equalsIgnoreCase("Bold")) return 1;
        else if (fontStyle.equalsIgnoreCase("Italic")) return 2;
        else if (fontStyle.equalsIgnoreCase("BoldItalic")) return 3;
        else return 4; // Plain
    }

}
