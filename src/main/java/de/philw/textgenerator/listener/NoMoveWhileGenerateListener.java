package de.philw.textgenerator.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.UUID;

public class NoMoveWhileGenerateListener implements Listener {

    private static final ArrayList<UUID> disallowedToMove = new ArrayList<>();

    public static void add(UUID uuid) {
        disallowedToMove.add(uuid);
    }

    public static void remove(UUID uuid) {
        disallowedToMove.remove(uuid);
    }

    @EventHandler
    public void noMove(PlayerMoveEvent e) {
        if (disallowedToMove.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
        }
    }

}
