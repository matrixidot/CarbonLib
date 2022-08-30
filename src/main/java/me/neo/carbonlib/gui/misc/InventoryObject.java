package me.neo.carbonlib.gui.misc;

import me.neo.carbonlib.gui.type.CarbonInventoryBuilder;

public class InventoryObject {
    private CarbonInventoryBuilder builder;

    public InventoryObject(CarbonInventoryBuilder InventoryBuilder) {
        setBuilder(InventoryBuilder);
    }

    public CarbonInventoryBuilder getBuilder() {
        return builder;
    }

    public InventoryObject setBuilder(CarbonInventoryBuilder builder) {
        this.builder = builder;
        return this;
    }


    public boolean isMainBuilder() {
        return true;
    }
}
