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

/**
 * Represents an item inside a menu
 */
public class CarbonInventoryItem implements Serializable {
    private CarbonItem item;
    private Consumer<InventoryClickEvent> clickAction = e -> {};
    private Consumer<InventoryDragEvent> dragAction = e -> {};

    /**
     * Creates a new CarbonInventoryItem with the specified material.
     * @param material The material of the item.
     */
    public CarbonInventoryItem(Material material) {
        this.item = new CarbonItem(new ItemStack(material));
    }

    /**
     * Creates a new CarbonInventoryItem with the specified material and name.
     * @param material The material of the item.
     * @param name The name of the item.
     */
    public CarbonInventoryItem(Material material, String name) {
        this.item = new CarbonItem(new ItemStack(material)).setDisplayName(name);
    }
    /**
     * Creates a new CarbonInventoryItem with the specified material and name.
     * @param material The material of the item.
     * @param name The name of the item.
     * @param lore The lore of the item.
     */
    public CarbonInventoryItem(Material material, String name, String... lore) {
        this.item = new CarbonItem(new ItemStack(material)).setDisplayName(name).setLore(lore);
    }

    /**
     * Creates a new CarbonInventoryItem from an existing ItemStack.
     * @param stack The ItemStack to use
     */
    public CarbonInventoryItem(ItemStack stack) {
        this.item = new CarbonItem(stack);
    }

    /**
     * Creates a new CarbonInventoryItem from an existing CarbonItem
     * @param item The CarbonItem to use
     */
    public CarbonInventoryItem(CarbonItem item) {
        this.item = item;
    }

    /**
     * Creates a new CarbonInventoryItem from an existing CarbonItem along with a click action.
     * @param item The CarbonItem to use
     * @param clickAction A lambda representing code that would run in an @EventHandler method.
     */
    public CarbonInventoryItem(CarbonItem item, Consumer<InventoryClickEvent> clickAction) {
        this.item = item;
        this.clickAction = clickAction;
    }
    /**
     * Creates a new CarbonInventoryItem from an existing CarbonItem along with a click action.
     * @param item The CarbonItem to use
     * @param clickAction A lambda representing code that would run in an @EventHandler method.
     * @param dragAction A lambda representing code that would run in an @EventHandler method.
     */
    public CarbonInventoryItem(CarbonItem item, Consumer<InventoryClickEvent> clickAction, Consumer<InventoryDragEvent> dragAction) {
        this.item = item;
        this.dragAction = dragAction;
        this.clickAction = clickAction;
    }

    /**
     * Gets the CarbonItem tied to this CarbonInventoryItem.
     * @return The CarbonItem tied to this CarbonInventoryItem.
     */
    public CarbonItem getItem() {
        return item;
    }

    public CarbonInventoryItem getItemConsumer(@NotNull Consumer<CarbonItem> consumer) {
        CarbonItem builder = getItem();
        consumer.accept(builder);
        return setItem(builder);
    }

    /**
     * Gets the ItemStack tied to this CarbonInventoryItem
     * @return The ItemStack tied to this CarbonInventoryItem
     */
    public ItemStack getItemStack() {
        CarbonItemCache.getCache().addInvItem(item.invItemForge(), new CarbonItemObject(this));
        return item.invItemForge();
    }

    /**
     * Gets the InventoryClickEvent tied to this CarbonInventoryItem
     * @return The InventoryClickEvent
     */
    public Consumer<InventoryClickEvent> getClickAction() {
        return clickAction;
    }

    /**
     * Sets an InventoryClickEvent for this specific CarbonInventoryItem
     * @param clickAction A lambda representing code that would run in an @EventHandler method
     * @return CarbonInventoryItem
     */
    public CarbonInventoryItem setClickAction(Consumer<InventoryClickEvent> clickAction) {
        this.clickAction = clickAction;
        return this;
    }

    /**
     * Gets the InventoryDragEvent tied to this CarbonInventoryItem
     * @return The InventoryDragEvent
     */
    public Consumer<InventoryDragEvent> getDragAction() {
        return dragAction;
    }

    /**
     * Sets an InventoryClickEvent for this specific CarbonInventoryItem
     * @param dragAction A lambda representing code that would run in an @EventHandler method
     * @return CarbonInventoryItem
     */
    public CarbonInventoryItem setDragAction(Consumer<InventoryDragEvent> dragAction) {
        this.dragAction = dragAction;
        return this;
    }

    /**
     * Makes this item completely inert. This item will not be able to do anything.
     * @return A CarbonInventoryItem
     */
    public CarbonInventoryItem setBlockItem() {
        setClickAction(e -> e.setCancelled(true)).setDragAction(e -> e.setCancelled(true));
        return this;
    }

    /**
     * Makes this item close the inventory when clicked.
     * @return CarbonInventoryItem
     */
    public CarbonInventoryItem setCloseItem() {
        setClickAction(e -> {
            e.setCancelled(true);
            e.getWhoClicked().closeInventory();
        });
        setDragAction(e -> {
            e.setCancelled(true);
            e.getWhoClicked().closeInventory();
        });
        return this;
    }

    /**
     * Sets the current item tied to this CarbonInventoryItem to a different CarbonItem
     * @param item The CarbonItem to set this CarbonInventoryItem to.
     * @return CarbonInventoryItem
     */
    public CarbonInventoryItem setItem(CarbonItem item) {
        this.item = item;
        return this;
    }
}
