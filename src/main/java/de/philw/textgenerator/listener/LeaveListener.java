package de.philw.textgenerator.listener;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.letters.CurrentEditedText;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        if (TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().containsKey(e.getPlayer().getUniqueId())) {
            CurrentEditedText currentEditedText = TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().get(e.getPlayer().getUniqueId());
            currentEditedText.confirm();
            TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().remove(e.getPlayer().getUniqueId());
        }
    }

}
