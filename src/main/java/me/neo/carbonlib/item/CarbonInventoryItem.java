package me.neo.carbonlib.item;

import me.neo.carbonlib.gui.misc.CarbonInventorySpecialData;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class CarbonInventoryItem {
    private CarbonItem item;
    private Consumer<InventoryClickEvent> clickAction = event -> {};
    private Consumer<InventoryDragEvent> dragAction = event -> {};
    private List<CarbonInventorySpecialData> specialData;

    /**
     * Sets the material of the item in the inventory
     * @param material The Material of the ItemStack
     */
    public CarbonInventoryItem(Material material) {
        this.item = new CarbonItem(new ItemStack(material));
    }

    /**
     * Sets the Material and DisplayName of the item in the inventory
     * @param material Material of the itemStack
     * @param displayName DisplayName of the item
     */
    public CarbonInventoryItem(Material material, String displayName) {
        this.item = new CarbonItem(new ItemStack(material)).setDisplayName(displayName);
    }

    /**
     * Sets the Material and the Special Data of the item in the inventory (Used for forward and backward buttons)
     * @param material Material of the item
     * @param specialData The CarbonInventorySpecialData value chosen as a list
     */
    public CarbonInventoryItem(Material material, List<CarbonInventorySpecialData> specialData) {
        this.item = new CarbonItem(new ItemStack(material));
        this.specialData = specialData;
    }

    /**
     * Sets the material and the Special Data of the item in the inventory (Used for forward and backward buttons)
     * @param material Material of the item
     * @param specialData The CarbonInventorySpecialData value chosen.
     */
    public CarbonInventoryItem(Material material, CarbonInventorySpecialData specialData) {
        this.item = new CarbonItem(new ItemStack(material));
        this.specialData = List.of(specialData);
    }

    /**
     * Sets the ItemStack of the item in the inventory
     * @param item the CarbonItem to use in the inventory
     */
    public CarbonInventoryItem(CarbonItem item) {
        this.item = item;
    }

    /**
     * Sets the ItemStack and the special data of the item in the inventory
     * @param item The CarbonItem to use in the inventory
     * @param specialData The value of the Special Data
     */
    public CarbonInventoryItem(CarbonItem item, CarbonInventorySpecialData specialData) {
        this.item = item;
        this.specialData = List.of(specialData);
    }

    /**
     * Sets the ItemStack and the special data of the item in the inventory
     * @param item The CarbonItem to use in the inventory
     * @param specialData The value of the Special Data as a List
     */
    public CarbonInventoryItem(CarbonItem item, List<CarbonInventorySpecialData> specialData) {
        this.item = item;
        this.specialData = specialData;
    }

    /**
     * Sets the ItemStack, its click action and its drag action
     * @param item The CarbonItem to use
     * @param clickAction The InventoryClickEvent to use
     * @param dragAction The InventoryDragEvent to use
     */
    public CarbonInventoryItem(CarbonItem item, Consumer<InventoryClickEvent> clickAction, Consumer<InventoryDragEvent> dragAction) {
        this.item = item;
        this.clickAction = clickAction;
        this.dragAction = dragAction;
    }

    /**
     * Sets the ItemStack and its click action
     * @param item the CarbonItem to use
     * @param clickAction The InventoryClickEvent to use
     */
    public CarbonInventoryItem(CarbonItem item, Consumer<InventoryClickEvent> clickAction) {
        this.item = item;
        this.clickAction = clickAction;
    }

    /**
     * Returns the item being created/used
     * @return CarbonItem
     */
    public CarbonItem getItem() {
        return item;
    }

    /**
     * Gets the consumer (Lambda) of this Item
     * @param consumer The Consumer
     * @return CarbonInventoryItem
     */
    public CarbonInventoryItem getItemConsumer(@NotNull Consumer<CarbonItem> consumer) {
        CarbonItem builder = getItem();
        consumer.accept(builder);
        return setItem(builder);
    }

    /**
     * Gets the ItemStack used in the inventory
     * @return ItemStack
     */
    public ItemStack getItemStack() {
        return item.forge();
    }

    /**
     * Gets the InventoryClickEvent related to this item.
     * @return Consumer InventoryClickEvent
     */
    public Consumer<InventoryClickEvent> getClickAction() {
        return clickAction;
    }

    /**
     * Sets the Click Action (InventoryClickEvent) of this item.
     * @param clickAction The InventoryClickEvent of this item
     * @return CarbonInventoryItem
     */
    public CarbonInventoryItem setClickAction(Consumer<InventoryClickEvent> clickAction) {
        this.clickAction = clickAction;
        return this;
    }

    /**
     * Gets the InventoryDragEvent related to this item
     * @return Consumer InventoryDragEvent
     */
    public Consumer<InventoryDragEvent> getDragAction() {
        return dragAction;
    }

    /**
     * Sets the Drag Event for this item
     * @param dragAction The InventoryDragEvent consumer to add to this item
     * @return CarbonInventoryItem
     */
    public CarbonInventoryItem setDragAction(Consumer<InventoryDragEvent> dragAction) {
        this.dragAction = dragAction;
        return this;
    }

    /**
     * Sets this item to do nothing
     * @return CarbonInventoryItem
     */
    public CarbonInventoryItem setBlockItem() {
        setClickAction(e -> e.setCancelled(true))
                .setDragAction(e -> e.setCancelled(true));
        return this;
    }

    /**
     * Sets the Item using a CarbonItem
     * @param item the CarbonItem to use as an item
     * @return CarbonInventoryItem
     */
    public CarbonInventoryItem setItem(CarbonItem item) {
        this.item = item;
        return this;
    }

    /**
     * Gets the Special Data value of the item
     * @return List CarbonInventorySpecialData
     */
    public List<CarbonInventorySpecialData> getSpecialData() {
        return specialData;
    }

    /**
     * Sets the Special data of the item
     * @param specialData The CarbonInventorySpecialData list to add to this item
     * @return CarbonInventoryItem
     */
    public CarbonInventoryItem setSpecialData(List<CarbonInventorySpecialData> specialData) {
        this.specialData = specialData;
        return this;
    }

    /**
     * Checks if the item has the specified specialData
     * @param data the CarbonInventorySpecialData to check against
     * @return true or false
     */
    public boolean hasSpecialData(CarbonInventorySpecialData data) {
        if (getSpecialData() == null) return false;

        return getSpecialData().contains(data);
    }

}
