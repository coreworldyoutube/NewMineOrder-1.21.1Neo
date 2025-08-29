package net.terrarian.nmo.world.item.crafting;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryObject;

public class NmoRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, "nmo");

    public static final RegistryObject<RecipeType<HeatedWorkbenchRecipe>> HEATED_WORKBENCH =
            RECIPE_TYPES.register("heated_workbench",
                    () -> RecipeType.simple(new ResourceLocation("nmo", "heated_workbench")));
}
