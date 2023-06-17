package de.philw.textgenerator.ui;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.ui.value.Block;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class BlockSearchUI extends SearchUI {

    public BlockSearchUI(Player player) {
        super(ChatColor.GREEN + "Select a Block for the text", "Search a block...", player);
    }

    @Override
    public ArrayList<ItemStack> getSearchedItems(String searched) {
        ArrayList<ItemStack> searchedItems = new ArrayList<>();
        for (Block block : Block.values()) {
            if (block.name().contains(searched.toUpperCase())) {
                searchedItems.add(getBlockItemStack(block));
            }
        }
        return searchedItems;
    }

    @Override
    public void updateAllItems() {
        allItems.clear();
        for (Block block: Block.values()) {
            allItems.add(getBlockItemStack(block));
        }
    }

    private ItemStack getBlockItemStack(Block block) {
        ItemStack itemStack = new ItemStack(Material.valueOf(block.name()));
        ItemMeta itemMeta = Objects.requireNonNull(itemStack).getItemMeta();
        String display = block.getDisplay();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + display);
        if (TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditTexts().containsKey(uuid)) {
            if (block == TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditTexts().get(uuid).getTextInstance().getBlock()) {
                itemMeta.setDisplayName(ChatColor.RED + display);
                itemMeta.setLore(Collections.singletonList(ChatColor.GRAY + "You have already assigned this value"));
                itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            } else {
                itemMeta.setLore(Collections.singletonList(ChatColor.YELLOW + "Click to change the block from you current edited text to " + display));
            }
        } else {
            if (block == ConfigManager.getBlock()) {
                itemMeta.setDisplayName(ChatColor.RED + display);
                itemMeta.setLore(Collections.singletonList(ChatColor.GRAY + "You have already assigned this value"));
                itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            } else {
                itemMeta.setLore(Collections.singletonList(ChatColor.YELLOW + "Click to change the default Block to " + display));
            }
        }
        itemMeta.setLocalizedName(block.name());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
