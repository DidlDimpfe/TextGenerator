package de.philw.textgenerator.manager;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.letters.Block;
import de.philw.textgenerator.utils.Validator;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ConfigManager {

    private static FileConfiguration config;


    /**
     * This method creates a config and saves the default values from it.
     */

    public static void setUpConfig() {
        ConfigManager.config = TextGenerator.getInstance().getConfig();
        TextGenerator.getInstance().saveDefaultConfig();
        updateConfig(TextGenerator.getInstance());
    }

    public static Block getBlock() {
        String block = config.getString("textSettings.block");
        if (!Validator.isValidBlock(Objects.requireNonNull(block))) {
            System.err.println("'" + block + "' is not a valid block! The default block 'quartz' will be taken.");
            return Block.QUARTZ;
        }
        return Block.valueOf(block.toUpperCase());
    }

    public static String getFontName() {
        String fontName = config.getString("textSettings.fontName");
        if (!Validator.isValidFont(fontName)) {
            System.err.println("'" + fontName + "' is not a valid font! The default font 'SansSerif' will be taken.");
            return "SansSerif";
        }
        return fontName;
    }

    public static int getFontSize() {
        String size = config.getString("textSettings.fontSize");
        if (!Validator.isValidSize(size)) {
            System.err.println("'" + size + "' is not a valid size! The default size '15' will be taken.");
            return 15;

        }
        return Integer.parseInt(Objects.requireNonNull(size));
    }

    public static int getFontStyle() {
        String fontStyle = config.getString("textSettings.fontStyle");
        if (!Validator.isValidFontStyle(Objects.requireNonNull(fontStyle))) {
            System.err.println("'" + fontStyle + "' is not a valid font style! The default font style 'Bold' will be taken.");
        }
        if (fontStyle.equalsIgnoreCase("Bold")) return 1;
        else if (fontStyle.equalsIgnoreCase("Italic")) return 2;
        else if (fontStyle.equalsIgnoreCase("BoldItalic")) return 3;
        else return 4; // Plain
    }

    public static boolean isUnderline() {
        String bool = config.getString("textSettings.underline");
        if (!Validator.isValidBoolean(Objects.requireNonNull(bool))) {
            System.err.println("'" + bool + "' is not a valid underline (either true or false)! The default underline 'false' will be taken.");
            return false;
        }
        return Boolean.parseBoolean(bool.toLowerCase());
    }

    public static int getSpaceBetweenEachLine() {
        String spaceBetweenEachLine = config.getString("textSettings.spaceBetweenEachLine");
        if (!Validator.isValidSpaceBetweenEachLine(spaceBetweenEachLine)) {
            System.err.println("'" + spaceBetweenEachLine + "' is not a valid spaceBetweenEachLine! The default spaceBetweenEachLine '2' will be taken.");
            return 2;
        }
        return Integer.parseInt(Objects.requireNonNull(spaceBetweenEachLine));
    }

    /**
     * This method adds a field, if the field is not in the config, to the config with the default value
     */

    private static void updateConfig(TextGenerator textGenerator) {
        if (!config.isSet("textSettings.block")) {
            config.set("textSettings.block", Objects.requireNonNull(config.getDefaults()).get("textSettings.block"));
        }
        if (!config.isSet("textSettings.fontName")) {
            config.set("textSettings.fontName", Objects.requireNonNull(config.getDefaults()).get("textSettings.fontName"));
        }
        if (!config.isSet("textSettings.fontSize")) {
            config.set("textSettings.fontSize", Objects.requireNonNull(config.getDefaults()).get("textSettings.fontSize"));
        }
        if (!config.isSet("textSettings.fontStyle")) {
            config.set("textSettings.fontStyle", Objects.requireNonNull(config.getDefaults()).get("textSettings.fontStyle"));
        }
        if (!config.isSet("textSettings.underline")) {
            config.set("textSettings.underline", Objects.requireNonNull(config.getDefaults()).get("textSettings.underline"));
        }
        if (!config.isSet("textSettings.spaceBetweenEachLine")) {
            config.set("textSettings.spaceBetweenEachLine", Objects.requireNonNull(config.getDefaults()).get("textSettings.spaceBetweenEachLine"));
        }
        try {
            config.save(new File(textGenerator.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
