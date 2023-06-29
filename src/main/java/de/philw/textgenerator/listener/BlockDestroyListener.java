package de.philw.textgenerator.listener;

import de.philw.textgenerator.manager.MessagesManager;
import de.philw.textgenerator.utils.FastBlockUpdate;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockDestroyListener implements Listener {
    @EventHandler
    public void onBlockDestroy(BlockBreakEvent e) {
        if (e.getBlock().hasMetadata(FastBlockUpdate.metaDataKey)) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(MessagesManager.getMessage("blockBreakFromCurrentEditedTextDisabled"));
        }
    }

}
