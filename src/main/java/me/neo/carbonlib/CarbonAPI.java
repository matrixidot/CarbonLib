package me.neo.carbonlib;

import me.neo.carbonlib.plugin.AbstractCarbon;

public class CarbonAPI {
    private static AbstractCarbon plugin;

    public static AbstractCarbon getInstance() {
        return plugin;
    }

    public static void init(AbstractCarbon plugin) {
        CarbonAPI.plugin = plugin;
    }
}
