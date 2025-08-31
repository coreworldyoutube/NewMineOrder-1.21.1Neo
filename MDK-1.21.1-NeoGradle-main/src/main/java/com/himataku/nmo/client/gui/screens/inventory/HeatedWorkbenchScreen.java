package com.himataku.nmo.client.gui.screens.inventory;

import com.himataku.nmo.world.inventory.HeatedWorkbenchMenu;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;



@OnlyIn(Dist.CLIENT)
public class HeatedWorkbenchScreen extends AbstractFurnaceScreen<HeatedWorkbenchMenu> {
    private static final ResourceLocation LIT_PROGRESS_SPRITE =
            ResourceLocation.fromNamespaceAndPath("nmo", "container/heated_workbench/lit_progress");
    private static final ResourceLocation BURN_PROGRESS_SPRITE =
            ResourceLocation.fromNamespaceAndPath("nmo", "container/heated_workbench/burn_progress");
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath("nmo", "textures/gui/container/heated_workbench.png");

    public HeatedWorkbenchScreen(HeatedWorkbenchMenu menu, Inventory playerInventory, Component title) {
        super(menu, new SmeltingRecipeBookComponent(), playerInventory, title,
                TEXTURE, LIT_PROGRESS_SPRITE, BURN_PROGRESS_SPRITE);
    }
}
