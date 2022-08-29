package me.neo.carbonlib.item;

import com.google.common.collect.Multimap;
import me.neo.carbonlib.utils.Util;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class CarbonItem {
    private ItemStack item;
    private Material material;
    private int amount;
    private ItemMeta meta;
    private Map<Enchantment, Integer> enchants;
    private ItemFlag[] flags;
    private Multimap<Attribute, AttributeModifier> attributes;
    private String displayName;
    private List<String> lore;
    private boolean unbreakable;
    private int customModelData;
    private PersistentDataContainer container = this.meta.getPersistentDataContainer();

    private Consumer<PlayerInteractEvent> rightClick = event -> {};
    private Consumer<PlayerInteractEvent> rightClickBlock = event -> {};
    private Consumer<PlayerInteractEvent> leftClick = event -> {};
    private Consumer<PlayerInteractEvent> leftClickBlock = event -> {};

    private ItemStack lastItem;
    public CarbonItem(ItemStack item) {
        this.item = item;
    }



    /**
     * Creates an ItemStack with a material and an amount
     * @param material The Material of the ItemStack to build
     * @param amount The amount of the item
     * @return returns instance of CarbonItem
     *
     */
    public CarbonItem setItem(Material material, int amount) {
        this.material = material;
        this.amount = amount;
        this.item = new ItemStack(material, amount);
        this.meta = item.getItemMeta();
        return this;
    }

    /**
     * Gets the ItemStack
     * @return The current ItemStack
     */
    public ItemStack getItem() {
        return this.item;
    }

    /**
     * Gets the material of the ItemStack
     * @return The current Material
     */
    public Material getMaterial() {
        return this.material;
    }

    /**
     * Gets the ItemMeta of the ItemStack
     * @return The ItemStack ItemMeta
     */
    public ItemMeta getMeta() {
        return this.meta;
    }

    /**
     * Gets the amount of the ItemStack
     * @return Amount of the TtemStack
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Adds an Enchantment to the Enchants map
     * @param enchantment The Enchantment to add
     * @param level Level of the enchantment
     * @return CarbonItem
     */
    public CarbonItem addEnchant(Enchantment enchantment, int level) {
        this.enchants.put(enchantment, level);
        return this;
    }

    /**
     * Sets the Enchants Map
     * @param enchants A Hashmap containing Enchantment, Integer pairs
     *                 Enchantment being the enchantment and the Integer being its level
     * @return CarbonItem
     */
    public CarbonItem setEnchants(Map<Enchantment, Integer> enchants) {
        this.enchants = enchants;
        return this;
    }

    /**
     * Gets the Map of Enchantment Integer pairs
     * @return The Map of Enchantment, Integer
     */
    public Map<Enchantment, Integer> getEnchants() {
        return enchants;
    }

    /**
     * adds an ItemFlag to the ItemFlag array
     * @param flag The ItemFlag to add
     * @return CarbonItem
     */
    public CarbonItem addFlags(ItemFlag flag) {
        Arrays.asList(this.flags).add(flag);
        return this;
    }

    /**
     * Sets the array of ItemFlags
     * @param flags The ItemFlag[] to add
     * @return CarbonItem
     */
    public CarbonItem setFlags(ItemFlag... flags) {
        this.flags = flags;
        return this;
    }

    /**
     * Gets an array of the current ItemStacks itemFlags
     * @return The ItemStack's itemflags
     */
    public ItemFlag[] getFlags() {
        return this.flags;
    }

    /**
     * Adds an attribute modifier to the attributes MultiMap
     * @param attribute The Type of attribute to add
     * @param modifier The Attribute modifier
     * @return CarbonItem
     */
    public CarbonItem addAttribute(Attribute attribute, AttributeModifier modifier) {
        this.attributes.put(attribute, modifier);
        return this;
    }

    /**
     * Sets the Multimap of attribute, AttributeModifier
     * @param attributes The Multimap of Attribute, AttributeModifier.
     * @return CarbonItem
     */
    public CarbonItem setAttributes(Multimap<Attribute, AttributeModifier> attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * Gets the Multimap of Attribute, AttributeModifier pairs
     * @return Returns the Multimap of the ItemStack's attributes
     */
    public Multimap<Attribute, AttributeModifier> getAttributes() {
        return this.attributes;
    }

    /**
     * Sets the display name of the item
     * @param displayName The Displayname of the item
     * @return
     */
    public CarbonItem setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Gets the displayname of the item
     * @return The display name of the item
     */
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * Adds a line of lore
     * @param loreLine The line of lore to add
     * @return CarbonItem
     */
    public CarbonItem addLoreLine(String loreLine) {
        this.lore.add(loreLine);
        return this;
    }

    /**
     * Sets the lore of the item
     * @param lore The String[] of lore to add
     * @return CarbonItem
     */
    public CarbonItem setLore(String... lore) {
        this.lore = Arrays.asList(lore);
        return this;
    }

    /**
     * Gets the lore of the item
     * @return The List of Strings which is the lore of the item.
     */
    public List<String> getLore() {
        return this.lore;
    }

    /**
     * Sets the item to be unbreakable or not unbreakable
     * @param unbreakable If the item should be unbreakable or not
     * @return CarbonItem
     */
    public CarbonItem setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    /**
     * Checks if the item is unbreakable or not
     * @return If the item is unbreakable
     */
    public boolean isUnbreakable() {
        return this.unbreakable;
    }

    /**
     * Sets the CustomModelData of the item
     * It Must be an integer 7 digits long and cannot start with 0
     * @param customModelData The Integer of the customModelData probably specified in a texture pack
     * @return CarbonItem
     */
    public CarbonItem setCustomModelData(int customModelData) {
        this.customModelData = customModelData;
        return this;
    }

    /**
     * Gets the CustomModelData of the Item
     * @return The integer corresponding to this item's customModelData
     */
    public int getCustomModelData() {
        return this.customModelData;
    }

    /**
     * Adds a persistentDataContainer entry to the item's persistentDataContainer
     * @param key A NamespacedKey to identify this entry
     * @param type The type of Persistent Data container
     * @param value The Value of the type of container
     * @return CarbonItem
     */
    public CarbonItem addContainer(NamespacedKey key, PersistentDataType type, Object value) {
        this.container.set(key, type, value);
        return this;
    }

    /**
     * Gets the PersistentDataContainer on the item
     * @return The PersistentDataContainer of this item
     */
    public PersistentDataContainer getContainer() {
        return this.container;
    }

    public ItemStack getLastItem() {
        return Util.getOrDefault(lastItem, forge());
    }
    public CarbonItem setLastItem(ItemStack lastItem) {
        this.lastItem = lastItem;
        return this;
    }

    public Consumer<PlayerInteractEvent> getRightClick() {
        return rightClick;
    }
    public CarbonItem setRightClick(Consumer<PlayerInteractEvent> rightClick) {
        this.rightClick = rightClick;
        return this;
    }

    public Consumer<PlayerInteractEvent> getRightClickBlock() {
        return rightClickBlock;
    }
    public CarbonItem setRightClickBlock(Consumer<PlayerInteractEvent> rightClickBlock) {
        this.rightClickBlock = rightClickBlock;
        return this;
    }

    public Consumer<PlayerInteractEvent> getLeftClick() {
        return leftClick;
    }
    public CarbonItem setLeftClick(Consumer<PlayerInteractEvent> leftClick) {
        this.leftClick = leftClick;
        return this;
    }

    public Consumer<PlayerInteractEvent> getLeftClickBlock() {
        return leftClickBlock;
    }
    public CarbonItem setLeftClickBlock(Consumer<PlayerInteractEvent> leftClickBlock) {
        this.leftClickBlock = leftClickBlock;
        return this;
    }

    /**
     * Forges the final ItemStack for use
     * @return The Final ItemStack
     */
    public ItemStack forge() {
        this.item.setAmount(this.amount);
        this.meta.setDisplayName(this.displayName);
        this.meta.setLore(this.lore);
        this.item.addUnsafeEnchantments(this.enchants);
        this.meta.addItemFlags(this.flags);
        this.meta.setAttributeModifiers(this.attributes);
        this.meta.setUnbreakable(this.unbreakable);
        this.meta.setCustomModelData(this.customModelData);
        this.item.setItemMeta(this.meta);
        setLastItem(item);
        // Adds the itemStack along with an instance of the builder to the item cache
        CarbonItemCache.getCache().addItem(item, new CarbonItemObject(this));
        return item;
    }

}
