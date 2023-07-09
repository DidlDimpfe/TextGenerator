package de.philw.textgenerator;

import de.philw.textgenerator.command.TextGeneratorCommand;
import de.philw.textgenerator.font.CurrentEditedText;
import de.philw.textgenerator.listener.BlockDestroyListener;
import de.philw.textgenerator.listener.LeaveListener;
import de.philw.textgenerator.listener.NoMoveWhileGenerateListener;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.manager.GeneratedTextsManager;
import de.philw.textgenerator.manager.MessagesManager;
import de.philw.textgenerator.ui.SearchUIListener;
import de.philw.textgenerator.ui.SettingsUIListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TextGenerator extends JavaPlugin {

    private static TextGenerator INSTANCE;
    private SearchUIListener searchUIListener;
    private TextGeneratorCommand textGeneratorCommand;

    @Override
    public void onEnable() {
        new Metrics(this, 19024);

        INSTANCE = this;

        registerEventsAndCommandAndManager();
    }

    @Override
    public void onDisable() {
        for (CurrentEditedText currentEditedText: textGeneratorCommand.getCurrentEditedTexts().values()) {
            currentEditedText.save();
            currentEditedText.removeMetaDataFromPreviewedBlocks();
        }
    }

    private void registerEventsAndCommandAndManager() {
        // Manager
        ConfigManager.setUpConfig();
        GeneratedTextsManager.setUpManager();
        MessagesManager.setUpManager();

        // Command
        textGeneratorCommand = new TextGeneratorCommand();

        // Events
        searchUIListener = new SearchUIListener();

        Bukkit.getPluginManager().registerEvents(searchUIListener, this);

        Bukkit.getPluginManager().registerEvents(new LeaveListener(), this);
        Bukkit.getPluginManager().registerEvents(new SettingsUIListener(), this);
        Bukkit.getPluginManager().registerEvents(new NoMoveWhileGenerateListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockDestroyListener(), this);
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
