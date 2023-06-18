package de.philw.textgenerator;

import de.philw.textgenerator.command.TextGeneratorCommand;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.ui.*;
import de.philw.textgenerator.utils.TextInstance;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TextGenerator extends JavaPlugin {

    private static TextGenerator INSTANCE;
    private SearchUIListener searchUIListener;
    private TextGeneratorCommand textGeneratorCommand;

    @Override
    public void onEnable() {
        INSTANCE = this;
        ConfigManager.setUpConfig();
//        Location start = new Location(Bukkit.getWorld("world"), -59, 73, 98);
//
//        Letters letters = new ThreeByThreeLetters();
//
//        LettersBuilder.build(letters.getT(Font.OAK, Direction.EAST), Direction.EAST, start);
        textGeneratorCommand = new TextGeneratorCommand();
        searchUIListener = new SearchUIListener();
        Bukkit.getPluginManager().registerEvents(new SettingsUIListener(), this);
        Bukkit.getPluginManager().registerEvents(searchUIListener, this);
    }

    @Override
    public void onDisable() {

    }

    public static TextGenerator getInstance() {
        return INSTANCE;
    }

    public static String getMessageBeginning() {
        return "[TextGenerator] ";
    }

    public SearchUIListener getSearchUIListener() {
        return searchUIListener;
    }

    public TextGeneratorCommand getTextGeneratorCommand() {
        return textGeneratorCommand;
    }
}
