package de.philw.textgenerator.ui;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.philw.textgenerator.utils.SkullData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class SettingsUI {

    private Inventory inventory;
    private Player player;

    public SettingsUI(Player player) {
        inventory = Bukkit.createInventory(null, 45);
        this.player = player;

        addSize();
        player.openInventory(inventory);
    }

    public void addSize() {
        inventory.addItem(getPlayerHeadByString(SkullData.fontSize));

    }

    public ItemStack getPlayerHeadByString(String string) {
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

}
