package de.philw.textgenerator.command;

import de.philw.textgenerator.letters.big.CurrentEditText;
import de.philw.textgenerator.ui.SettingsUI;
import de.philw.textgenerator.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class TextGeneratorCommand extends Command {

    private final ArrayList<HashMap<Location, BlockData>> lastChanges;
    private final HashMap<UUID, CurrentEditText> currentEditTexts;


    public TextGeneratorCommand() {
        super("textgenerator", new String[]{"tg", "textgen", "tgen"}, "The command for the TextGenerator plugin",
                "textgenerator.use");
        lastChanges = new ArrayList<>();
        currentEditTexts = new HashMap<>();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            return;
        }
        Player player = (Player) sender;
        if (args.length == 0 || (args.length == 1 && args[0].equalsIgnoreCase("help"))) {
            helpRequested(player);
        }
        if (args.length > 1 && args[0].equalsIgnoreCase("generate")) {
            StringBuilder builder = new StringBuilder();
            for (int index = 1; index < args.length; index++) {
                builder.append(args[index]).append(" ");
            }
            generate(player, builder.substring(0, builder.toString().length() - 1));
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("undo")) {
            undo(player);
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("settings")) {
            new SettingsUI(player);
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("edit")) {
            edit(player);
        }
    }

    private void edit(Player player) {
        if (!currentEditTexts.containsKey(player.getUniqueId())) {

            return;
        }
        new SettingsUI(player);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }

    private void helpRequested(Player player) {
        player.sendMessage(ChatColor.GREEN + "The textgenerator command has the following options:");
        player.sendMessage(ChatColor.GREEN + "/textgenerator help");
        player.sendMessage(ChatColor.GREEN + "/textgenerator setStart (<coordinates>)");
        player.sendMessage(ChatColor.GREEN + "/textgenerator setDirection <input>)");
        player.sendMessage(ChatColor.GREEN + "/textgenerator generate <direction>");
        player.sendMessage(ChatColor.GREEN + "/textgenerator undo");
    }

    private void generate(Player player, String toGenerate) {
        currentEditTexts.put(player.getUniqueId(), new CurrentEditText(player, toGenerate));
    }

    private void undo(Player player) {
        if (lastChanges.isEmpty()) {
            player.sendMessage(Messages.nothingToUndo);
            return;
        }
        HashMap<Location, BlockData> lastChange = lastChanges.get(lastChanges.size() - 1);
        for (Location location : lastChange.keySet()) {
            Objects.requireNonNull(location.getWorld()).getBlockAt(location).setBlockData(lastChange.get(location));
        }
        player.sendMessage(Messages.successfulUndo);
        lastChanges.remove(lastChanges.size() - 1);
    }

    public HashMap<UUID, CurrentEditText> getCurrentEditTexts() {
        return currentEditTexts;
    }
}
