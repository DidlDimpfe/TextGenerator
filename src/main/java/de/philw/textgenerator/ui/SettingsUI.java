package de.philw.textgenerator.ui;

import de.philw.textgenerator.utils.SkullData;
import de.philw.textgenerator.utils.UIUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SettingsUI {

    private final Inventory inventory;
    private final Player player;

    public SettingsUI(Player player) {
        inventory = Bukkit.createInventory(null, 54, ChatColor.GREEN + "TextGenerator Settings");
        this.player = player;

        inventory.addItem(UIUtil.getSkullByString(SkullData.FONT_SIZE));
        inventory.addItem(UIUtil.getSkullByString(SkullData.LINE_SPACING));
        inventory.addItem(UIUtil.getSkullByString(SkullData.BLOCKS));
        player.openInventory(inventory);
    }


}