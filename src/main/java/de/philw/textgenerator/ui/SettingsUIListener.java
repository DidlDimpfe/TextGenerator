package de.philw.textgenerator.ui;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.font.CurrentEditedText;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.manager.MessagesManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SettingsUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.GREEN +
                "TextGenerator settings") && !ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.GREEN +
                "TextGenerator edit menu")) {
            return;
        }
        if (e.getCurrentItem() == null) return;
        Player player = (Player) e.getWhoClicked();
        e.setCancelled(true);
        switch (e.getRawSlot()) {
            case SettingsUI.FONT_SIZE_INDEX:
                new FontSizeSearchUI(player);
                break;
            case SettingsUI.LINE_SPACING_INDEX:
                new LineSpacingSearchUI(player);
                break;
            case SettingsUI.BLOCKS_INDEX:
                new BlockSearchUI(player);
                break;
            case SettingsUI.DRAG_TO_MOVE_INDEX:
                toggleDragToMove(player);
                break;
            case SettingsUI.PLACE_RANGE_INDEX:
                placeRangeIndexClicked(player);
                break;
            case SettingsUI.FONT_STYLE_INDEX:
                new FontStyleSearchUI(player);
                break;
            case SettingsUI.UNDERLINE_INDEX:
                toggleUnderline(player);
                break;
            case SettingsUI.FONT_INDEX:
                new FontSearchUI(player);
        }
    }

    private void toggleDragToMove(Player player) {
        if (!TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().containsKey(player.getUniqueId())) {
            boolean dragToMove = !ConfigManager.isDragToMove();
            ConfigManager.setDragToMove(dragToMove);
            new SettingsUI(player);
        } else {
            CurrentEditedText currentEditedText =
                    TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().get(player.getUniqueId());
            boolean dragToMove = !currentEditedText.getTextInstance().isDragToMove();
            currentEditedText.setDragToMoveTasks(dragToMove);
            player.sendMessage(MessagesManager.getMessage("changedValueOfCurrentText.success", "drag to move",
                    dragToMove));
            player.closeInventory();
        }
    }

    private void placeRangeIndexClicked(Player player) {
        if (!TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().containsKey(player.getUniqueId())) {
            new PlacementRangeSearchUI(player);
        } else {
            if (TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().get(player.getUniqueId()).getTextInstance().isDragToMove()) {
                new PlacementRangeSearchUI(player);
            } else {
                player.sendMessage(MessagesManager.getMessage("placementRangeIndexUIDenied"));
                player.closeInventory();
            }
        }
    }

    private void toggleUnderline(Player player) {
        if (!TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().containsKey(player.getUniqueId())) {
            boolean underline = !ConfigManager.isUnderline();
            ConfigManager.setUnderline(underline);
            new SettingsUI(player);
        } else {
            CurrentEditedText currentEditedText =
                    TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().get(player.getUniqueId());
            boolean underline = !currentEditedText.getTextInstance().isUnderline();
            currentEditedText.setUnderline(underline);
            player.sendMessage(MessagesManager.getMessage("changedValueOfCurrentText.success", "underline",
                    underline));
            player.closeInventory();
        }
    }

}
