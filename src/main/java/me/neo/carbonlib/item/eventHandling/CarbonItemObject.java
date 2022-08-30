package me.neo.carbonlib.item.eventHandling;

import me.neo.carbonlib.item.CarbonInventoryItem;
import me.neo.carbonlib.item.CarbonItem;

public class CarbonItemObject {
    private CarbonItem builder;
    private CarbonInventoryItem inventoryItem;

    public CarbonItemObject(CarbonItem builder) { setBuilder(builder); }
    public CarbonItemObject(CarbonInventoryItem inventoryItem) { setInventoryItem(inventoryItem); }
    public CarbonItem getBuilder() {return builder;}
    public CarbonItemObject setBuilder(CarbonItem builder) {
        this.builder = builder;
        return this;
    }

    public CarbonInventoryItem getInventoryItem() { return inventoryItem; }
    public CarbonItemObject setInventoryItem(CarbonInventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
        return this;
    }
}
