package de.philw.textgenerator.command;

import de.philw.textgenerator.letters.CurrentEditedText;
import de.philw.textgenerator.ui.SettingsUI;
import de.philw.textgenerator.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class TextGeneratorCommand extends Command {

    private final HashMap<UUID, CurrentEditedText> currentEditTexts;


    public TextGeneratorCommand() {
        super("textgenerator", new String[]{"tg", "textgen", "tgen"}, "The command for the TextGenerator plugin",
                "textgenerator.use");
        currentEditTexts = new HashMap<>();
    }

    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }

    private void helpRequested(Player player) {
        player.sendMessage(ChatColor.GREEN + "The textgenerator command has the following options:");
        player.sendMessage(ChatColor.GREEN + "/textgenerator help");
        player.sendMessage(ChatColor.GREEN + "/textgenerator generate <input>");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            return;
        }
        Player player = (Player) sender;
        if (checkEdit(player, args)) return;
        if (checkConfirm(player, args)) return;
        if (checkSettingsMenu(player, args)) return;
        if (checkGenerate(player, args)) return;
        if (checkCancel(player, args)) return;

        helpRequested(player);
    }

    private boolean checkGenerate(Player player, String[] args) {
        if (!(args.length > 1 && args[0].equalsIgnoreCase("generate"))) return false;
        StringBuilder builder = new StringBuilder();
        for (int index = 1; index < args.length; index++) {
            builder.append(args[index]).append(" ");
        }
        currentEditTexts.put(player.getUniqueId(), new CurrentEditedText(player, builder.substring(0, builder.toString().length() - 1), true));
        //TO WORK ON
        // You can  adjust it now message
        // don't Forget to confirm message
        return true;
    }

    public HashMap<UUID, CurrentEditedText> getCurrentEditTexts() {
        return currentEditTexts;
    }

    private boolean checkSettingsMenu(Player player, String[] args) {
        if (!(args.length == 1 && args[0].equalsIgnoreCase("settings"))) return false;
        if (!currentEditTexts.containsKey(player.getUniqueId())) {
            new SettingsUI(player);
        } else {
            player.sendMessage(Messages.openSettingsMenuDenied);
        }
        return true;
    }

    private boolean checkConfirm(Player player, String[] args) {
        if (!(args.length == 1 && args[0].equalsIgnoreCase("confirm"))) return false;
        if (currentEditTexts.containsKey(player.getUniqueId())) {
            currentEditTexts.get(player.getUniqueId()).confirm();
            currentEditTexts.remove(player.getUniqueId());
            player.sendMessage(Messages.confirmSuccessful);
        } else {
            player.sendMessage(Messages.confirmDenied);
        }
        return true;
    }

    private boolean checkEdit(Player player, String[] args) {
        if (args.length == 0) return false;
        if (!args[0].equalsIgnoreCase("edit")) return false;
        if (args.length == 1) {
            if (!currentEditTexts.containsKey(player.getUniqueId())) {
                player.sendMessage(Messages.openEditMenuDenied);
            } else {
                new SettingsUI(player);
            }
            return true;
        }
        if (args.length != 2) {
            return false;
        }
        if (args[1].equalsIgnoreCase("this")) {
            // TO WORK ON
            return true;
        }
        if (args[1].equalsIgnoreCase("last")) {
            // TO WORK ON
            return true;
        }
        return false;
    }

    private boolean checkCancel(Player player, String[] args) {
        if (!(args.length == 1 && args[0].equalsIgnoreCase("cancel"))) return false;
        if (!currentEditTexts.containsKey(player.getUniqueId())) {
            player.sendMessage(Messages.cancelDestroyDenied);
            return true;
        }
        CurrentEditedText currentEditedText = currentEditTexts.get(player.getUniqueId());
        if (currentEditedText.isFirstGenerate()) {
            currentEditedText.destroy();
            currentEditTexts.remove(player.getUniqueId());
            player.sendMessage(Messages.cancelDestroySuccess);
            return true;
        }
        // TO WORK ON
        // SET TO PREVIOUS STATE

        return true;
    }

}
