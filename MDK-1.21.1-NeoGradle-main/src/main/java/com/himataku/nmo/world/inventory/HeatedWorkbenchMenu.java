package net.terrarian.nmo.world.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;

public class HeatedWorkbenchMenu extends AbstractFurnaceMenu {
    public HeatedWorkbenchMenu(int containerId, Inventory playerInventory) {
        super(NmoMenuType.HEATED_WORKBENCH.get(),
                RecipeType.SMELTING, // ここを独自RecipeTypeにしてもOK
                RecipeBookType.FURNACE, // 独自にしたいなら新規定義も可能
                containerId,
                playerInventory);
    }

    public HeatedWorkbenchMenu(int containerId, Inventory playerInventory, Container container, ContainerData data) {
        super(NmoMenuType.HEATED_WORKBENCH.get(),
                RecipeType.SMELTING,
                RecipeBookType.FURNACE,
                containerId,
                playerInventory,
                container,
                data);
    }
}
