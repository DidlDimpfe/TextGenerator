package de.philw.textgenerator.command;

import de.philw.textgenerator.font.CurrentEditedText;
import de.philw.textgenerator.manager.MessagesManager;
import de.philw.textgenerator.ui.SettingsUI;
import de.philw.textgenerator.utils.GenerateUtil;
import de.philw.textgenerator.utils.Validator;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.*;

public class TextGeneratorCommand extends Command {

    private final HashMap<UUID, CurrentEditedText> currentEditedTexts;


    public TextGeneratorCommand() {
        super("textgenerator", new String[]{"tg", "textgen", "tgen"}, "The command for the TextGenerator plugin",
                "textgenerator.use");
        currentEditedTexts = new HashMap<>();
    }

    public List<String> onTabComplete(CommandSender sender, String[] args) {
        Player player = ((Player) sender);
        // General
        if (args.length == 1) {
            if (currentEditedTexts.containsKey(player.getUniqueId())) {
                return StringUtil.copyPartialMatches(args[0], Arrays.asList("edit", "confirm", "cancel", "move", "destroy", "refresh", "settext", "reset", "remove"), new ArrayList<>());
            } else {
                return StringUtil.copyPartialMatches(args[0], Arrays.asList("settings", "generate", "edit"), new ArrayList<>());
            }
        }
        // edit
        if (args.length == 2 && args[0].equalsIgnoreCase("edit")) {
            if (currentEditedTexts.containsKey(player.getUniqueId())) {
                return StringUtil.copyPartialMatches(args[1], Collections.singletonList("menu"), new ArrayList<>());
            } else {
                return StringUtil.copyPartialMatches(args[1], Collections.singletonList("this"), new ArrayList<>());
            }
        }


        return null;
    }

    private void helpRequested(Player player) {
        player.sendMessage(MessagesManager.getMessage("helpRequested"));
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
        if (checkMove(player, args)) return;
        if (checkDestroy(player, args)) return;
        if (checkRefresh(player, args)) return;
        if (checkSetText(player, args)) return;
        if (checkReset(player, args)) return;
        if (checkRemove(player, args)) return;

        helpRequested(player);
    }

    public HashMap<UUID, CurrentEditedText> getCurrentEditedTexts() {
        return currentEditedTexts;
    }

    private boolean checkGenerate(Player player, String[] args) {
        if (!(args.length > 1 && args[0].equalsIgnoreCase("generate"))) return false;
        StringBuilder builder = new StringBuilder();
        for (int index = 1; index < args.length; index++) {
            builder.append(args[index]).append(" ");
        }
        if (!currentEditedTexts.containsKey(player.getUniqueId())) {
            CurrentEditedText currentEditedText = new CurrentEditedText(player, builder.substring(0,
                    builder.toString().length() - 1));
            if (currentEditedText.getGenerateSuccess() == CurrentEditedText.VALID || currentEditedText.getGenerateSuccess() == CurrentEditedText.WOULD_OVERRIDE_OTHER_BLOCKS) {
                currentEditedTexts.put(player.getUniqueId(), currentEditedText);
                player.sendMessage(MessagesManager.getMessage("generate.success",
                        currentEditedText.getTextInstance().getText()));
                if (currentEditedText.getGenerateSuccess() == CurrentEditedText.WOULD_OVERRIDE_OTHER_BLOCKS) {
                    player.sendMessage(MessagesManager.getMessage("textCannotBeBuildBecauseItWouldOverrideOtherBlocks"));
                }
            } else if (currentEditedText.getGenerateSuccess() == CurrentEditedText.BLOCK_HAS_NO_SLAB_OR_STAIR) {
                player.sendMessage(MessagesManager.getMessage("changedValueOfCurrentText.deniedBecauseBlockHasNoSlabOrStairAndSpecificFontSizeIsSelected", currentEditedText.getTextInstance().getBlock().getDisplay()));
            } else if (currentEditedText.getGenerateSuccess() ==CurrentEditedText.TEXT_IS_NOT_VALID_FOR_SPECIFIC_FONT_SIZE) {
                player.sendMessage(MessagesManager.getMessage("changedValueOfCurrentText.deniedBecauseSpecificFontSizeIsSelectedButTextIsNotValid", currentEditedText.getTextInstance().getText()));
            }
        } else {
            player.sendMessage(MessagesManager.getMessage("generate.deniedBecauseAlreadyEditingSomething",
                    currentEditedTexts.get(player.getUniqueId()).getTextInstance().getText()));
        }
        return true;
    }

    private boolean checkSettingsMenu(Player player, String[] args) {
        if (!(args.length == 1 && args[0].equalsIgnoreCase("settings"))) return false;
        if (!currentEditedTexts.containsKey(player.getUniqueId())) {
            new SettingsUI(player);
        } else {
            player.sendMessage(MessagesManager.getMessage("openSettingsMenu.denied",
                    currentEditedTexts.get(player.getUniqueId()).getTextInstance().getText()));
        }
        return true;
    }

