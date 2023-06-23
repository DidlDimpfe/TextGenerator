package de.philw.textgenerator.utils;

import org.bukkit.ChatColor;

public class Messages {
    public static final String confirmSuccessful = "";
    public static final String confirmDenied = "";
    public static final String openEditMenuDenied = ChatColor.RED + "You cannot open the edit menu, when you are not editing something";
    public static final String textMoveDeniedBecauseNotEditing = ChatColor.RED + "You cannot move the edited text, when you are not editing something!";
    public static String textMoveSuccess(String coordinates) { return ChatColor.GREEN + "Succesfully moved your text to these directions: " + coordinates; }
    public static final String textMoveDeniedBecauseInvalidCoordinates = ChatColor.RED + "The coordinates you have given are not valid";

    public static final String openSettingsMenuDenied = ChatColor.RED + "You are currently editing a text. Try /textgenerator edit or /textgenerator confirm";
    public static final String cancelDestroyDenied = "Denied Cancel";
    public static final String cancelDestroySuccess = "Sucessfully cancelled";
    public static final String cancelToPreviousLocationSuccess = "";

    public static String defaultBlockChangeSuccess(String blockDisplay) { return ChatColor.GREEN + "Succesfully changed the default block to " + blockDisplay; }
    public static String currentTextBlockChangeSuccess(String blockDisplay) { return ChatColor.GREEN + "Succesfully changed the block of your current edited text to " + blockDisplay; }
    public static String defaultFontSizeChangeSuccess(int fontSize) { return ChatColor.GREEN + "Succesfully changed the default font size to " + fontSize; }
    public static String currentTextFontSizeChangeSuccess(int fontSize) { return ChatColor.GREEN + "Succesfully changed the font fontSize of your current edited text to " + fontSize; }
    public static String defaultLineSpacingChangeSuccess(int lineSpacing) { return ChatColor.GREEN + "Succesfully changed the default lineSpacing to " + lineSpacing; }
    public static String currentTextLineSpacingChangeSuccess(int lineSpacing) { return ChatColor.GREEN + "Succesfully changed the line spacing of your current edited text to " + lineSpacing; }
    public static String defaultDragPreviewChangeSuccess(boolean dragPreview) { return ChatColor.GREEN + "Succesfully changed the default drag preview to " + dragPreview; }
    public static String currentTextDragPreviewChangeSuccess(boolean dragPreview) { return ChatColor.GREEN + "Succesfully changed the drag preview of your current edited text to " + dragPreview; }

    public static String needPermissionForCommand(String permission) { return ChatColor.RED + "You need the " + permission + " permission to use this command!"; }
}
