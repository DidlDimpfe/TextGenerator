package de.philw.textgenerator.ui;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class LineSpacingSearchUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.GREEN +
                "Select line spacing")) {
            return;
        }
        if (e.getCurrentItem() == null) return;
        Player player = (Player) e.getWhoClicked();
        e.setCancelled(true);
        if (e.getCurrentItem().getItemMeta() == null) return;
        if (e.getCurrentItem().getItemMeta().getLocalizedName().equals("")) return;
        if (TextGenerator.getInstance().getCurrentEdited() == null) {
            String lineSpacing = e.getCurrentItem().getItemMeta().getLocalizedName();
            ConfigManager.setLineSpacing(Integer.parseInt(lineSpacing));
            TextGenerator.getInstance().getTextGeneratorCommand().getTextInstance().setLineSpacing(Integer.parseInt(lineSpacing));
            for (SearchUI searchUI : TextGenerator.getInstance().getSearchUIListener().getSearchUISToListenTo().values()) {
                if (!(searchUI instanceof LineSpacingSearchUI)) continue;
                searchUI.updateAllItems();
                searchUI.openPage(searchUI.currentPage);
            }
            player.sendMessage(ChatColor.GREEN + "Succesfully changed your default line spacing to " + lineSpacing);
            player.closeInventory();
        }
    }

}
