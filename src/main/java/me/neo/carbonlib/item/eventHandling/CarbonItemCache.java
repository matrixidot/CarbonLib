package me.neo.carbonlib.item.eventHandling;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CarbonItemCache {
    protected final Map<String, CarbonItemObject> customItems = new HashMap<>();
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
    // Checks if the cache contains the exact copy of the item passed into the method.
    public boolean hasItem(String item) {
        return this.customItems.containsKey(item);
    }
}
