package me.neo.carbonlib.gui.type;

import me.neo.carbonlib.gui.misc.CarbonInventoryCache;
import me.neo.carbonlib.gui.misc.CarbonInventoryObject;
import me.neo.carbonlib.gui.misc.CarbonInventoryRows;
import me.neo.carbonlib.gui.misc.CarbonInventorySpecialData;
import me.neo.carbonlib.item.CarbonInventoryItem;
import me.neo.carbonlib.misc.CarbonParticle;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.checkerframework.checker.units.qual.K;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class CarbonPageInventoryBuilder extends CarbonInventoryBuilder{
    private int currentPage;
    private int maxItemsPerPage;

    private final HashMap<Integer, Inventory> lastInventories = new HashMap<>();
    private final Map<Integer, CarbonInventoryItem> finalItems = new HashMap<>();

    private List<Map<Integer, CarbonInventoryItem>> pages = new ArrayList<>();

    public CarbonPageInventoryBuilder(String title, CarbonInventoryRows rows) {
        super(title, rows);
    }

    public CarbonPageInventoryBuilder setItems(@NotNull List<CarbonInventoryItem> items) {
        List<Map<Integer, CarbonInventoryItem>> pages = new ArrayList<>();
        int iPage = 0;
        int i = 0;
        Map<Integer, CarbonInventoryItem> map = new HashMap<>();

        for (CarbonInventoryItem item : items) {
            if (i >= getMaxItemsPerPage()) {
                pages.add(iPage, map);
                iPage++;
                i = 0;
                map = new HashMap<>();
            }
            if (map.containsKey(i)) map.replace(i, item);
            else map.put(i, item);
            i++;
        }
        pages.add(iPage, map);

        this.pages = pages;
        return this;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public CarbonPageInventoryBuilder nextPage(Player player) {
        if (currentPage + 1 >= getPages().size()) return this;

        currentPage++;
        openPage(player, currentPage);
        return this;
    }

    public CarbonPageInventoryBuilder previousPage(Player player) {
        if (currentPage - 1 < 0) return this;
        currentPage--;
        openPage(player, currentPage);
        return this;
    }

    public boolean hasPreviousPage() {
        return currentPage -1 > 0;
    }
    public List<Map<Integer, CarbonInventoryItem>> getPages() {
        return pages;
    }
    public Map<Integer, CarbonInventoryItem> getMaxItemsFromPage(int page) {
        if (page - 1 > pages.size()) return new HashMap<>();
        Map<Integer, CarbonInventoryItem> map = pages.get(page);
        for (int slot : finalItems.keySet()) {
            if (map.containsKey(slot)) map.replace(slot, finalItems.getOrDefault(slot, null));
            else map.put(slot, finalItems.getOrDefault(slot, null));
        }
        return map;
    }

    public int getMaxItemsPerPage() {
        return maxItemsPerPage;
    }

    public CarbonPageInventoryBuilder setMaxItemsPerPage(int maxItemsPerPage) {
        if (getSize() -1 > maxItemsPerPage) {
            this.maxItemsPerPage = maxItemsPerPage;
        }
        return this;
    }

    public CarbonPageInventoryBuilder openPage(@NotNull Player player, int page) {
        currentPage = page;
        player.openInventory(getLastInventory(page));
        CarbonInventoryCache.getCache().setLastInventory(player.getUniqueId(), new CarbonInventoryObject(this));
        return this;
    }

    public Inventory build(int page) {
        Inventory inventory = super.build();
        Map<Integer, CarbonInventoryItem> map = getMaxItemsFromPage(page);
        for (int slot : map.keySet()) {
            CarbonInventoryItem item = map.getOrDefault(slot, null);
            if (item == null) continue;
            inventory.setItem(slot, item.getItemStack());
        }
        return inventory;
    }

    @Override
    public CarbonPageInventoryBuilder addPlayer(Player player) {
        return openPage(player, 0);
    }
    public Inventory getLastInventory(int page) {
        if (lastInventories.get(page) == null) return build(page);
        else return lastInventories.get(page);

    }

    public CarbonPageInventoryBuilder setLastInventory(Inventory inventory, int page) {
        if (lastInventories.containsKey(page)) lastInventories.replace(page, inventory);
        else lastInventories.put(page, inventory);
        return this;
    }

    public CarbonPageInventoryBuilder setItem(Material material, int page, int... slots) {
        return setItem(new CarbonInventoryItem(material), page, slots);
    }
    public CarbonPageInventoryBuilder setBlockItem(Material material, int page, int... slots) {
        CarbonInventoryItem item = new CarbonInventoryItem(material)
                .setClickAction(e -> e.setCancelled(true))
                .setDragAction(e -> e.setCancelled(true));
        return setItem(item, page, slots);
    }

    public CarbonPageInventoryBuilder setItem(CarbonInventoryItem item, int page, int... slots) {
        if (slots == null || slots.length == 0) return this;

        for (int slot : slots) {
            if (pages.get(page) == null) pages.get(page).put(slot, item);
            else pages.get(page).replace(slot, item);
        }
        return this;
    }

    public CarbonPageInventoryBuilder setBlockItem(@NotNull CarbonInventoryItem item, int page, int... slots) {
        item.setClickAction(e -> e.setCancelled(true)).setDragAction(e -> e.setCancelled(true));
        return setItem(item, page, slots);
    }

    public CarbonPageInventoryBuilder setFinalItem(@NotNull CarbonInventoryItem item, int @NotNull ... slots) {
        if (item.hasSpecialData(CarbonInventorySpecialData.PAGED_INVENTORY_PREVIOUS_PAGE)) {
            item.setClickAction(e -> {
                e.setCancelled(true);
                previousPage((Player) e.getWhoClicked());
            });
        }
        if (item.hasSpecialData(CarbonInventorySpecialData.PAGED_INVENTORY_NEXT_PAGE)) {
            item.setClickAction(e -> {
                e.setCancelled(true);
                nextPage((Player) e.getWhoClicked());
            });
        }
        for (int slot : slots) {
            if (finalItems.containsKey(slot)) finalItems.replace(slot, item);
            else finalItems.put(slot, item);
        }
        return this;
    }

    public CarbonPageInventoryBuilder setFinalItem(Material material, int @NotNull ... slots) {
        return setFinalItem(new CarbonInventoryItem(material), slots);
    }

    public Optional<CarbonInventoryItem> getItem(int page, int slot) {
        return Optional.ofNullable(getItemsFromPage(page).get(slot));
    }
    public Map<Integer, CarbonInventoryItem> getItemsFromPage(int page) {
        if (page -1 > pages.size()) return new HashMap<>();
        Map<Integer, CarbonInventoryItem> map = pages.get(page);
        for (int slot : finalItems.keySet()) {
            if (map.containsKey(slot)) map.replace(slot, finalItems.getOrDefault(slot, null));
            else map.put(slot, finalItems.getOrDefault(slot, null));
        }
        return map;
    }

    public CarbonPageInventoryBuilder setFinalBlockItem(@NotNull CarbonInventoryItem item, int... slots) {
        item.setClickAction(e -> e.setCancelled(true)).setDragAction(e -> e.setCancelled(true));
        return setFinalItem(item, slots);
    }
    public CarbonPageInventoryBuilder setFinalBlockItem(@NotNull Material material, int... slots) {
        CarbonInventoryItem item = new CarbonInventoryItem(material);
        item.setClickAction(e -> e.setCancelled(true)).setDragAction(e -> e.setCancelled(true));
        return setFinalBlockItem(item, slots);
    }
}

