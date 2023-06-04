package de.philw.textgenerator.ui;

import de.philw.textgenerator.ui.value.FontSize;
import de.philw.textgenerator.utils.UIUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class SizeSearchUI extends SearchUI {
    public SizeSearchUI(Player player) {
        super(ChatColor.GREEN + "Select Size", "Search a font size...", player);
        player.openInventory(inventory);
    }

    @Override
    public ArrayList<ItemStack> getSearchedItems(String searched) {
        ArrayList<ItemStack> searchedItems = new ArrayList<>();
        for (FontSize fontSize : FontSize.values()) {
            for (String searchKeyword : fontSize.getSearchKeywords()) {
                if (searchKeyword.contains(searched.toUpperCase())) {
                    searchedItems.add(UIUtil.getFontSizeItemStack(fontSize));
                    break;
                }
            }
        }
        return searchedItems;
    }

    @Override
    public void addAllItems() {
        allItems.clear();
        for (FontSize fontSize: FontSize.values()) {
            allItems.add(UIUtil.getFontSizeItemStack(fontSize));
        }
    }
}
