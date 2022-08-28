package me.neo.carbonlib.item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;


public class CarbonRecipeShaped {
    private ShapedRecipe shapedRecipe;
    private NamespacedKey key;

    /**
     *
     * @param key The NamespacedKey to identify this recipe
     */
    public CarbonRecipeShaped(NamespacedKey key) {
        this.key = key;
    }

    /**
     *
     * @param product The ItemStack that results from this craft
     * @return CarbonShapedRecipe
     */
    public CarbonRecipeShaped createShapedRecipe(ItemStack product) {
        this.shapedRecipe = new ShapedRecipe(this.key, product);
        return this;
    }

    /**
     *
     * @param shape The Shape of the recipe in a string[] The array must be a length of 3 and each String MUST be 3 chars long
     * @return CarbonRecipeShaped
     */
    public CarbonRecipeShaped setShape(String... shape) {
        this.shapedRecipe.shape(shape[0], shape[1], shape[2]);
        return this;
    }

    /**
     *
     * @param ingredients A String[] of ingredients
     *                    Format should be "CHAR:MATERIAL"
     *                    Example "I:IRON_INGOT"
     * @return CarbonRecipeShaped
     */
    public CarbonRecipeShaped setIngredients(String... ingredients) {
        for (String KvP : ingredients) {
            String[] split = KvP.split(":");
            char key = split[0].charAt(0);
            String value = split[1];
            this.shapedRecipe.setIngredient(key, new RecipeChoice.ExactChoice(new ItemStack(Material.matchMaterial(value), 1)));
        }
        return this;
    }

    /**
     *
     * @return The Finished Shaped Recipe
     */
    public ShapedRecipe forge() {
        return this.shapedRecipe;
    }
}
