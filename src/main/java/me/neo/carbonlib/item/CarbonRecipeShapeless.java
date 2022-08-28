package me.neo.carbonlib.item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;

public class CarbonRecipeShapeless {
    private ShapelessRecipe shapelessRecipe;
    private NamespacedKey key;

    /**
     *
     * @param key A NamespacedKey to identify this recipe by
     */
    public CarbonRecipeShapeless(NamespacedKey key) {
        this.key = key;
    }

    /**
     *
     * @param target The ItemStack that results from this recipe
     * @return CarbonRecipeShapeless
     */
    public CarbonRecipeShapeless createShapelessRecipe(ItemStack target) {
        this.shapelessRecipe = new ShapelessRecipe(key, target);
        return this;
    }

    /**
     *
     * @param ingredient The RecipeChoice for an Ingredient. Useful if you want to exactly match items
     * @return CarbonRecipeShapeless
     */
    public CarbonRecipeShapeless addIngredient(RecipeChoice ingredient) {
        this.shapelessRecipe.addIngredient(ingredient);
        return this;
    }

    /**
     *
     * @return The final Shapeless Recipe
     */
    public ShapelessRecipe forge() {
        return this.shapelessRecipe;
    }
}
