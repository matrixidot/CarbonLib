package me.neo.carbonlib.item.eventHandling;

import org.bukkit.inventory.ItemStack;

import java.util.*;

public class CarbonItemCache {
    protected final Map<String, CarbonItemObject> customItems = new HashMap<>();
    protected final Map<ItemStack, CarbonItemObject> customInvItems = new HashMap<>();
    private static CarbonItemCache cache;

    public CarbonItemCache() {
        cache = this;
    }

    public static CarbonItemCache getCache() { return cache; }
    // Adds or replaces an Item along with its builder
    public void addItem(String item, CarbonItemObject builder) {
        if (customItems.containsKey(item)) customItems.replace(item, builder);
        else customItems.put(item, builder);
    }
    // Returns the builder object
    public Optional<CarbonItemObject> getItem(String item) {
        return Optional.ofNullable(customItems.get(item));
    }
    public List<String> getItems() {
        return new ArrayList<>(customItems.keySet());
    }
    // Checks if the cache contains the exact copy of the item passed into the method.
    public boolean hasItem(String item) {
        return this.customItems.containsKey(item);
    }

    public void addInvItem(ItemStack item, CarbonItemObject builder) {
        if (customInvItems.containsKey(item)) customInvItems.replace(item, builder);
        else customInvItems.put(item, builder);
    }
    public Optional<CarbonItemObject> getInvItem(ItemStack item) {
        return Optional.ofNullable(customInvItems.get(item));
    }
    public boolean hasInvItem(ItemStack item) {return this.customInvItems.containsKey(item); }

}
