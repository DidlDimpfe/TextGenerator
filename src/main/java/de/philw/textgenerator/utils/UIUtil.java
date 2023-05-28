package de.philw.textgenerator.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class UIUtil {

    public static ItemStack getSkullByString(String string) {
        ItemStack skullItemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullItemMeta = (SkullMeta) skullItemStack.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", string));
        Field field;
        try {
            field = skullItemMeta.getClass().getDeclaredField("profile");
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
        return true;
    }

    public static int getMaxPage(List<ItemStack> items, int spaces) {
        return 0;
    }

}
