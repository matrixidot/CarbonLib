package me.neo.carbonlib.item.eventHandling;

import me.neo.carbonlib.item.CarbonItem;

public class CarbonItemObject {
    private CarbonItem builder;

    public CarbonItemObject(CarbonItem builder) { setBuilder(builder); }

    public CarbonItem getBuilder() {return builder;}
    public CarbonItemObject setBuilder(CarbonItem builder) {
        this.builder = builder;
        return this;
    }
}
