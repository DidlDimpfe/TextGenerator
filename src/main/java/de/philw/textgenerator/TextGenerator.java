package de.philw.textgenerator;

import de.philw.textgenerator.command.TextGeneratorCommand;
import de.philw.textgenerator.letters.*;
import de.philw.textgenerator.utils.Direction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public final class TextGenerator extends JavaPlugin {

    private static TextGenerator INSTANCE;

    @Override
    public void onEnable() {
//        Location start = new Location(Bukkit.getWorld("world"), -59, 73, 98);
//
//        Letters letters = new ThreeByThreeLetters();
//
//        LettersBuilder.build(letters.getT(Font.OAK, Direction.EAST), Direction.EAST, start);
        new TextGeneratorCommand();
    }

    @Override
    public void onDisable() {

    }

    public static TextGenerator getInstance () {
        if (INSTANCE == null) {
            INSTANCE = new TextGenerator();
        }
        return INSTANCE;
    }
}
