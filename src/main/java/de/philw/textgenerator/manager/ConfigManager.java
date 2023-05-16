package de.philw.textgenerator.manager;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.letters.small.Block;
import de.philw.textgenerator.utils.Direction;
import de.philw.textgenerator.utils.TextInstance;
import de.philw.textgenerator.utils.Validator;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.awt.*;
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
        if (!Validator.isValidBlock(block)) {
            System.err.println(block + "is not a valid block! The default block 'quartz' will be taken.");
            return Block.QUARTZ;
        }
        return Block.valueOf(block);
    }

    public static String getFontName() {
        String fontName = config.getString("textSettings.fontName");
        if (!Validator.isValidFont(fontName)) {
            System.err.println(fontName + "is not a valid font! The default font 'SansSerif' will be taken.");
            return "SansSerif";
        }
        return fontName;
    }

    public static int getFontSize() {
        String size = config.getString("textSettings.fontSize");
        if (!Validator.isValidSize(size)) {
            if (!Validator.isValidFont(size)) {
                System.err.println(size + "is not a valid size! The default size '15' will be taken.");
                return 15;
            }
        }
        return Integer.parseInt(size);
    }

    // Bis hier gekommen

    public static int getTimer() {
        return config.getInt("crafting-table-timer");
    }

    public static boolean getUINeedPermission() {
        return config.getBoolean("crafting-table-ui-need-permission");
    }

    /**
     * This method adds a field, if the field is not in the config, to the config with the default value
     */

    private static void updateConfig(TextGenerator textGenerator) {
        if (!config.isSet("textSetting.block")) {
            config.set("textSetting.block", Objects.requireNonNull(config.getDefaults()).get("textSetting.block"));
        }
        if (!config.isSet("textSetting.fontName")) {
            config.set("textSetting.fontName", Objects.requireNonNull(config.getDefaults()).get("textSetting.fontName"));
        }
        if (!config.isSet("textSetting.fontSize")) {
            config.set("textSetting.fontSize", Objects.requireNonNull(config.getDefaults()).get("textSetting.fontSize"));
        }
        if (!config.isSet("textSetting.fontStyle")) {
            config.set("textSetting.fontStyle", Objects.requireNonNull(config.getDefaults()).get("textSetting.fontStyle"));
        }
        if (!config.isSet("textSetting.underline")) {
            config.set("textSetting.underline", Objects.requireNonNull(config.getDefaults()).get("textSetting.underline"));
        }
        try {
            config.save(new File(textGenerator.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
