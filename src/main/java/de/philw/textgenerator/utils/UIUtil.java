package de.philw.textgenerator.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.ui.value.FontSize;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.*;

public class UIUtil {

    public static ItemStack getSizeItemStack(int size, String skullValue, String sizeAsString) {
        ItemStack itemStack = getSkullByString(skullValue);
        ItemMeta itemMeta = Objects.requireNonNull(itemStack).getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + String.valueOf(size));
        if (TextGenerator.getInstance().getCurrentEdited() == null) {
            itemMeta.setLore(Collections.singletonList(ChatColor.YELLOW + "Click to change the default size to " + size));
        } else {
            itemMeta.setLore(Collections.singletonList(ChatColor.YELLOW + "Click to edit " + TextGenerator.getInstance().getCurrentEdited().getText() + " to size " + size));
        }
        if (size == ConfigManager.getFontSize()) {
            itemMeta.setDisplayName(ChatColor.RED + String.valueOf(size));
            itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            itemMeta.setLore(Collections.singletonList(ChatColor.GRAY + "You have already assigned this value"));
        }
        itemMeta.setLocalizedName(sizeAsString);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack getSkullByString(String string) {
        ItemStack skullItemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullItemMeta = (SkullMeta) skullItemStack.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
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

    public static ArrayList<ItemStack> getAllItemStacksFromEnum(Class<? extends Enum<?>> enumValue) {
        ArrayList<ItemStack> items = new ArrayList<>();
        String[] values = Arrays.stream(enumValue.getEnumConstants()).map(Enum::name).toArray(String[]::new);
        if (enumValue == FontSize.class) {
            for (String string: values) {
                items.add(FontSize.valueOf(string).getItemStack());
            }
        }
        return items;
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

}
