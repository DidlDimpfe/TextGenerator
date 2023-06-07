package de.philw.textgenerator.ui;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.ui.value.LineSpacing;
import de.philw.textgenerator.utils.UIUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class LineSpacingSearchUI extends SearchUI {


    public LineSpacingSearchUI(Player player) {
        super(ChatColor.GREEN + "Select line spacing", "Search a line spacing...", player);
    }

    @Override
    public ArrayList<ItemStack> getSearchedItems(String searched) {
        ArrayList<ItemStack> searchedItems = new ArrayList<>();
        for (LineSpacing lineSpacing : LineSpacing.values()) {
            for (String searchKeyword : lineSpacing.getSearchKeywords()) {
                if (searchKeyword.contains(searched.toUpperCase())) {
                    searchedItems.add(getLineSpacingItemStack(lineSpacing));
                    break;
                }
            }
        }
        return searchedItems;
    }

    @Override
    public void updateAllItems() {
        allItems.clear();
        for (LineSpacing lineSpacing: LineSpacing.values()) {
            allItems.add(getLineSpacingItemStack(lineSpacing));
        }
    }

    private static ItemStack getLineSpacingItemStack(LineSpacing lineSpacing) {
        int lineSpacingNumber = lineSpacing.getNumber();
        ItemStack itemStack = UIUtil.getSkullByString(lineSpacing.getSkullData());
        ItemMeta itemMeta = Objects.requireNonNull(itemStack).getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + String.valueOf(lineSpacingNumber));
        if (TextGenerator.getInstance().getCurrentEdited() == null) {
            itemMeta.setLore(Collections.singletonList(ChatColor.YELLOW + "Click to change the default lineSpacing to " + lineSpacingNumber));
        } else {
            itemMeta.setLore(Collections.singletonList(ChatColor.YELLOW + "Click to edit " + TextGenerator.getInstance().getCurrentEdited().getText() + " to lineSpacing " + lineSpacingNumber));
        }
        if (lineSpacingNumber == ConfigManager.getLineSpacing()) {
            itemMeta.setDisplayName(ChatColor.RED + String.valueOf(lineSpacingNumber));
            itemMeta.setLore(Collections.singletonList(ChatColor.GRAY + "You have already assigned this value"));
        }
        itemMeta.setLocalizedName(String.valueOf(lineSpacingNumber));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
