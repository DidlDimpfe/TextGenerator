package de.philw.textgenerator.ui;

import de.philw.textgenerator.utils.SkullData;
import de.philw.textgenerator.utils.UIUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class SearchUI {

    protected final static int SPACES = 45;
    protected final static int RETURN_ARROW_INDEX = 45;
    protected final static int PREVIOUS_PAGE_INDEX = 48;
    protected final static int NEXT_PAGE_INDEX = 50;
    protected final static int SEARCH_INDEX = 49;
    protected final static int[] SPACE_INDEXES = new int[]{46, 47, 51, 52, 53};
    protected String name;
    protected ArrayList<ItemStack> allItems;
    protected ArrayList<ItemStack> searchedItems;
    protected Inventory inventory;
    protected int currentPage;
    protected String currentSearch;

    public SearchUI(String name, ArrayList<ItemStack> allItems) {
        this.inventory = Bukkit.createInventory(null, 54);
        this.name = name;
        this.allItems = allItems;
        this.searchedItems = this.allItems;
        this.currentSearch = "";

        // Add return item
        ItemStack returnArrowItemStack = UIUtil.getSkullByString(SkullData.RETURN_ARROW);
        ItemMeta returnArrowItemMeta = Objects.requireNonNull(returnArrowItemStack).getItemMeta();
        Objects.requireNonNull(returnArrowItemMeta).setDisplayName(ChatColor.GREEN + "Return");
        List<String> returnArrowLore = new ArrayList<>();
        returnArrowLore.add(ChatColor.YELLOW + "Click to go back to TextGenerator settings");
        returnArrowItemMeta.setLore(returnArrowLore);
        returnArrowItemStack.setItemMeta(returnArrowItemMeta);
        inventory.setItem(RETURN_ARROW_INDEX, returnArrowItemStack);

        //Add search Item
        ItemStack searchItemStack = UIUtil.getSkullByString(SkullData.SEARCH);
        ItemMeta searchItemMeta = Objects.requireNonNull(searchItemStack).getItemMeta();
        Objects.requireNonNull(searchItemMeta).setDisplayName(ChatColor.GREEN + "Search");
        List<String> searchLore = new ArrayList<>();
        searchLore.add(ChatColor.GRAY + "Search Items");
        searchLore.add("");
        searchLore.add(ChatColor.GRAY + "Filtered: " + currentSearch);
        searchLore.add("");
        searchLore.add(ChatColor.AQUA + "Right click to clear");
        searchLore.add(ChatColor.YELLOW + "Click to edit filter");
        searchItemMeta.setLore(searchLore);
        searchItemStack.setItemMeta(searchItemMeta);
        inventory.setItem(SEARCH_INDEX, searchItemStack);
        //Add page arrows
        updatePageArrows();

        //Add fill items
        for (int index: SPACE_INDEXES) {
            ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta itemMeta = itemStack.getItemMeta();
            Objects.requireNonNull(itemMeta).setDisplayName("Space");
            itemStack.setItemMeta(itemMeta);
            inventory.setItem(index, itemStack);
        }
    }

    public void updatePageArrows() {
        ItemStack nextPageItemStack, previousPageItemStack;
        if (UIUtil.isPageValid(searchedItems, currentPage+1, SPACES)) {
            nextPageItemStack = UIUtil.getSkullByString(SkullData.NEXT_PAGE_ALLOWED);
            ItemMeta itemMeta = Objects.requireNonNull(nextPageItemStack).getItemMeta();
            Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + "Next Page");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "(" + currentPage + "/" + UIUtil.getMaxPage(searchedItems, SPACES) + ")");
            lore.add("");
            lore.add(ChatColor.AQUA + "Right click for max. page");
            lore.add(ChatColor.YELLOW + "Click to change page");
            itemMeta.setLore(lore);
            nextPageItemStack.setItemMeta(itemMeta);
        } else {
            nextPageItemStack = UIUtil.getSkullByString(SkullData.NEXT_PAGE_DISALLOWED);
            ItemMeta itemMeta = Objects.requireNonNull(nextPageItemStack).getItemMeta();
            Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.RED + "Next Page");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "(" + currentPage + "/" + UIUtil.getMaxPage(searchedItems, SPACES) + ")");
            lore.add("");
            lore.add(ChatColor.RED + "You are on the max. page");
            itemMeta.setLore(lore);
            nextPageItemStack.setItemMeta(itemMeta);
        }
        if (UIUtil.isPageValid(searchedItems, currentPage-1, SPACES)) {
            previousPageItemStack = UIUtil.getSkullByString(SkullData.PREVIOUS_PAGE_ALLOWED);
            ItemMeta itemMeta = Objects.requireNonNull(previousPageItemStack).getItemMeta();
            Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + "Previous Page");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "(" + currentPage + "/" + UIUtil.getMaxPage(searchedItems, SPACES) + ")");
            lore.add("");
            lore.add(ChatColor.AQUA + "Right click for min. page");
            lore.add(ChatColor.YELLOW + "Click to change page");
            itemMeta.setLore(lore);
            previousPageItemStack.setItemMeta(itemMeta);
        } else {
            previousPageItemStack = UIUtil.getSkullByString(SkullData.PREVIOUS_PAGE_DISALLOWED);
            ItemMeta itemMeta = Objects.requireNonNull(previousPageItemStack).getItemMeta();
            Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.RED + "Previous Page");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "(" + currentPage + "/" + UIUtil.getMaxPage(searchedItems, SPACES) + ")");
            lore.add("");
            lore.add(ChatColor.RED + "You are on the min. page");
            itemMeta.setLore(lore);
            previousPageItemStack.setItemMeta(itemMeta);
        }
        inventory.setItem(NEXT_PAGE_INDEX, nextPageItemStack);
        inventory.setItem(PREVIOUS_PAGE_INDEX, previousPageItemStack);
    }

    public void updateSearchItem() {
        ItemStack itemStack = inventory.getItem(SEARCH_INDEX);
        List<String> lore = Objects.requireNonNull(Objects.requireNonNull(itemStack).getItemMeta()).getLore();
        Objects.requireNonNull(lore).set(2, "Filtered: " + currentSearch);
        itemStack.getItemMeta().setLore(lore);
    }

}
