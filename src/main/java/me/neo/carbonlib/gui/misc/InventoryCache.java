package me.neo.carbonlib.gui.misc;
import me.neo.carbonlib.gui.InventoryListener;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import static me.neo.carbonlib.utils.Util.getOrDefault;
public class InventoryCache {
    private static InventoryCache cache;
    private final HashMap<UUID, InventoryObject> lastInventories = new HashMap<>();

    public InventoryCache() {
        cache = this;
        new InventoryListener();
    }

    public static InventoryCache getCache() {
        return getOrDefault(cache, new InventoryCache());
    }

    public void setLastInventory(UUID uuid, InventoryObject builder) {
        if (lastInventories.containsKey(uuid)) lastInventories.replace(uuid, builder);
        else lastInventories.put(uuid, builder);
    }

    public Optional<InventoryObject> getLastInventory(UUID uuid) {
        return Optional.ofNullable(lastInventories.get(uuid));
    }

    public void removeLastInventory(UUID uuid) {
        lastInventories.remove(uuid);
    }
}
