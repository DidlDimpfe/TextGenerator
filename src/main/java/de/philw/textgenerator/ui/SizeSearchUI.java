package de.philw.textgenerator.ui;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class SizeSearchUI extends SearchUI {


    public SizeSearchUI(Player player) {
        super(ChatColor.GREEN + "Select Size", new ArrayList<>());
        player.openInventory(inventory);
    }
}
