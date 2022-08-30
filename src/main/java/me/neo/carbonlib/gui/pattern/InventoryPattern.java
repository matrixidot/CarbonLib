package me.neo.carbonlib.gui.pattern;

import me.neo.carbonlib.item.CarbonInventoryItem;
import org.bukkit.Material;

import java.util.HashMap;

public class InventoryPattern {
    private final String pattern;
    private final HashMap<String, CarbonInventoryItem> map = new HashMap<>();

    public InventoryPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public String[] getSplitPattern() {
        return pattern.split(" ");
    }

    public HashMap<String, CarbonInventoryItem> getMap() {
        return map;
    }

    public CarbonInventoryItem getItem(String pattern, CarbonInventoryItem defaultValue) {
        return map.getOrDefault(pattern, defaultValue);
    }

    public CarbonInventoryItem getItem(String pattern, Material defaultValue) {
        return getItem(pattern, new CarbonInventoryItem(defaultValue));
    }

    public InventoryPattern addPatternValue(String patternName, CarbonInventoryItem item) {
        map.put(patternName, item);
        return this;
    }

    public InventoryPattern addPatternValue(String patternName, Material material) {
        map.put(patternName, new CarbonInventoryItem(material, patternName));
        return this;
    }
}
