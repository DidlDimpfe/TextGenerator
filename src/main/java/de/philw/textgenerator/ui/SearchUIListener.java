package de.philw.textgenerator.ui;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.utils.UIUtil;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class SearchUIListener implements Listener {

    private final ArrayList<SearchUI> searchUISToListenTo;

    public SearchUIListener() {
        searchUISToListenTo = new ArrayList<>();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        SearchUI clickedSearchUI = searchUISToListenTo.stream().filter(searchUI -> ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).contains(searchUI.name)).findFirst().orElse(null);
        if (clickedSearchUI == null) return;
        if (e.getCurrentItem() == null) return;
        Player player = (Player) e.getWhoClicked();
        e.setCancelled(true);
        switch (e.getRawSlot()) {
            case SearchUI.RETURN_ARROW_INDEX:
                new SettingsUI(player);
                searchUISToListenTo.remove(clickedSearchUI);
                break;
            case SearchUI.PREVIOUS_PAGE_INDEX:
                if (e.getClick().isLeftClick()) {
                    clickedSearchUI.openPage(clickedSearchUI.currentPage-1);
                } else if (e.getClick().isRightClick()) {
                    clickedSearchUI.openPage(1);
                }
                break;
            case SearchUI.NEXT_PAGE_INDEX:
                if (e.getClick().isLeftClick()) {
                    clickedSearchUI.openPage(clickedSearchUI.currentPage+1);
                } else if (e.getClick().isRightClick()) {
                    clickedSearchUI.openPage(UIUtil.getMaxPage(clickedSearchUI.searchedItems, SearchUI.SPACES));
                }
                break;
            case SearchUI.SEARCH_INDEX:
                if (e.getClick().isRightClick()) {
                    clickedSearchUI.search("");
                } else if (e.getClick().isLeftClick()) {
                    new AnvilGUI.Builder()
                            .onComplete((target, text) -> {
                                clickedSearchUI.search(text);
                                return AnvilGUI.Response.openInventory(clickedSearchUI.inventory);
                            })
                            .title(clickedSearchUI.searchDisplayTitle)
                            .itemLeft(new ItemStack(Material.NAME_TAG))
                            .text(" ")
                            .plugin(TextGenerator.getInstance())
                            .open(player);

                }
                break;
        }
    }

    public void addSearchUI(SearchUI searchUI){
        searchUISToListenTo.add(searchUI);
    }
}
