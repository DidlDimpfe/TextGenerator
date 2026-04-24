package de.philw.textgenerator.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.URL;
import java.util.*;

public class UIUtil {

    public static ItemStack getSkullByString(String string) {
        ItemStack skullItemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullItemMeta = (SkullMeta) skullItemStack.getItemMeta();
        if (skullItemMeta == null) return skullItemStack;

        PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID(), "CustomHead");
        PlayerTextures textures = profile.getTextures();

        try {
            String decoded = new String(Base64.getDecoder().decode(string));
            String urlString = decoded.split("\"url\":\"")[1].split("\"")[0];
            textures.setSkin(new URL(urlString));
        } catch (Exception e) {
            try {
                textures.setSkin(new URL(string));
            } catch (Exception ignored) {}
        }

        profile.setTextures(textures);
        skullItemMeta.setOwnerProfile(profile);

        skullItemStack.setItemMeta(skullItemMeta);
        return skullItemStack;
    }

    public static boolean isPageValid(List<ItemStack> items, int page, int spaces) {
        if (page <= 0) return false;
        int upperBound = page * spaces;
        int lowerBound = upperBound - spaces;
        return items.size() > lowerBound;
    }

    public static int getMaxPage(List<ItemStack> items, int spaces) {
        return items.size() / spaces + 1;
    }

    public static List<ItemStack> getPageItems(List<ItemStack> itemStacks, int page, int spaces) {
        int upperBound = page * spaces;
        int lowerBound = upperBound - spaces;
        List<ItemStack> newItems = new ArrayList<>();
        for (int index = lowerBound; index < upperBound; index++) {
            try {
                newItems.add(itemStacks.get(index));
            } catch (IndexOutOfBoundsException ignored) {}
        }
        return newItems;
    }

    public static List<String> getLore(ChatColor chatColor, String lore) {
        List<String> realLore = new ArrayList<>();
        String[] words = lore.split(" ");
        int lineCount = words.length % 4 == 0 ? words.length / 4 : words.length / 4 + 1;
        String[] lines = new String[lineCount];
        Arrays.fill(lines, String.valueOf(chatColor));

        for (int i = 0; i < words.length; i++) {
            int lineFromWord = i / 4;
            lines[lineFromWord] = lines[lineFromWord] + (i % 4 == 0 ? "" : " ") + words[i];
        }

        Collections.addAll(realLore, lines);
        return realLore;
    }
}