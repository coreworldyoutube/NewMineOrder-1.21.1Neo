package com.himataku.nmo.world.inventory;

import com.himataku.nmo.world.inventory.NmoMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;

public class HeatedWorkbenchMenu extends AbstractFurnaceMenu {
    public HeatedWorkbenchMenu(int containerId, Inventory playerInventory) {
        super(
                NmoMenuTypes.HEATED_WORKBENCH.get(), // ← 修正: NmoMenuType → NmoMenuTypes
                RecipeType.SMELTING, // ここは将来 HeatedWorkbenchRecipeType に差し替え予定
                RecipeBookType.FURNACE, // GUIタブに「かまど」レシピ帳を使う設定
                containerId,
                playerInventory
        );
    }

    public HeatedWorkbenchMenu(int containerId, Inventory playerInventory, Container container, ContainerData data) {
        super(
                NmoMenuTypes.HEATED_WORKBENCH.get(), // ← 同じく修正
                RecipeType.SMELTING,
                RecipeBookType.FURNACE,
                containerId,
                playerInventory,
                container,
                data
        );
    }
}