    private boolean checkConfirm(Player player, String[] args) {
        if (!(args.length == 1 && args[0].equalsIgnoreCase("confirm"))) return false;
        if (currentEditedTexts.containsKey(player.getUniqueId())) {
            CurrentEditedText currentEditedText = currentEditedTexts.get(player.getUniqueId());
            if (currentEditedText.confirm(true)) {
                player.sendMessage(MessagesManager.getMessage("confirm.success",
                        currentEditedText.getTextInstance().getText()));
            } else {
                player.sendMessage(MessagesManager.getMessage("confirm.deniedBecauseNotValidPosition",
                        currentEditedText.getTextInstance().getText()));
            }
            currentEditedTexts.remove(player.getUniqueId());
        } else {
            player.sendMessage(MessagesManager.getMessage("confirm.deniedBecauseNotEditingSomething"));
        }
        return true;
    }

    private boolean checkEdit(Player player, String[] args) {
        if (args.length == 0) return false;
        if (!args[0].equalsIgnoreCase("edit")) return false;
        if (args.length == 2 && args[1].equalsIgnoreCase("menu")) {
            if (!currentEditedTexts.containsKey(player.getUniqueId())) {
                player.sendMessage(MessagesManager.getMessage("edit.openMenuDenied"));
            } else {
                new SettingsUI(player);
            }
            return true;
        }
        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("this")) {
                if (currentEditedTexts.containsKey(player.getUniqueId())) {
                    player.sendMessage(MessagesManager.getMessage("edit.this.deniedBecauseAlreadyEditingSomething",
                            currentEditedTexts.get(player.getUniqueId()).getTextInstance().getText()));
                    return true;
                }
                CurrentEditedText currentEditedText = GenerateUtil.getPlayersWantedTextToEdit(player);
                if (currentEditedText == null) {
                    player.sendMessage(MessagesManager.getMessage("edit.this.deniedBecauseNotLookingAtSomething"));
                } else {
                    if (currentEditedText.isNotTheSameAsItWas()) {
                        currentEditedTexts.put(player.getUniqueId(), currentEditedText);
                        player.sendMessage(MessagesManager.getMessage("edit.this.success",
                                currentEditedText.getTextInstance().getText()));
                        TextComponent info = new TextComponent(MessagesManager.getMessage("edit.this.infoNotTheSameAsItWas").replace('&', '§'));
                        TextComponent reset = new TextComponent("§r§l§2[RESET] ");
                        reset.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to reset the text to it's previous form")));
                        reset.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tg reset"));
                        TextComponent remove = new TextComponent("§r§l§c[REMOVE]");
                        remove.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to remove the text from the plugin")));
                        remove.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tg remove"));
                        player.spigot().sendMessage(info, reset, remove);
                    } else {
                        currentEditedTexts.put(player.getUniqueId(), currentEditedText);
                        player.sendMessage(MessagesManager.getMessage("edit.this.success",
                                currentEditedText.getTextInstance().getText()));
                    }
                }
                return true;
            }
        }
        return false;
    }

    private boolean checkCancel(Player player, String[] args) {
        if (!(args.length == 1 && args[0].equalsIgnoreCase("cancel"))) return false;
        if (!currentEditedTexts.containsKey(player.getUniqueId())) {
            player.sendMessage(MessagesManager.getMessage("cancel.destroy.deniedBecauseNotEditingSomething"));
            return true;
        }
        CurrentEditedText currentEditedText = currentEditedTexts.get(player.getUniqueId());
        if (currentEditedText.isFirstGenerate()) {
            currentEditedText.destroy();
            currentEditedTexts.remove(player.getUniqueId());
            player.sendMessage(MessagesManager.getMessage("cancel.destroy.success",
                    currentEditedText.getTextInstance().getText()));
        } else {
            currentEditedText.moveToPreviousState();
            currentEditedTexts.remove(player.getUniqueId());
            player.sendMessage(MessagesManager.getMessage("cancel.toPreviousLocationSuccess",
                    currentEditedText.getTextInstance().getText()));
        }
        return true;
    }

    private boolean checkMove(Player player, String[] args) {
        if (!(args.length == 4 && args[0].equalsIgnoreCase("move"))) return false;
        if (currentEditedTexts.containsKey(player.getUniqueId())) {
            if (Validator.isNoInteger(args[1]) || Validator.isNoInteger(args[2]) || Validator.isNoInteger(args[3])) {
                player.sendMessage(MessagesManager.getMessage("move.deniedBecauseInvalidCoordinates"));
            } else {
                CurrentEditedText currentEditedText = currentEditedTexts.get(player.getUniqueId());
                if (currentEditedText.getTextInstance().isDragToMove()) {
                    player.sendMessage(MessagesManager.getMessage("move.deniedBecauseDragToMoveActivated"));
                    return true;
                }
                if (currentEditedText.move(Integer.parseInt(args[1]), Integer.parseInt(args[2]),
                        Integer.parseInt(args[3]))) {
                    player.sendMessage(MessagesManager.getMessage("move.success",
                            currentEditedText.getTextInstance().getText(), args[1] + ", " + args[2] + ", " + args[3]));
                } else {
                    player.sendMessage(MessagesManager.getMessage("move.deniedBecauseLocationNotValid"));
                }
            }
            return true;
        }
        player.sendMessage(MessagesManager.getMessage("move.deniedBecauseNotEditingSomething"));
        return true;
    }

    private boolean checkDestroy(Player player, String[] args) {
        if (!(args.length == 1 && args[0].equalsIgnoreCase("destroy"))) return false;
        if (currentEditedTexts.containsKey(player.getUniqueId())) {
            CurrentEditedText currentEditedText = currentEditedTexts.get(player.getUniqueId());
            currentEditedText.destroy();
            player.sendMessage(MessagesManager.getMessage("destroy.success",
                    currentEditedText.getTextInstance().getText()));
            currentEditedTexts.remove(player.getUniqueId());
        } else {
            player.sendMessage(MessagesManager.getMessage("destroy.deniedBecauseNotEditingSomething"));
        }
        return true;
    }

    private boolean checkRefresh(Player player, String[] args) {
        if (!(args.length == 1 && args[0].equalsIgnoreCase("refresh"))) return false;
        if (currentEditedTexts.containsKey(player.getUniqueId())) {
            CurrentEditedText currentEditedText = currentEditedTexts.get(player.getUniqueId());
            if (currentEditedText.getTextInstance().isDragToMove()) {
                player.sendMessage(MessagesManager.getMessage("refresh.deniedBecauseDragToMoveActivated"));
                return true;
            }
            currentEditedText.refreshBlocksByPlayersSight();
            player.sendMessage(MessagesManager.getMessage("refresh.success", currentEditedText.getTextInstance().getText()));
            return true;
        }
        player.sendMessage(MessagesManager.getMessage("refresh.deniedBecauseNotEditingSomething"));
        return true;
    }

    private boolean checkSetText(Player player, String[] args) {
        if (!(args.length > 1 && args[0].equalsIgnoreCase("settext"))) return false;
        if(currentEditedTexts.containsKey(player.getUniqueId())) {
            StringBuilder builder = new StringBuilder();
            for (int index = 1; index < args.length; index++) {
                builder.append(args[index]).append(" ");
            }
            String newText = builder.substring(0, builder.toString().length() - 1);

            CurrentEditedText currentEditedText = currentEditedTexts.get(player.getUniqueId());
            if (currentEditedText.getTextInstance().getText().equals(newText)) {
                player.sendMessage(MessagesManager.getMessage("changedValueOfCurrentText.deniedBecauseValueAlreadySelected", "text", newText));
                return true;
            }
            if (!currentEditedText.setText(newText)) {
                player.sendMessage(MessagesManager.getMessage("changedValueOfCurrentText.deniedBecauseSpecificFontSizeIsSelectedButTextIsNotValid", newText));
            } else {
                player.sendMessage(MessagesManager.getMessage("setText.success", newText));
            }
        } else {
            player.sendMessage(MessagesManager.getMessage("setText.deniedBecauseNotEditingSomething"));
        }
        return true;
    }

    private boolean checkRemove(Player player, String[] args) {
        if (!(args.length == 1 && args[0].equalsIgnoreCase("remove"))) return false;
        if(currentEditedTexts.containsKey(player.getUniqueId())) {
            CurrentEditedText currentEditedText = currentEditedTexts.get(player.getUniqueId());
            currentEditedText.confirm(false);
            currentEditedTexts.remove(player.getUniqueId());
            player.sendMessage(MessagesManager.getMessage("remove.success", currentEditedText.getTextInstance().getText()));
        } else {
            player.sendMessage(MessagesManager.getMessage("remove.deniedBecauseNotEditingSomething"));
        }
        return true;
    }

    private boolean checkReset(Player player, String[] args) {
        if (!(args.length == 1 && args[0].equalsIgnoreCase("reset"))) return false;
        if(currentEditedTexts.containsKey(player.getUniqueId())) {
            CurrentEditedText currentEditedText = currentEditedTexts.get(player.getUniqueId());
            currentEditedText.reset();
            player.sendMessage(MessagesManager.getMessage("reset.success", currentEditedText.getTextInstance().getText()));
        } else {
            player.sendMessage(MessagesManager.getMessage("reset.deniedBecauseNotEditingSomething"));
        }
        return true;
    }

}
