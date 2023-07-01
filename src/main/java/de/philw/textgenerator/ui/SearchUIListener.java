package de.philw.textgenerator.ui;

import de.philw.textgenerator.TextGenerator;
import de.philw.textgenerator.letters.CurrentEditedText;
import de.philw.textgenerator.manager.ConfigManager;
import de.philw.textgenerator.manager.MessagesManager;
import de.philw.textgenerator.ui.value.Block;
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
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class SearchUIListener implements Listener {

    private final HashMap<UUID, SearchUI> searchUISToListenTo;
    //This arrayList is for all players who open an anvil gui to search and close the search UI inventory, so it
    // knows that
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
        for (UUID uuid : searchUISToListenTo.keySet()) {
            if (player.getUniqueId().equals(uuid)) {
                clickedSearchUI = searchUISToListenTo.get(uuid);
            }
        }
        if (clickedSearchUI == null) return;
        if (e.getCurrentItem() == null) return;
        if (!e.getView().getTitle().equals(clickedSearchUI.inventoryDisplay)) return;
        e.setCancelled(true);
        switch (e.getRawSlot()) {
            case SearchUI.RETURN_ARROW_INDEX:
                new SettingsUI(player);
                searchUISToListenTo.remove(player.getUniqueId());
                break;
            case SearchUI.PREVIOUS_PAGE_INDEX:
                if (e.getClick().isLeftClick()) {
                    clickedSearchUI.openPage(clickedSearchUI.currentPage - 1);
                } else if (e.getClick().isRightClick()) {
                    clickedSearchUI.openPage(1);
                }
                break;
            case SearchUI.NEXT_PAGE_INDEX:
                if (e.getClick().isLeftClick()) {
                    clickedSearchUI.openPage(clickedSearchUI.currentPage + 1);
                } else if (e.getClick().isRightClick()) {
                    clickedSearchUI.openPage(UIUtil.getMaxPage(clickedSearchUI.searchedItems, SearchUI.SPACES));
                }
                break;
            case SearchUI.SEARCH_INDEX:
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
            default:
                if (e.getRawSlot() >= SearchUI.RETURN_ARROW_INDEX) return;
                if (clickedSearchUI.inventory.getItem(e.getRawSlot()) == null) return;
                String[] information =
                        Objects.requireNonNull(Objects.requireNonNull(clickedSearchUI.inventory.getItem(e.getRawSlot())).
                                getItemMeta()).getLocalizedName().split(";");
                switch (Integer.parseInt(information[0])) {
                    case SearchUI.BLOCK_SEARCH_UI:
                        blockSearchUIValueClicked(player, information);
                        break;
                    case SearchUI.FONT_SIZE_SEARCH_UI:
                        fontSizeSearchUIValueClicked(player, information);
                        break;
                    case SearchUI.LINE_SPACING_SEARCH_UI:
                        lineSpacingSearchUIValueClicked(player, information);
                        break;
                }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        if (notRemove.contains(e.getPlayer().getUniqueId())) {
            notRemove.remove(e.getPlayer().getUniqueId());
            return;
        }
        UUID toRemove = null;
        for (UUID uuid : searchUISToListenTo.keySet()) {
            if (player.getUniqueId().equals(uuid) && e.getView().getTitle().contains(searchUISToListenTo.get(uuid).inventoryDisplay)) {
                toRemove = uuid;
                break;
            }
        }
        searchUISToListenTo.remove(toRemove);
    }

    public void addSearchUI(UUID uuid, SearchUI searchUI) {
        searchUISToListenTo.put(uuid, searchUI);
    }

    public HashMap<UUID, SearchUI> getSearchUISToListenTo() {
        return searchUISToListenTo;
    }

    private void blockSearchUIValueClicked(Player player, String[] information) {
        Block block = Block.valueOf(information[1]);
        if (!TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().containsKey(player.getUniqueId())) {
            ConfigManager.setBlock(block);
            for (SearchUI searchUI : this.getSearchUISToListenTo().values()) {
                if (!(searchUI instanceof BlockSearchUI)) continue;
                searchUI.updateCurrentSearchUIItems();
            }
        } else {
            CurrentEditedText currentEditedText =
                    TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().get(player.getUniqueId());
            if (block == currentEditedText.getTextInstance().getBlock()) {
                player.sendMessage(MessagesManager.getMessage("changedValueOfCurrentText" +
                        ".deniedBecauseValueAlreadySelected", "block", block.getDisplay()));
            } else {
                if (!currentEditedText.setBlock(block)) {
                    player.sendMessage(MessagesManager.getMessage("changedValueOfCurrentText.deniedBecauseBlockHasNoSlabOrStairAndSpecificFontSizeIsSelected", currentEditedText.getTextInstance().getBlock().getDisplay()));
                } else {
                    player.sendMessage(MessagesManager.getMessage("changedValueOfCurrentText.success", "block",
                            block.getDisplay()));
                }
            }
            player.closeInventory();
        }
    }

    private void fontSizeSearchUIValueClicked(Player player, String[] information) {
        int fontSize = Integer.parseInt(information[1]);
        if (!TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().containsKey(player.getUniqueId())) {
            ConfigManager.setFontSize(fontSize);
            for (SearchUI searchUI :
                    TextGenerator.getInstance().getSearchUIListener().getSearchUISToListenTo().values()) {
                if (!(searchUI instanceof FontSizeSearchUI)) continue;
                searchUI.updateCurrentSearchUIItems();
            }
        } else {
            CurrentEditedText currentEditedText =
                    TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().get(player.getUniqueId());
            if (fontSize == currentEditedText.getTextInstance().getFontSize()) {
                player.sendMessage(MessagesManager.getMessage("changedValueOfCurrentText" +
                        ".deniedBecauseValueAlreadySelected", "font size", fontSize));
            } else {
                int changeValid = currentEditedText.setFontSize(fontSize);
                if (changeValid == CurrentEditedText.VALID) {
                    player.sendMessage(MessagesManager.getMessage("changedValueOfCurrentText.success", "font size",
                            fontSize));
                } else if (changeValid == CurrentEditedText.BLOCK_HAS_NO_SLAB_OR_STAIR) {
                    player.sendMessage(MessagesManager.getMessage("changedValueOfCurrentText.deniedBecauseBlockHasNoSlabOrStairAndSpecificFontSizeIsSelected", currentEditedText.getTextInstance().getBlock().getDisplay())); // TESTEN
                } else if (changeValid == CurrentEditedText.TEXT_IS_NOT_VALID_FOR_SPECIFIC_FONT_SIZE) {
                    player.sendMessage(MessagesManager.getMessage("changedValueOfCurrentText.deniedBecauseSpecificFontSizeIsSelectedButTextIsNotValid", currentEditedText.getTextInstance().getText())); // TESTEN
                }
            }
            player.closeInventory();
        }
    }

    private void lineSpacingSearchUIValueClicked(Player player, String[] information) {
        int lineSpacing = Integer.parseInt(information[1]);
        if (!TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().containsKey(player.getUniqueId())) {
            ConfigManager.setLineSpacing(lineSpacing);
            for (SearchUI searchUI :
                    TextGenerator.getInstance().getSearchUIListener().getSearchUISToListenTo().values()) {
                if (!(searchUI instanceof LineSpacingSearchUI)) continue;
                searchUI.updateCurrentSearchUIItems();
            }
        } else {
            CurrentEditedText currentEditedText =
                    TextGenerator.getInstance().getTextGeneratorCommand().getCurrentEditedTexts().get(player.getUniqueId());
            if (lineSpacing == currentEditedText.getTextInstance().getLineSpacing()) {
                player.sendMessage(MessagesManager.getMessage("changedValueOfCurrentText" +
                        ".deniedBecauseValueAlreadySelected", "line spacing", lineSpacing));
            } else {
                player.sendMessage(MessagesManager.getMessage("changedValueOfCurrentText.success", "line spacing",
                        lineSpacing));
                currentEditedText.setLineSpacing(lineSpacing);
            }
            player.closeInventory();
        }
    }

}