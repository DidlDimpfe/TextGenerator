package de.philw.textgenerator.ui;

import de.philw.textgenerator.utils.SkullData;
import de.philw.textgenerator.utils.UIUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SettingUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent inventoryClickEvent) {
        if (inventoryClickEvent.getCurrentItem().getItemMeta().hasDisplayName()) {
            inventoryClickEvent.setCancelled(true);
            new SizeSearchUI((Player) inventoryClickEvent.getWhoClicked());
        }
    }

}
