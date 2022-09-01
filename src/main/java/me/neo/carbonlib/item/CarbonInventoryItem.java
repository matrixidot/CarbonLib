package me.neo.carbonlib.item;

import me.neo.carbonlib.item.eventHandling.CarbonItemCache;
import me.neo.carbonlib.item.eventHandling.CarbonItemObject;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.function.Consumer;

public class CarbonInventoryItem implements Serializable {
    private CarbonItem item;
    private Consumer<InventoryClickEvent> clickAction = e -> {};
    private Consumer<InventoryDragEvent> dragAction = e -> {};

    public CarbonInventoryItem(Material material) {
        this.item = new CarbonItem(new ItemStack(material));
    }
    public CarbonInventoryItem(Material material, String name) {
        this.item = new CarbonItem(new ItemStack(material)).setDisplayName(name);
    }
    public CarbonInventoryItem(CarbonItem item, Consumer<InventoryClickEvent> clickAction, Consumer<InventoryDragEvent> dragAction) {
        this.item = item;
        this.dragAction = dragAction;
        this.clickAction = clickAction;
    }
    public CarbonInventoryItem(CarbonItem item, Consumer<InventoryClickEvent> clickAction) {
        this.item = item;
        this.clickAction = clickAction;
    }

    public CarbonItem getItem() {
        return item;
    }

    public CarbonInventoryItem getItemConsumer(@NotNull Consumer<CarbonItem> consumer) {
        CarbonItem builder = getItem();
        consumer.accept(builder);
        return setItem(builder);
    }

    public ItemStack getItemStack() {
        CarbonItemCache.getCache().addInvItem(item.invItemForge(), new CarbonItemObject(this));
        return item.invItemForge();
    }

    public Consumer<InventoryClickEvent> getClickAction() {
        return clickAction;
    }
    public CarbonInventoryItem setClickAction(Consumer<InventoryClickEvent> clickAction) {
        this.clickAction = clickAction;
        return this;
    }

    public Consumer<InventoryDragEvent> getDragAction() {
        return dragAction;
    }

    public CarbonInventoryItem setDragAction(Consumer<InventoryDragEvent> dragAction) {
        this.dragAction = dragAction;
        return this;
    }

    public CarbonInventoryItem setBlockItem() {
        setClickAction(e -> e.setCancelled(true)).setDragAction(e -> e.setCancelled(true));
        return this;
    }

    public CarbonInventoryItem setItem(CarbonItem item) {
        this.item = item;
        return this;
    }
}
