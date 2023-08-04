package de.philw.textgenerator.ui;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.utils.SkullData;
import de.philw.textgenerator.utils.UIUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class SearchUI {

    public final static int BLOCK_SEARCH_UI = 0;
    public final static int FONT_SIZE_SEARCH_UI = 1;
    public final static int LINE_SPACING_SEARCH_UI = 2;
    public final static int PLACEMENT_RANGE_SEARCH_UI = 3;
    public final static int FONT_STYLE_SEARCH_UI = 4;
    public final static int FONT_SEARCH_UI = 5;

    protected final static int SPACES = 45;
    protected final static int RETURN_ARROW_INDEX = 45;
    protected final static int PREVIOUS_PAGE_INDEX = 48;
    protected final static int NEXT_PAGE_INDEX = 50;
    protected final static int SEARCH_INDEX = 49;
    protected final static int[] SPACE_INDEXES = new int[]{46, 47, 51, 52, 53};
    protected String inventoryDisplay;
    protected ArrayList<ItemStack> allItems;
    protected ArrayList<ItemStack> searchedItems;
    protected Inventory inventory;
    protected int currentPage;
    protected String currentSearch;
    protected String searchDisplayTitle;
    protected UUID uuid;

    public SearchUI(String inventoryDisplay, String searchDisplayTitle, Player player) {
        TextGenerator.getInstance().getSearchUIListener().addSearchUI(player.getUniqueId(), this);
        this.inventory = Bukkit.createInventory(null, 54, inventoryDisplay);
        this.inventoryDisplay = inventoryDisplay;
        this.allItems = new ArrayList<>();
        this.currentSearch = "";
        this.currentPage = 1;
        this.searchDisplayTitle = searchDisplayTitle;
        this.uuid = player.getUniqueId();
        updateAllItems();
        this.searchedItems = this.allItems;

        // Add return item
        ItemStack returnArrowItemStack = UIUtil.getSkullByString(SkullData.RETURN_ARROW);
        ItemMeta returnArrowItemMeta = Objects.requireNonNull(returnArrowItemStack).getItemMeta();
        Objects.requireNonNull(returnArrowItemMeta).setDisplayName(ChatColor.GREEN + "Return");
        List<String> returnArrowLore = new ArrayList<>();
        returnArrowLore.add(!TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().containsKey(player.getUniqueId()) ?
                ChatColor.YELLOW + "Click to go back to TextGenerator settings" :
                ChatColor.YELLOW + "Click to go back to TextGenerator edit menu");
        returnArrowItemMeta.setLore(returnArrowLore);
        returnArrowItemStack.setItemMeta(returnArrowItemMeta);
        inventory.setItem(RETURN_ARROW_INDEX, returnArrowItemStack);

        //Add search Item
        updateSearchItem("");

        //Add fill items
        for (int index : SPACE_INDEXES) {
            ItemStack itemStack = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
            ItemMeta itemMeta = itemStack.getItemMeta();
            Objects.requireNonNull(itemMeta).setDisplayName(" ");
            itemStack.setItemMeta(itemMeta);
            inventory.setItem(index, itemStack);
        }

        //Open first page
        openPage(1);
        player.openInventory(inventory);
    }

    public void updatePageArrows() {
        ItemStack nextPageItemStack, previousPageItemStack;
        if (UIUtil.isPageValid(searchedItems, currentPage + 1, SPACES)) {
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
            Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + "Next Page");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "(" + currentPage + "/" + UIUtil.getMaxPage(searchedItems, SPACES) + ")");
            lore.add("");
            lore.add(ChatColor.RED + "You are on the max. page");
            itemMeta.setLore(lore);
            nextPageItemStack.setItemMeta(itemMeta);
        }
        if (UIUtil.isPageValid(searchedItems, currentPage - 1, SPACES)) {
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
            Objects.requireNonNull(itemMeta).setDisplayName(ChatColor.GREEN + "Previous Page");
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

    public void search(String searched) {
        currentSearch = searched.trim();
        updateSearchItem(currentSearch);
        if (searched.isEmpty()) {
            searchedItems.clear();
            updateAllItems();
            searchedItems = allItems;
            openPage(1);
            return;
        }
        searchedItems = getSearchedItems(currentSearch);
        openPage(1);
    }

    public abstract ArrayList<ItemStack> getSearchedItems(String searched);

    public void clearPage() {
        for (int i = 0; i < 45; i++) {
            inventory.setItem(i, null);
        }
    }

    public void openPage(int page) {
        if (searchedItems.isEmpty()) {
            clearPage();
            return;
        }
        if (!UIUtil.isPageValid(searchedItems, page, SPACES)) return;
        clearPage();
        List<ItemStack> contents = UIUtil.getPageItems(searchedItems, page, SPACES);
        for (int index = 0; index < contents.size(); index++) {
            inventory.setItem(index, contents.get(index));
        }
        currentPage = page;
        updatePageArrows();
    }

    public void updateSearchItem(String filtered) {
        ItemStack searchItemStack = UIUtil.getSkullByString(SkullData.SEARCH);
        ItemMeta searchItemMeta = Objects.requireNonNull(searchItemStack).getItemMeta();
        Objects.requireNonNull(searchItemMeta).setDisplayName(ChatColor.GREEN + "Search");
        List<String> searchLore = new ArrayList<>();
        searchLore.add(ChatColor.GRAY + "Search Items");
        searchLore.add("");
        searchLore.add(ChatColor.GRAY + "Filtered: " + filtered);
        searchLore.add("");
        searchLore.add(ChatColor.AQUA + "Right click to clear");
        searchLore.add(ChatColor.YELLOW + "Click to edit filter");
        searchItemMeta.setLore(searchLore);
        searchItemStack.setItemMeta(searchItemMeta);
        inventory.setItem(SEARCH_INDEX, searchItemStack);
    }

    public void updateCurrentSearchUIItems() {
        if (currentSearch.isEmpty()) {
            updateAllItems();
            openPage(currentPage);
        } else {
            search(currentSearch);
            openPage(currentPage);
        }
    }

    public abstract void updateAllItems();

}
