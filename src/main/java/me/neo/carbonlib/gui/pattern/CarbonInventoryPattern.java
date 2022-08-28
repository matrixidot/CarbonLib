package me.neo.carbonlib.gui.pattern;

import me.neo.carbonlib.item.CarbonInventoryItem;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class CarbonInventoryPattern {
    private final String pattern;
    private final Map<String, CarbonInventoryItem> map = new HashMap<>();

    public CarbonInventoryPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public String[] getSplitPattern() {
        return pattern.split(" ");
    }

    public Map<String, CarbonInventoryItem> getMap() {
        return map;
    }

    public CarbonInventoryItem getItem(String pattern, CarbonInventoryItem defaultValue) {
        return getMap().getOrDefault(pattern, defaultValue);
    }
    public CarbonInventoryItem getItem(String pattern, Material defaultValue) {
        return getItem(pattern, new CarbonInventoryItem(defaultValue));
    }
    public CarbonInventoryPattern addPatternValue(String patternName, CarbonInventoryItem item) {
        map.put(patternName, item);
        return this;
    }

    public CarbonInventoryPattern addPatternValue(String patternName, Material material) {
        map.put(patternName, new CarbonInventoryItem(material, patternName));
        return this;
    }
}
