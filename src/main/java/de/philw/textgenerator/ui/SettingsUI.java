package de.philw.textgenerator.ui;

import de.philw.textgenerator.utils.SkullData;
import de.philw.textgenerator.utils.UIUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SettingsUI {

    private Inventory inventory;
    private Player player;

    public SettingsUI(Player player) {
        inventory = Bukkit.createInventory(null, 54, ChatColor.GREEN + "TextGenerator Settings");
        this.player = player;

        addSize();
        player.openInventory(inventory);
    }

    public void addSize() {
        inventory.addItem(UIUtil.getSkullByString(SkullData.FONT_SIZE));
    }

}
