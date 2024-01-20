package de.philw.textgenerator.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.*;

public class UIUtil {

    public static ItemStack getSkullByString(String string) {
        ItemStack skullItemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullItemMeta = (SkullMeta) skullItemStack.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), "Test");
        profile.getProperties().put("textures", new Property("textures", string));
        Field field;
        try {
            field = Objects.requireNonNull(skullItemMeta).getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(skullItemMeta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
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
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
        return newItems;
    }

    public static List<String> getLore(ChatColor chatColor, String lore) {
        List<String> realLore = new ArrayList<>();

        String[] words = lore.split(" ");
        String[] lines = new String[words.length % 4 == 0 ? words.length/4 : words.length/4+1];
        Arrays.fill(lines, String.valueOf(chatColor));

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int lineFromWord = i / 4;
            lines[lineFromWord] = lines[lineFromWord] + (i % 4 == 0 ? "" : " ") + word;
        }

        for (String line: lines) {
            line.substring(0, line.length()-1);
            realLore.add(line);
        }
        return realLore;
    }

}
