package me.neo.carbonlib.gui.misc;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public interface IHolder extends InventoryHolder {
    /**
     * Gets the name of the Inventory
     * @return The name
     */
    String getName();

    /**
     * Gets the Inventory
     * @return null
     */
    @Override
    default @NotNull Inventory getInventory() {
        return null;
    }
}
