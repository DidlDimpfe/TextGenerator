package de.philw.textgenerator.ui;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.utils.SkullData;
import de.philw.textgenerator.utils.UIUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class SettingsUI {

    protected final static int FONT_SIZE_INDEX = 0;
    protected final static int LINE_SPACING_INDEX = 1;
    protected final static int BLOCKS_INDEX = 2;
    protected final static int DRAG_TO_MOVE_INDEX = 3;
    protected final static int PLACE_RANGE_INDEX = 4;

    private final Player player;

    public SettingsUI(Player player) {
        this.player = player;
        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.GREEN + (isEditMode() ? "TextGenerator edit menu" : "TextGenerator settings"));

        inventory.setItem(FONT_SIZE_INDEX, getFontSizeItemStack());
        inventory.setItem(LINE_SPACING_INDEX, getLineSpacingItemStack());
        inventory.setItem(BLOCKS_INDEX, getChangeBlocksItemStack());
        inventory.setItem(DRAG_TO_MOVE_INDEX, getDragToMoveItemStack());
        inventory.setItem(PLACE_RANGE_INDEX, getPlacementRangeItemStack());
        player.openInventory(inventory);
    }

    private boolean isEditMode() {
        return TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().containsKey(player.getUniqueId());
    }

    private ItemStack getFontSizeItemStack() {
        ItemStack itemStack = UIUtil.getSkullByString(SkullData.FONT_SIZE);
        ItemMeta itemMeta = Objects.requireNonNull(itemStack).getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + "Font size");
        if (!isEditMode()) {
            itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Change the default size from your texts"));
        } else {
            itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Change the size of your current edited text"));
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack getLineSpacingItemStack() {
        ItemStack itemStack = UIUtil.getSkullByString(SkullData.LINE_SPACING);
        ItemMeta itemMeta = Objects.requireNonNull(itemStack).getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + "Line spacing");
        if (!isEditMode()) {
            itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Change the default distance between lines"));
        } else {
            itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Change the distance between lines of your current edited text"));
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack getChangeBlocksItemStack() {
        ItemStack itemStack = UIUtil.getSkullByString(SkullData.CHANGE_BLOCKS);
        ItemMeta itemMeta = Objects.requireNonNull(itemStack).getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + "Block");
        if (!isEditMode()) {
            itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Change the default block your texts consist of"));
        } else {
            itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Change the block of your current edited text consists of"));
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack getDragToMoveItemStack() {
        ItemStack itemStack = UIUtil.getSkullByString(SkullData.DRAG_TO_MOVE);
        ItemMeta itemMeta = Objects.requireNonNull(itemStack).getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + "Drag to move");
        if (isEditMode()) {
            if (TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().get(player.getUniqueId()).getTextInstance().isDragToMove()) {
                itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Stop your current edited text follow your view"));
            } else {
                itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Make your current edited text follow your view"));
            }
        } else {
            if (ConfigManager.isDragToMove(false)) {
                itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Stop default texts follow your view"));
            } else {
                itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Make default texts follow your view"));
            }
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack getPlacementRangeItemStack() {
        ItemStack itemStack = UIUtil.getSkullByString(SkullData.PLACE_RANGE);
        ItemMeta itemMeta = Objects.requireNonNull(itemStack).getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + "Placement range");
        if (!isEditMode()) {
            itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Change the default distance between the player and the text"));
        } else {
            itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Change the distance between you and your current edited text"));
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }


}