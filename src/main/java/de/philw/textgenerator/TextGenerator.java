package de.philw.textgenerator;

import de.philw.textgenerator.command.TextGeneratorCommand;
import de.philw.textgenerator.letters.big.LetterConverter;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.ui.SettingUIListener;
import de.philw.textgenerator.ui.SettingsUI;
import de.philw.textgenerator.utils.Direction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class TextGenerator extends JavaPlugin {

    private static TextGenerator INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        ConfigManager.setUpConfig();
//        Location start = new Location(Bukkit.getWorld("world"), -59, 73, 98);
//
//        Letters letters = new ThreeByThreeLetters();
//
//        LettersBuilder.build(letters.getT(Font.OAK, Direction.EAST), Direction.EAST, start);
        new TextGeneratorCommand();
        new SettingUIListener();
    }

    @Override
    public void onDisable() {

    }

    public static TextGenerator getInstance () {
        return INSTANCE;
    }

    public static String getMessageBeginning() {
        return "[TextGenerator] ";
    }

}
