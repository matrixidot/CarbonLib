package me.neo.carbonlib.gui.type;

import me.neo.carbonlib.gui.misc.IHolder;
import me.neo.carbonlib.gui.misc.InventoryCache;
import me.neo.carbonlib.gui.misc.InventoryObject;
import me.neo.carbonlib.gui.misc.InventoryRows;
import me.neo.carbonlib.gui.pattern.InventoryPattern;
import me.neo.carbonlib.item.CarbonInventoryItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import static me.neo.carbonlib.utils.Util.getOrDefault;

public class CarbonInventoryBuilder {
    private String inventoryName;
    private InventoryRows rows;
    private String title;

    private Consumer<InventoryClickEvent> clickEventConsumer = e -> {};
    private Consumer<InventoryCloseEvent> closeEventConsumer = e -> {};
    private Consumer<InventoryDragEvent> dragEventConsumer = e -> {};

    private Inventory lastInventory;

    private final HashMap<Integer, CarbonInventoryItem> items = new HashMap<>();

    public CarbonInventoryBuilder(String title, InventoryRows rows) {
        this.title = title;
        this.rows = rows;
        this.inventoryName = ChatColor.translateAlternateColorCodes('&', title);
    }

    public CarbonInventoryBuilder(String inventoryName, String title, InventoryRows rows) {
        this.title = title;
        this.rows = rows;
        this.inventoryName = ChatColor.translateAlternateColorCodes('&', title);
    }

    public InventoryRows getRows() {
        return rows;
    }

    public int getSize() {
        return getRows().getSize();
    }

    public CarbonInventoryBuilder setRows(InventoryRows rows) {
        this.rows = rows;
        return this;
    }

    public String getTitle() {
        return ChatColor.translateAlternateColorCodes('&', title);
    }

    public CarbonInventoryBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public Inventory build() {
        Inventory inventory = Bukkit.createInventory((IHolder) () -> inventoryName, getSize(), getTitle());
        for (int slot : items.keySet()) {
            if (slot >= getSize()) {
                continue;
            }
            ItemStack item = items.get(slot).getItemStack();
            inventory.setItem(slot, item);
        }
        setLastInventory(inventory);
        return inventory;
    }

    public Consumer<InventoryClickEvent> getClickEventConsumer() {
        return clickEventConsumer;
    }

    public CarbonInventoryBuilder setClickEvent(Consumer<InventoryClickEvent> clickEventConsumer) {
        this.clickEventConsumer = clickEventConsumer;
        return this;
    }

    public Consumer<InventoryCloseEvent> getCloseEventConsumer() {
        return closeEventConsumer;
    }

    public CarbonInventoryBuilder setCloseEvent(Consumer<InventoryCloseEvent> closeEventConsumer) {
        this.closeEventConsumer = closeEventConsumer;
        return this;
    }

    public Consumer<InventoryDragEvent> getDragEventConsumer() {
        return dragEventConsumer;
    }

    public CarbonInventoryBuilder setDragEvent(Consumer<InventoryDragEvent> dragEventConsumer) {
        this.dragEventConsumer = dragEventConsumer;
        return this;
    }

    public CarbonInventoryBuilder setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
        return this;
    }

    public CarbonInventoryBuilder addPlayer(Player player) {
        if (player == null) {
            return this;
        }
        UUID uuid = player.getUniqueId();
        InventoryCache.getCache().setLastInventory(uuid, new InventoryObject(this));
        player.openInventory(getLastInventory());
        return this;
    }

    public CarbonInventoryBuilder addPlayers(@NotNull List<Player> players) {
        players.forEach(this::addPlayer);
        return this;
    }

    public Inventory getLastInventory() {
        return getOrDefault(lastInventory, build());
    }

    public CarbonInventoryBuilder setLastInventory(Inventory lastInventory) {
        this.lastInventory = lastInventory;
        return this;
    }

    public CarbonInventoryBuilder setNoCloseable() {
        closeEventConsumer = e -> addPlayer((Player) e.getPlayer());
        return this;
    }

    public Optional<CarbonInventoryItem> getItem(int slot) {
        return Optional.ofNullable(items.get(slot));
    }

    public CarbonInventoryBuilder setItem(CarbonInventoryItem item, int... slots) {
        if (slots == null || slots.length == 0) {
            return this;
        }
        for (int slot : slots) {
            if (items.containsKey(slot)) items.replace(slot, item);
            else items.put(slot,item);
        }
        return this;
    }

    public CarbonInventoryBuilder setItem(Material material, int... slots) {
        return setItem(new CarbonInventoryItem(material), slots);
    }

    public CarbonInventoryBuilder setBlockItem(Material material, int... slots) {
        CarbonInventoryItem item = new CarbonInventoryItem(material)
                .setClickAction(e -> e.setCancelled(true))
                .setDragAction(e -> e.setCancelled(true));
        return setItem(item, slots);
    }

    public CarbonInventoryBuilder setBlockItem(@NotNull CarbonInventoryItem item, int... slots) {
        item.setClickAction(e -> e.setCancelled(true))
                .setDragAction(e -> e.setCancelled(true));
        return setItem(item, slots);
    }

    public CarbonInventoryBuilder setPattern(@NotNull InventoryPattern pattern) {
        String[] strings = pattern.getSplitPattern();
        int i = 0;
        for (String key : strings) {
            setItem(pattern.getItem(key, Material.AIR), i);
            i++;
        }
        return this;
    }

    public String getInventoryName() {
        return inventoryName;
    }
}
