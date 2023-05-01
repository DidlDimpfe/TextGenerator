package de.philw.textgenerator;

import de.philw.textgenerator.letters.Letters;
import de.philw.textgenerator.letters.LettersBuilder;
import de.philw.textgenerator.letters.TwoTimesTwoLetters;
import de.philw.textgenerator.utils.Direction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public final class TextGenerator extends JavaPlugin {

    /**
     * Bukkit.getWorld("world").getBlockAt(-57, 66, 100).setType(Material.OAK_STAIRS);
     * Block block = Bukkit.getWorld("world").getBlockAt(-57, 66, 100);
     * Stairs directional = (Stairs) block.getBlockData();
     * directional.setHalf(Bisected.Half.TOP);
     * block.setBlockData(directional);
     */

    private static TextGenerator INSTANCE;

    public TextGenerator() {}

    @Override
    public void onEnable() {

        Location start = new Location(Bukkit.getWorld("world"), -59, 73, 98);

        Letters letters = new TwoTimesTwoLetters();

        LettersBuilder.build(letters.getS("cobblestone", Direction.EAST), Direction.EAST, start);
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
