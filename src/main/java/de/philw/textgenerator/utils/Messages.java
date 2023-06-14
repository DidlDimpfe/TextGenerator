package de.philw.textgenerator.utils;

import org.bukkit.ChatColor;

public class Messages {

    public static String nothingToUndo = ChatColor.RED + "There's nothing to undo!";
    public static String successfulUndo = ChatColor.GREEN + "Successfully undid the last change!";
    public static String invalidDirection = ChatColor.RED + "That is not a valid direction. Try north, east, south or west!";
    public static String needPermissionForCommand(String permission) {
        return ChatColor.RED + "You need the " + permission + " permission to use this command!";
    }
    public static String successfulDirectionChange(String direction) {
        return ChatColor.GREEN + "Successfully changed the direction to " + direction;
    }
}
