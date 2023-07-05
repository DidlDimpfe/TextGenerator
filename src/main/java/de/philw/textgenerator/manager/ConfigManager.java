package de.philw.textgenerator.manager;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.ui.value.Block;
import de.philw.textgenerator.utils.FileUtil;
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
        updateConfig();
    }

    public static Block getBlock() {
        String block = config.getString("textSettings.block");
        if (!Validator.isValidBlock(Objects.requireNonNull(block))) {
            printNotValidMessage(block, Objects.requireNonNull(config.getDefaults()).getString("textSettings.block"),
                    "block");
            return Block.valueOf(config.getDefaults().getString("textSettings.block"));
        }
        return Block.valueOf(block.toUpperCase());
    }

    public static void setBlock(Block block) {
        config.set("textSettings.block", block.name());
        saveConfig();
    }

    public static String getFontName() {
        String fontName = config.getString("textSettings.fontName");
        if (!Validator.isValidFont(fontName)) {
            printNotValidMessage(fontName, Objects.requireNonNull(config.getDefaults()).getString("textSettings" +
                    ".fontName"), "font name");
            return config.getDefaults().getString("textSettings.fontName");
        }
        return fontName;
    }

    public static int getFontSize() {
        String size = config.getString("textSettings.fontSize");
        if (!Validator.isValidFontSize(size)) {
            printNotValidMessage(size, String.valueOf(Objects.requireNonNull(config.getDefaults()).getInt(
                    "textSettings.fontSize")), "font size");
            return config.getDefaults().getInt("textSettings.fontSize");
        }
        return Integer.parseInt(Objects.requireNonNull(size));
    }

    public static void setFontSize(int size) {
        config.set("textSettings.fontSize", size);
        saveConfig();
    }

    public static int getFontStyle() {
        String fontStyle = config.getString("textSettings.fontStyle");
        if (!Validator.isValidFontStyle(Objects.requireNonNull(fontStyle))) {
            printNotValidMessage(fontStyle, Objects.requireNonNull(config.getDefaults()).getString("textSettings" +
                    ".fontStyle"), "font style");
            return 1;
        }
        return FileUtil.fromFontStyleStringToInt(fontStyle);
    }

    public static void setFontStyle(String fontStyle) {
        config.set("textSettings.fontStyle", fontStyle);
        saveConfig();
    }

    public static boolean isUnderline() {
        String bool = config.getString("textSettings.underline");
        if (Validator.isNoValidBoolean(Objects.requireNonNull(bool))) {
            printNotValidMessage(bool, String.valueOf(Objects.requireNonNull(config.getDefaults()).getBoolean(
                    "textSettings.underline")), "underline");
            return config.getDefaults().getBoolean("textSettings.underline");
        }
        return Boolean.parseBoolean(bool.toLowerCase());
    }

    public static void setUnderline(boolean underline) {
        config.set("textSettings.underline", underline);
        saveConfig();
    }

    public static int getLineSpacing() {
        String spaceBetweenEachLine = config.getString("textSettings.lineSpacing");
        if (!Validator.isValidLineSpacing(spaceBetweenEachLine)) {
            printNotValidMessage(spaceBetweenEachLine,
                    String.valueOf(Objects.requireNonNull(config.getDefaults()).getInt("textSettings.lineSpacing")),
                    "lineSpacing");
            return config.getDefaults().getInt("textSettings.lineSpacing");
        }
        return Integer.parseInt(Objects.requireNonNull(spaceBetweenEachLine));
    }

    public static void setLineSpacing(int lineSpacing) {
        config.set("textSettings.lineSpacing", lineSpacing);
        saveConfig();
    }

    public static int getPlacementRange() {
        String placementRange = config.getString("placementRange");
        if (!Validator.isValidPlacementRange(placementRange)) {
            printNotValidMessage(placementRange, String.valueOf(Objects.requireNonNull(config.getDefaults()).getInt(
                    "placementRange")), "place range");
            return config.getDefaults().getInt("placementRange");
        }
        return Integer.parseInt(Objects.requireNonNull(placementRange));
    }

    public static void setPlacementRange(int placementRange) {
        config.set("placementRange", placementRange);
        saveConfig();
    }

    public static boolean isDragToMove() {
        String dragToMove = config.getString("dragToMove");
        if (Validator.isNoValidBoolean(Objects.requireNonNull(dragToMove))) {
            printNotValidMessage(dragToMove, String.valueOf(Objects.requireNonNull(config.getDefaults()).getInt(
                    "dragToMove")), "drag to move");
            return Objects.requireNonNull(config.getDefaults()).getBoolean("dragToMove");
        }
        return Boolean.parseBoolean(dragToMove.toLowerCase());
    }

    public static void setDragToMove(boolean dragToMove) {
        config.set("dragToMove", dragToMove);
        saveConfig();
    }

    public static void printNotValidMessage(String notValidValue, String defaultValue, String dataType) {
        System.err.println(TextGenerator.getMessageBeginning() + "The in your config assigned " + dataType + " '" + notValidValue + "' " +
                "is not a valid " + dataType + "! The default " + dataType + " '" + defaultValue + "' will be taken.");
    }

    public static void saveConfig() {
        try {
            config.save(new File(TextGenerator.getInstance().getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method adds a field, if the field is not in the config, to the config with the default value
     */

    private static void updateConfig() {
        if (!config.isSet("textSettings.block")) {
            config.set("textSettings.block", Objects.requireNonNull(config.getDefaults()).get("textSettings.block"));
        }
        if (!config.isSet("textSettings.fontName")) {
            config.set("textSettings.fontName", Objects.requireNonNull(config.getDefaults()).get("textSettings" +
                    ".fontName"));
        }
        if (!config.isSet("textSettings.fontSize")) {
            config.set("textSettings.fontSize", Objects.requireNonNull(config.getDefaults()).get("textSettings" +
                    ".fontSize"));
        }
        if (!config.isSet("textSettings.fontStyle")) {
            config.set("textSettings.fontStyle", Objects.requireNonNull(config.getDefaults()).get("textSettings" +
                    ".fontStyle"));
        }
        if (!config.isSet("textSettings.underline")) {
            config.set("textSettings.underline", Objects.requireNonNull(config.getDefaults()).get("textSettings" +
                    ".underline"));
        }
        if (!config.isSet("textSettings.lineSpacing")) {
            config.set("textSettings.lineSpacing", Objects.requireNonNull(config.getDefaults()).get(
                    "textSettings.lineSpacing"));
        }
        if (!config.isSet("placementRange")) {
            config.set("placementRange", Objects.requireNonNull(config.getDefaults()).get(
                    "placementRange"));
        }
        if (!config.isSet("dragToMove")) {
            config.set("dragToMove", Objects.requireNonNull(config.getDefaults()).get(
                    "dragToMove"));
        }
        saveConfig();
    }

}