package me.neo.carbonlib.item;

import me.neo.carbonlib.utils.Util;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class CarbonItemCache {
    protected final Map<ItemStack, CarbonItemObject> customItems = new HashMap<>();
    private static CarbonItemCache cache;

    public CarbonItemCache() {
        cache = this;
    }

    public static CarbonItemCache getCache() { return cache; }
    // Adds or replaces an Item along with its builder
    public void addItem(ItemStack item, CarbonItemObject builder) {
        if (customItems.containsKey(item)) customItems.replace(item, builder);
        else customItems.put(item, builder);
        System.out.println(customItems);
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
