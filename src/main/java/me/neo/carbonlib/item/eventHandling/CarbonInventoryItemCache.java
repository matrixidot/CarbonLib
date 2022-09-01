package me.neo.carbonlib.item.eventHandling;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CarbonInventoryItemCache {
    protected final Map<ItemStack, CarbonItemObject> customItems = new HashMap<>();
    private static CarbonInventoryItemCache cache;

    public CarbonInventoryItemCache() {
        cache = this;
    }

    public static CarbonInventoryItemCache getCache() { return cache; }
    // Adds or replaces an Item along with its builder
    public void addItem(ItemStack item, CarbonItemObject builder) {
        if (customItems.containsKey(item)) customItems.replace(item, builder);
        else customItems.put(item, builder);
    }
    // Returns the builder object
    public Optional<CarbonItemObject> getItem(ItemStack item) {
        return Optional.ofNullable(customItems.get(item));
    }
    // Checks if the cache contains the exact copy of the item passed into the method.
    public boolean hasItem(ItemStack item) {
        return this.customItems.containsKey(item);
    }
}
