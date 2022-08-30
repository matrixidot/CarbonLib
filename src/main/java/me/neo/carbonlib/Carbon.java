package me.neo.carbonlib;

import me.neo.carbonlib.gui.InventoryListener;
import me.neo.carbonlib.item.eventHandling.CarbonItemCache;
import me.neo.carbonlib.item.eventHandling.ItemListener;
import me.neo.carbonlib.plugin.AbstractCarbon;
import org.bukkit.Bukkit;

public final class Carbon extends AbstractCarbon {

    @Override
    public void onLoad() {

        super.onLoad();
    }

    @Override
    public void onEnable() {
        CarbonAPI.init(this);
        Bukkit.getServer().getPluginManager().registerEvents(new ItemListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        new CarbonItemCache();
        super.onEnable();



    }

    @Override
    public void onDisable() {
        super.onDisable();
    }


}
