package me.neo.carbonlib;

import me.neo.carbonlib.item.eventHandling.CarbonItemCache;
import me.neo.carbonlib.item.ItemListener;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class Carbon extends JavaPlugin {
    public final NamespacedKey key = new NamespacedKey(this, "Custom");
    private static Carbon plugin;
    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getServer().getPluginManager().registerEvents(new ItemListener(this), this);
        new CarbonItemCache();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static NamespacedKey getKey() {
        return plugin.key;
    }

}
