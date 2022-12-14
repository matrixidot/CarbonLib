package me.neo.carbonlib.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public interface IHolder extends InventoryHolder {
    String getName();

    @Override
    default @NotNull Inventory getInventory() {
        return null;
    }
}
