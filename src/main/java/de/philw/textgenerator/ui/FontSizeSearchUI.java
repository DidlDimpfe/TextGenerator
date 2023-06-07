package de.philw.textgenerator.ui;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.ui.value.FontSize;
import de.philw.textgenerator.utils.UIUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class FontSizeSearchUI extends SearchUI {
    public FontSizeSearchUI(Player player) {
        super(ChatColor.GREEN + "Select Size", "Search a font size...", player);
    }

    @Override
    public ArrayList<ItemStack> getSearchedItems(String searched) {
        ArrayList<ItemStack> searchedItems = new ArrayList<>();
        for (FontSize fontSize : FontSize.values()) {
            for (String searchKeyword : fontSize.getSearchKeywords()) {
                if (searchKeyword.contains(searched.toUpperCase())) {
                    searchedItems.add(getFontSizeItemStack(fontSize));
                    break;
                }
            }
        }
        return searchedItems;
    }

    @Override
    public void updateAllItems() {
        allItems.clear();
        for (FontSize fontSize: FontSize.values()) {
            allItems.add(getFontSizeItemStack(fontSize));
        }
    }

    private ItemStack getFontSizeItemStack(FontSize fontSize) {
        int size = fontSize.getNumber();
        ItemStack itemStack = UIUtil.getSkullByString(fontSize.getSkullData());
        ItemMeta itemMeta = Objects.requireNonNull(itemStack).getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + String.valueOf(size));
        if (TextGenerator.getInstance().getCurrentEdited() == null) {
            itemMeta.setLore(Collections.singletonList(ChatColor.YELLOW + "Click to change the default size to " + size));
        } else {
            itemMeta.setLore(Collections.singletonList(ChatColor.YELLOW + "Click to edit " + TextGenerator.getInstance().getCurrentEdited().getText() + " to size " + size));
        }
        if (size == ConfigManager.getFontSize()) {
            itemMeta.setDisplayName(ChatColor.RED + String.valueOf(size));
            itemMeta.setLore(Collections.singletonList(ChatColor.GRAY + "You have already assigned this value"));
        }
        itemMeta.setLocalizedName(String.valueOf(size));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
