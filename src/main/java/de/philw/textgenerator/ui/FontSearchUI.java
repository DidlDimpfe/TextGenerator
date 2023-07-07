package de.philw.textgenerator.ui;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.ui.value.Font;
import de.philw.textgenerator.utils.UIUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class FontSearchUI extends SearchUI {


    public FontSearchUI(Player player) {
        super(ChatColor.GREEN + "Select a font", "Search a font style...", player);
    }

    @Override
    public ArrayList<ItemStack> getSearchedItems(String searched) {
        ArrayList<ItemStack> searchedItems = new ArrayList<>();
        for (Font font : Font.values()) {
            if (font.getFontName().toLowerCase().contains(searched.toLowerCase())) {
                searchedItems.add(getFontItemStack(font));
            }
        }
        return searchedItems;
    }

    @Override
    public void updateAllItems() {
        allItems.clear();
        for (Font font : Font.values()) {
            allItems.add(getFontItemStack(font));
        }
    }

    private ItemStack getFontItemStack(Font font) {
        String fontName = font.getFontName();
        ItemStack itemStack = UIUtil.getSkullByString(font.getSkullData());
        ItemMeta itemMeta = Objects.requireNonNull(itemStack).getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + fontName);
        if (TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().containsKey(uuid)) {
            if (Objects.equals(fontName, TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().get(uuid).getTextInstance().getFontName())) {
                itemMeta.setLore(UIUtil.getLore(ChatColor.RED, "You have already assigned this value"));
            } else {
                itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Click to change the font of your" +
                        " current edited text to " + fontName));
            }
        } else {
            if (Objects.equals(fontName, ConfigManager.getFontName())) {
                itemMeta.setLore(UIUtil.getLore(ChatColor.RED, "You have already assigned this value"));
            } else {
                itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Click to change the default font " +
                        "to " + fontName));
            }
        }
        itemMeta.setLocalizedName(SearchUI.FONT_SEARCH_UI + ";" + fontName);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
