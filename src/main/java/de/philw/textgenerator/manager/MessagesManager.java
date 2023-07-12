package de.philw.textgenerator.manager;

import de.philw.textgenerator.TextGenerator;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.text.MessageFormat;
import java.util.Objects;

public class MessagesManager {

    private static YamlConfiguration messages;

    public static void setUpManager() {
        if (!TextGenerator.getInstance().getDataFolder().exists()) {
            TextGenerator.getInstance().getDataFolder().mkdir();
        }

        File file = new File(TextGenerator.getInstance().getDataFolder(), "messages.yml");

        if (!file.exists()) {
            TextGenerator.getInstance().saveResource("messages.yml", true);
        }
        messages = YamlConfiguration.loadConfiguration(file);
    }

    public static String getMessage(String path, Object... replace) {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(getMessageWithoutColorCodes(path,
                replace)));
    }

    private static String getMessageWithoutColorCodes(String path, Object... replace) {
        if (!messages.isSet(path)) return null;
        StringBuilder finalString = new StringBuilder();
        String[] partsOfMessage =
                MessageFormat.format(Objects.requireNonNull(messages.getString(path)), replace).split("♦");
        for (String onePart : partsOfMessage) {
            onePart.replace("♦", "");
            if (onePart.isEmpty()) continue;
            if (!messages.isSet(onePart)) continue;
            finalString.append(getMessageWithoutColorCodes(onePart) == null ? onePart :
                    getMessageWithoutColorCodes(onePart));
        }
        finalString.append(partsOfMessage[partsOfMessage.length - 1]);
        return finalString.toString();
    }
}
