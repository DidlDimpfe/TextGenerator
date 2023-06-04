package de.philw.textgenerator.ui;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.utils.UIUtil;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class SearchUIListener implements Listener {

    private final HashMap<UUID, SearchUI> searchUISToListenTo;
    //This arrayList is for all players who open an anvil gui to search and close the search UI inventory, so it knows that
    //this particular search UI should not be removed
    private final ArrayList<UUID> notRemove;

    public SearchUIListener() {
        searchUISToListenTo = new HashMap<>();
        notRemove = new ArrayList<>();
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        SearchUI clickedSearchUI = null;
        Player player = (Player) e.getWhoClicked();
        for (UUID uuid: searchUISToListenTo.keySet()) {
            if (player.getUniqueId().equals(uuid)) {
                clickedSearchUI = searchUISToListenTo.get(uuid);
            }
        }
        if (clickedSearchUI == null) return;
        if (e.getCurrentItem() == null) return;
        switch (e.getRawSlot()) {
            case SearchUI.RETURN_ARROW_INDEX:
                e.setCancelled(true);
                new SettingsUI(player);
                searchUISToListenTo.remove(player.getUniqueId());
                break;
            case SearchUI.PREVIOUS_PAGE_INDEX:
                e.setCancelled(true);
                if (e.getClick().isLeftClick()) {
                    clickedSearchUI.openPage(clickedSearchUI.currentPage - 1);
                } else if (e.getClick().isRightClick()) {
                    clickedSearchUI.openPage(1);
                }
                break;
            case SearchUI.NEXT_PAGE_INDEX:
                e.setCancelled(true);
                if (e.getClick().isLeftClick()) {
                    clickedSearchUI.openPage(clickedSearchUI.currentPage + 1);
                } else if (e.getClick().isRightClick()) {
                    clickedSearchUI.openPage(UIUtil.getMaxPage(clickedSearchUI.searchedItems, SearchUI.SPACES));
                }
                break;
            case SearchUI.SEARCH_INDEX:
                e.setCancelled(true);
                notRemove.add(player.getUniqueId());
                if (e.getClick().isRightClick()) {
                    clickedSearchUI.search("");
                } else if (e.getClick().isLeftClick()) {
                    AtomicBoolean completed = new AtomicBoolean(false);
                    SearchUI finalClickedSearchUI = clickedSearchUI;
                    new AnvilGUI.Builder()
                            .onComplete((target, text) -> {
                                finalClickedSearchUI.search(text);
                                completed.set(true);
                                return AnvilGUI.Response.openInventory(finalClickedSearchUI.inventory);
                            })
                            .onClose(stateSnapshot -> {
                                if (!completed.get()) searchUISToListenTo.remove(player.getUniqueId());
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

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        if (notRemove.contains(e.getPlayer().getUniqueId())) {
            notRemove.remove(e.getPlayer().getUniqueId());
            return;
        }
        for (UUID uuid: searchUISToListenTo.keySet()) {
            if (player.getUniqueId().equals(uuid)) {
                if (e.getView().getTitle().contains(searchUISToListenTo.get(uuid).inventoryDisplay)) searchUISToListenTo.remove(uuid);
            }
        }
    }

    public void addSearchUI(UUID uuid, SearchUI searchUI) {
        searchUISToListenTo.put(uuid, searchUI);
    }
}