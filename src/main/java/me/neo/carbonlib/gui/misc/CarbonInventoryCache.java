package me.neo.carbonlib.gui.misc;

import me.neo.carbonlib.gui.InventoryListener;
import me.neo.carbonlib.utils.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class CarbonInventoryCache {
    private static CarbonInventoryCache cache;
    private final Map<UUID, CarbonInventoryObject> lastInventories = new HashMap<>();

    public CarbonInventoryCache() {
        cache = this;
        new InventoryListener();
    }

    public static CarbonInventoryCache getCache() {
        return Util.getOrDefault(cache, new CarbonInventoryCache());
    }
    public void setLastInventory(UUID uuid, CarbonInventoryObject builder) {
        if (lastInventories.containsKey(uuid)) lastInventories.replace(uuid, builder);
        else lastInventories.put(uuid, builder);
    }

    public Optional<CarbonInventoryObject> getLastInventory(UUID uuid) {
        return Optional.ofNullable(lastInventories.get(uuid));
    }
    public void removeLastInventory(UUID uuid) {
        lastInventories.remove(uuid);
    }
}
