package com.minecampkids.potatoes;

import java.util.Map;

import com.google.gson.JsonObject;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ShapelessBucketConsumingRecipe extends ShapelessRecipes {

    public ShapelessBucketConsumingRecipe(String group, ItemStack output, NonNullList<Ingredient> ingredients) {
        super(group, output, ingredients);
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        NonNullList<ItemStack> ret = super.getRemainingItems(inv);
        ret.replaceAll(s -> s.getItem() == Items.BUCKET ? ItemStack.EMPTY : s);
        return ret;
    }

    public static class Factory implements IRecipeFactory {

        private static final IRecipeFactory shapeless;
        static {
            Map<ResourceLocation, IRecipeFactory> recipeFactories = ObfuscationReflectionHelper.getPrivateValue(CraftingHelper.class, null, "recipes");
            shapeless = recipeFactories.get(new ResourceLocation("crafting_shapeless"));
            if (shapeless == null) {
                throw new IllegalStateException("Missing shapeless recipe factory?!");
            }
        }

        @Override
        public IRecipe parse(JsonContext context, JsonObject json) {
            IRecipe parent = shapeless.parse(context, json);
            return new ShapelessBucketConsumingRecipe(parent.getGroup(), parent.getRecipeOutput(), parent.getIngredients());
        }
    }
}
