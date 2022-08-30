package me.neo.carbonlib.gui.misc;
import me.neo.carbonlib.gui.InventoryListener;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import static me.neo.carbonlib.utils.Util.getOrDefault;
public class InventoryCache {
    private static InventoryCache cache;
    private final HashMap<Inventory, InventoryObject> lastInventories = new HashMap<>();

    public InventoryCache() {
        cache = this;
        new InventoryListener();
    }

    public static InventoryCache getCache() {
        return getOrDefault(cache, new InventoryCache());
    }

    public void setLastInventory(Inventory inv, InventoryObject builder) {
        if (lastInventories.containsKey(inv)) lastInventories.replace(inv, builder);
        else lastInventories.put(inv, builder);
    }

    public Optional<InventoryObject> getLastInventory(Inventory inv) {
        return Optional.ofNullable(lastInventories.get(inv));
    }

    public void removeLastInventory(Inventory inv) {
        lastInventories.remove(inv);
    }
}
