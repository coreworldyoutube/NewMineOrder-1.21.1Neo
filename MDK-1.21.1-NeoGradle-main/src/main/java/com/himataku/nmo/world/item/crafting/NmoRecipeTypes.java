package com.himataku.nmo.world.item.crafting;

import com.himataku.nmo.Main;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class NmoRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, Main.MOD_ID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<?>> HEATED_WORKBENCH =
            RECIPE_TYPES.register("heated_workbench",
                    () -> RecipeType.simple(new ResourceLocation(Main.MOD_ID, "heated_workbench")));
}
