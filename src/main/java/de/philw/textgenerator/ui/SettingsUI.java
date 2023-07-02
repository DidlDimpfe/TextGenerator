package de.philw.textgenerator.ui;

import de.philw.textgenerator.utils.SkullData;
import de.philw.textgenerator.utils.UIUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SettingsUI {

    protected final static int FONT_SIZE_INDEX = 0;
    protected final static int LINE_SPACING_INDEX = 1;
    protected final static int BLOCKS_INDEX = 2;
    protected final static int DRAG_PREVIEW_INDEX = 3;


    private final Inventory inventory;
    private final Player player;

    public SettingsUI(Player player, String display) {
        inventory = Bukkit.createInventory(null, 54, ChatColor.GREEN + display);
        this.player = player;

        inventory.setItem(FONT_SIZE_INDEX, UIUtil.getSkullByString(SkullData.FONT_SIZE));
        inventory.setItem(LINE_SPACING_INDEX, UIUtil.getSkullByString(SkullData.LINE_SPACING));
        inventory.setItem(BLOCKS_INDEX, UIUtil.getSkullByString(SkullData.CHANGE_BLOCKS));
        inventory.setItem(DRAG_PREVIEW_INDEX, UIUtil.getSkullByString(SkullData.DRAG_PREVIEW));
        player.openInventory(inventory);
    }


}