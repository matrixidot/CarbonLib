package me.neo.carbonlib.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public abstract class AbstractCarbon extends JavaPlugin {
    private static AbstractCarbon plugin;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        enable();
    }

    public void enable() {

    }
    public void disable() {

    }
    @Override
    public void onDisable() {
        disable();
        plugin = null;
    }

    public static @NotNull Logger getLog() {
        return plugin.getLogger();
    }
    public static AbstractCarbon getInstance() {
        return plugin;
    }
}
