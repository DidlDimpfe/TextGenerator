package de.philw.textgenerator.command;

import de.philw.textgenerator.letters.big.LetterConverter;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.ui.SettingsUI;
import de.philw.textgenerator.utils.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class TextGeneratorCommand extends Command {

    private final TextInstance textInstance;
    private final ArrayList<HashMap<Location, BlockData>> lastChanges;


    public TextGeneratorCommand() {
        super("textgenerator", new String[]{"tg", "textgen", "tgen"}, "The command for the TextGenerator plugin",
                "textgenerator.use");
        lastChanges = new ArrayList<>();
        textInstance = TextInstance.getTextInstanceBuilder()
                .withBlock(ConfigManager.getBlock())
                .withFontSize(ConfigManager.getFontSize())
                .withFontName(ConfigManager.getFontName())
                .withFontStyle(ConfigManager.getFontStyle())
                .withUnderline(ConfigManager.isUnderline())
                .withLineSpacing(ConfigManager.getLineSpacing())
                .build();
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
        if ((args.length == 1 || args.length == 4) && args[0].equalsIgnoreCase("setStart")) {
            if (args.length == 1) {
                setStart(player);
            } else {
                setStart(player, args[1], args[2], args[3]);
            }
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("undo")) {
            undo(player);
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("setDirection")) {
            setDirection(player, args[1]);
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("settings")) {
            new SettingsUI(player);
        }

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
        if (textInstance.getStartLocation() == null) {
            player.sendMessage(ChatColor.RED + "Before you do this you have to set the start location with " +
                    "/textgenerator setStart (<coordinates>)");
            return;
        }

        Direction nowDirection = textInstance.getDirection();
        boolean deleteDirectionChange = false;
        if (nowDirection == null) {
            deleteDirectionChange = true;
            textInstance.setDirection(Direction.valueOf(player.getFacing().toString()).getRightDirection());
        }

        textInstance.setText(toGenerate);

        GenerateUtil.setTextInstance(textInstance);
        GenerateUtil.setBlocks(GenerateUtil.getBlocks(LetterConverter.stringToBufferedImages(textInstance)));

        lastChanges.add(GenerateUtil.getAffectedBlocks());

        GenerateUtil.buildBlocks();

        if (deleteDirectionChange) textInstance.setDirection(null);
    }

    private void setStart(Player player) {
        textInstance.setStartLocation(player.getLocation().getBlock().getLocation());
        player.sendMessage(ChatColor.GREEN + "StartLocation has been successfully set to " +
                textInstance.getStartLocation().getX() + ", " + textInstance.getStartLocation().getY() + ", " +
                textInstance.getStartLocation().getZ());
    }

    private void setStart(Player player, String x, String y, String z) {
        if (!Validator.isInteger(x) || !Validator.isInteger(y) || !Validator.isInteger(z)) {
            player.sendMessage(ChatColor.RED + "The coordinates have to be integers.");
            return;
        }
        textInstance.setStartLocation(new Location(player.getWorld(), Double.parseDouble(x), Double.parseDouble(y),
                Double.parseDouble(z)));
        player.sendMessage(ChatColor.GREEN + "StartLocation has been successfully set to " +
                textInstance.getStartLocation().getX() + ", " + textInstance.getStartLocation().getY() + ", " +
                textInstance.getStartLocation().getZ());
    }

    private void setDirection(Player player, String direction) {
        try {
            Direction.valueOf(direction.toUpperCase());
        } catch (IllegalArgumentException e) {
            player.sendMessage(Messages.invalidDirection);
            return;
        }

        this.textInstance.setDirection(Direction.valueOf(direction.toUpperCase()));
        player.sendMessage(Messages.successfulDirectionChange(direction));
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

    public TextInstance getTextInstance() {
        return textInstance;
    }

}
