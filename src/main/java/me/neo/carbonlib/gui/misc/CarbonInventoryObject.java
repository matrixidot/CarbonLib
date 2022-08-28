package me.neo.carbonlib.gui.misc;

import me.neo.carbonlib.gui.type.CarbonInventoryBuilder;
import me.neo.carbonlib.gui.type.CarbonPageInventoryBuilder;

public class CarbonInventoryObject {
    private CarbonInventoryBuilder builder;
    private CarbonPageInventoryBuilder pageBuilder;

    public CarbonInventoryObject(CarbonPageInventoryBuilder pageBuilder) {
        setPageBuilder(pageBuilder);
    }

    public CarbonInventoryObject(CarbonInventoryBuilder builder) {
        setBuilder(builder);
    }

    public CarbonInventoryBuilder getBuilder() {
        return builder;
    }
    public CarbonInventoryObject setBuilder(CarbonInventoryBuilder builder) {
        this.builder = builder;
        return this;
    }

    public CarbonPageInventoryBuilder getPageBuilder() {
        return pageBuilder;
    }
    public CarbonInventoryObject setPageBuilder(CarbonPageInventoryBuilder builder) {
        this.pageBuilder = builder;
        return this;
    }

    public boolean isMainBuilder() {
        return getPageBuilder() == null;
    }
}
