package de.philw.textgenerator.ui;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.ui.value.Block;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BlockSearchUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.GREEN +
                "Select a Block for the text")) {
            return;
        }
        if (e.getCurrentItem() == null) return;
        Player player = (Player) e.getWhoClicked();
        e.setCancelled(true);
        if (e.getCurrentItem().getItemMeta() == null) return;
        if (e.getCurrentItem().getItemMeta().getLocalizedName().equals("")) return;
        if (TextGenerator.getInstance().getCurrentEdited() == null) {
            Block block = Block.valueOf(e.getCurrentItem().getItemMeta().getLocalizedName());
            ConfigManager.setBlock(block);
            TextGenerator.getInstance().getTextGeneratorCommand().getTextInstance().setBlock(block);
            for (SearchUI searchUI : TextGenerator.getInstance().getSearchUIListener().getSearchUISToListenTo().values()) {
                if (!(searchUI instanceof BlockSearchUI)) continue;
                searchUI.updateAllItems();
                searchUI.openPage(searchUI.currentPage);
            }
            player.sendMessage(ChatColor.GREEN + "Succesfully changed your default block to " + block.getDisplay());
            player.closeInventory();
        }
    }

}

