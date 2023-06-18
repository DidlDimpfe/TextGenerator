package de.philw.textgenerator.utils;

import org.bukkit.ChatColor;

public class Messages {

    public static String undoDenied = ChatColor.RED + "There's nothing to undo!";
    public static String undoSuccessful = ChatColor.GREEN + "Successfully undid the last change!";
    public static String confirmSuccessful = "";
    public static String confirmDenied = "";
    public static String openEditMenuDenied = ChatColor.RED + "You cannot open the edit menu, when you are not editing something!";
    public static String openSettingsMenuDenied = ChatColor.RED + "You are currently editing a text. Try /textgenerator edit or /textgenerator confirm";
    public static String needPermissionForCommand(String permission) {
        return ChatColor.RED + "You need the " + permission + " permission to use this command!";
    }
}
