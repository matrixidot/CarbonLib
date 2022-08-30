package me.neo.carbonlib.gui.misc;
import me.neo.carbonlib.gui.InventoryListener;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import static me.neo.carbonlib.utils.Util.getOrDefault;
public class InventoryCache {
    private static InventoryCache cache;
    private final HashMap<Inventory, InventoryObject> Inventories = new HashMap<>();

    public InventoryCache() {
        cache = this;
        new InventoryListener();
    }

    public static InventoryCache getCache() {
        return getOrDefault(cache, new InventoryCache());
    }

    public void setInventory(Inventory inv, InventoryObject builder) {
        if (Inventories.containsKey(inv)) Inventories.replace(inv, builder);
        else Inventories.put(inv, builder);
    }

    public Optional<InventoryObject> getInventory(Inventory inv) {
        return Optional.ofNullable(Inventories.get(inv));
    }

    public void removeInventory(Inventory inv) {
        Inventories.remove(inv);
    }
}
