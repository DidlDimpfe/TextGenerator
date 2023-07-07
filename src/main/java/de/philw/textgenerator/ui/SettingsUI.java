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

import java.util.List;
import java.util.Objects;

public class SettingsUI {

    protected final static int FONT_SIZE_INDEX = 0;
    protected final static int LINE_SPACING_INDEX = 1;
    protected final static int BLOCKS_INDEX = 2;
    protected final static int DRAG_TO_MOVE_INDEX = 3;
    protected final static int PLACE_RANGE_INDEX = 4;
    protected final static int FONT_STYLE_INDEX = 5;
    protected final static int UNDERLINE_INDEX = 6;
    protected final static int FONT_INDEX = 7;

    private final Player player;

    public SettingsUI(Player player) {
        this.player = player;
        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.GREEN + (isEditMode() ? "TextGenerator edit menu" : "TextGenerator settings"));

        inventory.setItem(FONT_SIZE_INDEX, getFontSizeItemStack());
        inventory.setItem(LINE_SPACING_INDEX, getLineSpacingItemStack());
        inventory.setItem(BLOCKS_INDEX, getChangeBlocksItemStack());
        inventory.setItem(DRAG_TO_MOVE_INDEX, getDragToMoveItemStack());
        inventory.setItem(PLACE_RANGE_INDEX, getPlacementRangeItemStack());
        inventory.setItem(FONT_STYLE_INDEX, getFontStyleItemStack());
        inventory.setItem(UNDERLINE_INDEX, getUnderlineItemStack());
        inventory.setItem(FONT_INDEX, getFontItemStack());
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
        List<String> carefulLore = UIUtil.getLore(ChatColor.RED, "Careful: Enabling this feature with a text containing many blocks, this can cause issues!");
        carefulLore.add("");
        if (isEditMode()) {
            if (TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().get(player.getUniqueId()).getTextInstance().isDragToMove()) {
                itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Click to stop your current edited text follow your view"));
            } else {
                carefulLore.addAll(UIUtil.getLore(ChatColor.YELLOW, "Click to make your current edited text follow your view"));
                itemMeta.setLore(carefulLore);
            }
        } else {
            if (ConfigManager.isDragToMove()) {
                itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Click to stop default texts follow your view"));
            } else {
                carefulLore.addAll(UIUtil.getLore(ChatColor.YELLOW, "Click to make default texts follow your view"));
                itemMeta.setLore(carefulLore);
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

    private ItemStack getFontStyleItemStack() {
        ItemStack itemStack = UIUtil.getSkullByString(SkullData.FONT_STYLE);
        ItemMeta itemMeta = Objects.requireNonNull(itemStack).getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + "Font style");
        if (!isEditMode()) {
            itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Change the default font style"));
        } else {
            itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Change the font style of your current edited text"));
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack getUnderlineItemStack() {
        ItemStack itemStack = UIUtil.getSkullByString(SkullData.UNDERLINE);
        ItemMeta itemMeta = Objects.requireNonNull(itemStack).getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + "Underline");
        if (isEditMode()) {
            if (TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().get(player.getUniqueId()).getTextInstance().isUnderline()) {
                itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Click to remove the underline of your current edited text"));
            } else {
                itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Click to add underline to your current edited text"));
            }
        } else {
            if (ConfigManager.isUnderline()) {
                itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Click to remove underline from your default texts"));
            } else {
                itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Click to add underline from your default texts"));
            }
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack getFontItemStack() {
        ItemStack itemStack = UIUtil.getSkullByString(SkullData.FONT);
        ItemMeta itemMeta = Objects.requireNonNull(itemStack).getItemMeta();
        Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + "Font");
        if (!isEditMode()) {
            itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Change the default font"));
        } else {
            itemMeta.setLore(UIUtil.getLore(ChatColor.YELLOW, "Change the font of your current edited text"));
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}