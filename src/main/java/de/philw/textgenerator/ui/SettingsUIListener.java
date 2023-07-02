package de.philw.textgenerator.ui;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.letters.CurrentEditedText;
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
            case SettingsUI.DRAG_PREVIEW_INDEX:
                toggleDragToMove(player);
                break;
        }
    }

    private void toggleDragToMove(Player player) {
        if (!TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().containsKey(player.getUniqueId())) {
            boolean dragToMove = !ConfigManager.isDragToMove(false);
            ConfigManager.setDragToMove(dragToMove);
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

}
