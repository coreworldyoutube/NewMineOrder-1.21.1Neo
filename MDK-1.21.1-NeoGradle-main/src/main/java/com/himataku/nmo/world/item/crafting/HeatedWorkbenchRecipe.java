package com.himataku.nmo.world.item.crafting;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;

public abstract class HeatedWorkbenchRecipe implements Recipe<SingleRecipeInput> {
    private final ResourceLocation id;
    private final ItemStack result;

    public HeatedWorkbenchRecipe(ResourceLocation id, ItemStack result) {
        this.id = id;
        this.result = result;
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        // TODO: 入力スロットのアイテムをチェック
        return false;
    }


    public ItemStack getResultItem(net.minecraft.world.item.crafting.RecipeInput input) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }


    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return result.copy();
    }


    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return NmoRecipeSerializers.HEATED_WORKBENCH.get();
    }

    @Override
    public RecipeType<?> getType() {
        return NmoRecipeTypes.HEATED_WORKBENCH.get();
    }
}
