package de.philw.textgenerator.ui;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.ui.value.PlacementRange;
import de.philw.textgenerator.utils.UIUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class PlacementRangeSearchUI extends SearchUI {


    public PlacementRangeSearchUI(Player player) {
        super(ChatColor.GREEN + "Select a placement range", "Search a placement range", player);
    }

    @Override
    public ArrayList<ItemStack> getSearchedItems(String searched) {
        ArrayList<ItemStack> searchedItems = new ArrayList<>();
        for (PlacementRange placementRange : PlacementRange.values()) {
            for (String searchKeyword : placementRange.getSearchKeywords()) {
                if (searchKeyword.contains(searched.toUpperCase())) {
                    searchedItems.add(getPlacementRangeItemStack(placementRange));
                    break;
                }
            }
        }
        return searchedItems;
    }

    @Override
    public void updateAllItems() {
        allItems.clear();
        for (PlacementRange placementRange : PlacementRange.values()) {
            allItems.add(getPlacementRangeItemStack(placementRange));
        }
    }

    private ItemStack getPlacementRangeItemStack(PlacementRange placementRange) {
        int size = placementRange.getNumber();
        ItemStack itemStack = UIUtil.getSkullByString(placementRange.getSkullData());
        ItemMeta itemMeta = Objects.requireNonNull(itemStack).getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + String.valueOf(size));
        if (TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().containsKey(uuid)) {
            if (size == TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().get(uuid).getTextInstance().getPlacementRange()) {
                itemMeta.setLore(UIUtil.getLore(ChatColor.RED, "You have already assigned this value"));
            } else {
                itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Click to change the placement rage of your" +
                        " current edited text to " + size));
            }
        } else {
            if (size == ConfigManager.getPlacementRange()) {
                itemMeta.setLore(UIUtil.getLore(ChatColor.RED, "You have already assigned this value"));
            } else {
                itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Click to change the default placement rage " +
                        "to " + size));
            }
        }
        itemMeta.setLocalizedName(SearchUI.PLACEMENT_RANGE_SEARCH_UI + ";" + size);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
