package de.philw.textgenerator.ui;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.ui.value.FontStyle;
import de.philw.textgenerator.utils.FileUtil;
import de.philw.textgenerator.utils.UIUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class FontStyleSearchUI extends SearchUI {

    public FontStyleSearchUI(Player player) {
        super(ChatColor.GREEN + "Select a font style", "Search a font style...", player);
    }

    @Override
    public ArrayList<ItemStack> getSearchedItems(String searched) {
        ArrayList<ItemStack> searchedItems = new ArrayList<>();
        for (FontStyle fontStyle : FontStyle.values()) {
            if (fontStyle.getDisplay().toLowerCase().contains(searched.toLowerCase())) {
                searchedItems.add(getFontStyleItemStack(fontStyle));
            }
        }
        return searchedItems;
    }

    @Override
    public void updateAllItems() {
        allItems.clear();
        for (FontStyle fontStyle : FontStyle.values()) {
            allItems.add(getFontStyleItemStack(fontStyle));
        }
    }

    private ItemStack getFontStyleItemStack(FontStyle fontStyle) {
        String fontStyleDisplay = fontStyle.getDisplay();
        ItemStack itemStack = UIUtil.getSkullByString(fontStyle.getSkullData());
        ItemMeta itemMeta = Objects.requireNonNull(itemStack).getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + fontStyleDisplay);
        int fontStyleInNumber = FileUtil.fromFontStyleStringToInt(fontStyle.getSavedInFile());
        if (TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().containsKey(uuid)) {
            if (fontStyleInNumber == TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().get(uuid).getTextInstance().getFontStyle()) {
                itemMeta.setLore(UIUtil.getLore(ChatColor.RED, "You have already assigned this value"));
            } else {
                itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Click to change the font style of your" +
                        " current edited text to " + fontStyleDisplay));
            }
        } else {
            if (fontStyleInNumber == ConfigManager.getFontStyle()) {
                itemMeta.setLore(UIUtil.getLore(ChatColor.RED, "You have already assigned this value"));
            } else {
                itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Click to change the default font style " +
                        "to " + fontStyleDisplay));
            }
        }
        itemMeta.setLocalizedName(SearchUI.FONT_STYLE_SEARCH_UI + ";" + fontStyle.getSavedInFile());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
