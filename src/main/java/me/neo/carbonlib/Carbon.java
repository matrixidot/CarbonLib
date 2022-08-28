package me.neo.carbonlib;

import me.neo.carbonlib.plugin.AbstractCarbon;

public final class Carbon extends AbstractCarbon {

    @Override
    public void onLoad() {

        super.onLoad();
    }

    @Override
    public void onEnable() {
        CarbonAPI.init(this);
        super.onEnable();



    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

}
