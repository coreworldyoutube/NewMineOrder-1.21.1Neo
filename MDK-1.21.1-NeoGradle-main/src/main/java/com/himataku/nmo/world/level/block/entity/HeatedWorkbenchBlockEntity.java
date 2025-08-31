package com.himataku.nmo.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;

public class HeatedWorkbenchBlockEntity extends AbstractFurnaceBlockEntity {
    public HeatedWorkbenchBlockEntity(BlockPos pos, BlockState state) {
        super(
                NmoBlockEntities.HEATED_WORKBENCH.get(), // 独自登録した BlockEntityType を指定
                pos,
                state,
                RecipeType.SMELTING // レシピはバニラの精錬を使用
        );
    }
}
