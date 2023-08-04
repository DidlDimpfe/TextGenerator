package de.philw.textgenerator.manager;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.ui.value.Block;
import de.philw.textgenerator.utils.Direction;
import de.philw.textgenerator.utils.FileUtil;
import de.philw.textgenerator.utils.TextInstance;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class GeneratedTextsManager {

    private static File file;
    private static YamlConfiguration generatedTexts;

    public static void setUpManager() {
        if (!TextGenerator.getInstance().getDataFolder().exists()) {
            TextGenerator.getInstance().getDataFolder().mkdir();
        }

        file = new File(TextGenerator.getInstance().getDataFolder(), "generatedTexts.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(TextGenerator.getMessageBeginning() + "Could not load file generatedTexts.yml");
            }
        }

        generatedTexts = YamlConfiguration.loadConfiguration(file);
    }

    private static void saveCraftingTables() {
        try {
            generatedTexts.save(file);
        } catch (IOException e) {
            System.err.println(TextGenerator.getMessageBeginning() + "Could not save generatedTexts.yml");
        }
    }

    public static void saveTextInstance(TextInstance textInstance) {
        UUID savedTextUUID;
        if (textInstance.getUuid() == null) {
            savedTextUUID = UUID.randomUUID();
        } else {
            savedTextUUID = textInstance.getUuid();
        }
        generatedTexts.set(savedTextUUID + ".text", textInstance.getText());
        generatedTexts.set(savedTextUUID + ".middleLocation", getSavedLocation(textInstance.getMiddleLocation()));
        generatedTexts.set(savedTextUUID + ".topLeftLocation", getSavedLocation(textInstance.getTopLeftLocation()));
        generatedTexts.set(savedTextUUID + ".bottomRightLocation",
                getSavedLocation(textInstance.getBottomRightLocation()));
        generatedTexts.set(savedTextUUID + ".direction", textInstance.getDirection().toString());
        generatedTexts.set(savedTextUUID + ".block", textInstance.getBlock().toString());
        generatedTexts.set(savedTextUUID + ".fontName", textInstance.getFontName());
        generatedTexts.set(savedTextUUID + ".fontSize", textInstance.getFontSize());
        generatedTexts.set(savedTextUUID + ".fontStyle",
                FileUtil.fromFontStyleIntToString(textInstance.getFontStyle()));
        generatedTexts.set(savedTextUUID + ".underline", textInstance.isUnderline());
        generatedTexts.set(savedTextUUID + ".lineSpacing", textInstance.getLineSpacing());
        saveCraftingTables();
    }

    public static TextInstance getTextInstance(UUID uuid) {
        TextInstance textInstance = TextInstance.getTextInstanceBuilder()
                .withUuid(uuid)
                .withText(generatedTexts.getString(uuid + ".text"))
                .withMiddleLocation(getLocationFromSavedString(Objects.requireNonNull(generatedTexts.getString(uuid + ".middleLocation"))))
                .withTopLeftLocation(getLocationFromSavedString(Objects.requireNonNull(generatedTexts.getString(uuid + ".topLeftLocation"))))
                .withBottomRightLocation(getLocationFromSavedString(Objects.requireNonNull(generatedTexts.getString(uuid + ".bottomRightLocation"))))
                .withDirection(Direction.valueOf(generatedTexts.getString(uuid + ".direction")))
                .withBlock(Block.valueOf(generatedTexts.getString(uuid + ".block")))
                .withFontName(generatedTexts.getString(uuid + ".fontName"))
                .withFontSize(generatedTexts.getInt(uuid + ".fontSize"))
                .withFontStyle(FileUtil.fromFontStyleStringToInt(Objects.requireNonNull(generatedTexts.getString(uuid + ".fontStyle"))))
                .withUnderline(generatedTexts.getBoolean(uuid + ".underline"))
                .withLineSpacing(generatedTexts.getInt(uuid + ".lineSpacing"))
                .build();
        textInstance.setPreviousTextInstance(textInstance.clone());
        return textInstance;
    }

    public static Set<UUID> getUUIDs() {
        Set<String> inStrings = Objects.requireNonNull(generatedTexts.getConfigurationSection("")).getKeys(false);
        Set<UUID> inUUID = new HashSet<>();
        for (String string : inStrings) {
            inUUID.add(UUID.fromString(string));
        }
        return inUUID;
    }

    public static void removeTextInstance(UUID uuid) {
        generatedTexts.set(uuid + ".text", null);
        generatedTexts.set(uuid + ".middleLocation", null);
        generatedTexts.set(uuid + ".topLeftLocation", null);
        generatedTexts.set(uuid + ".bottomRightLocation", null);
        generatedTexts.set(uuid + ".direction", null);
        generatedTexts.set(uuid + ".block", null);
        generatedTexts.set(uuid + ".fontName", null);
        generatedTexts.set(uuid + ".fontSize", null);
        generatedTexts.set(uuid + ".fontStyle", null);
        generatedTexts.set(uuid + ".underline", null);
        generatedTexts.set(uuid + ".lineSpacing", null);
        generatedTexts.set(uuid.toString(), null);
        saveCraftingTables();
    }

    public static YamlConfiguration getGeneratedTexts() {
        return generatedTexts;
    }

    /**
     * This method converts a location to a String how the location is stored in the generatedTexts.yml file.
     *
     * @param location The location you want to convert
     */
    private static String getSavedLocation(Location location) {
        return Objects.requireNonNull(location.getWorld()).getName() + "," + (int) location.getX() + "," + (int) location.getY() + "," + (int) location.getZ();
    }

    /**
     * This method converts a String how the location is stored in the generatedTexts.yml file to a real location.
     *
     * @param string The string you want to convert
     */

    private static Location getLocationFromSavedString(String string) {
        String[] info = string.split(",");
        World world = Bukkit.getWorld(info[0]);
        int x = Integer.parseInt(info[1]);
        int y = Integer.parseInt(info[2]);
        int z = Integer.parseInt(info[3]);
        return new Location(world, x, y, z);
    }

}