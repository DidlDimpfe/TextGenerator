package de.philw.textgenerator.ui;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class FontSizeSearchUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.GREEN +
                "Select Size")) {
            return;
        }
        if (e.getCurrentItem() == null) return;
        Player player = (Player) e.getWhoClicked();
        e.setCancelled(true);
        if (e.getCurrentItem().getItemMeta() == null) return;
        if (e.getCurrentItem().getItemMeta().getLocalizedName().equals("")) return;
        int size = Integer.parseInt(e.getCurrentItem().getItemMeta().getLocalizedName());
        if (!TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditTexts().containsKey(player.getUniqueId())) {
            ConfigManager.setFontSize(size);
            for (SearchUI searchUI : TextGenerator.getInstance().getSearchUIListener().getSearchUISToListenTo().values()) {
                if (!(searchUI instanceof FontSizeSearchUI)) continue;
                searchUI.updateAllItems();
                searchUI.openPage(searchUI.currentPage);
            }
            player.sendMessage(ChatColor.GREEN + "Succesfully changed the default font size to " + size);
        } else {
            TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditTexts().get(player.getUniqueId()).setFontSize(size);
            player.sendMessage(ChatColor.GREEN + "Succesfully changed the font size of your current edited text to " + size);
            player.closeInventory();
        }
    }
}
