package de.philw.textgenerator.holder;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.manager.GeneratedTextsManager;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class GeneratedTextsHolder extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "textgeneratorGeneratedTexts";
    }

    @Override
    public @NotNull String getAuthor() {
        return TextGenerator.getInstance().getDescription().getAuthors().get(0);
    }

    @Override
    public @NotNull String getVersion() {
        return TextGenerator.getInstance().getDescription().getVersion();
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        StringBuilder outPut = new StringBuilder(ChatColor.GREEN + "Generated texts: " + ChatColor.GRAY);
        for (String uuid: Objects.requireNonNull(GeneratedTextsManager.getGeneratedTexts().getConfigurationSection("")).getKeys(false)) {
            String text = GeneratedTextsManager.getGeneratedTexts().getString(uuid + ".text");
            outPut.append(text).append("; ");
        }
        if (outPut.toString().equals(ChatColor.GREEN + "Generated texts: " + ChatColor.GRAY)) {
            outPut.append("You haven't generated any text's yet");
        }
        return outPut.substring(0, outPut.length()-2);
    }
}
