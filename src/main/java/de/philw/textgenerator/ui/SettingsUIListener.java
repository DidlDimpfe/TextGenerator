package de.philw.textgenerator.ui;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.letters.CurrentEditedText;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SettingsUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.GREEN +
                "TextGenerator Settings")) {
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
                toggleDragPreview(player);
                break;
        }
    }

    private void toggleDragPreview(Player player) {
        if (!TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditTexts().containsKey(player.getUniqueId())) {
            boolean dragPreview = !ConfigManager.isDragPreview(false);
            ConfigManager.setDragPreview(dragPreview);
            player.sendMessage(Messages.defaultDragPreviewChangeSuccess(dragPreview));
        } else {
            CurrentEditedText currentEditedText = TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditTexts().get(player.getUniqueId());
            boolean dragPreview = !currentEditedText.getTextInstance().isDragPreview();
            currentEditedText.setDragPreviewTasks(dragPreview);
            player.sendMessage(Messages.currentTextDragPreviewChangeSuccess(dragPreview));
            player.closeInventory();

        }
    }

}
