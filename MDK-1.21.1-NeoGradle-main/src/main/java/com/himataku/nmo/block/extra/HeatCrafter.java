package com.himataku.nmo.block.extra;

import com.himataku.nmo.block.entity.HeatCrafterBlockEntity;
import com.himataku.nmo.screen.HeatCrafterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class HeatCrafter extends BaseEntityBlock {

    public HeatCrafter(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new HeatCrafterBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide && world.getBlockEntity(pos) instanceof HeatCrafterBlockEntity be && player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openGui(serverPlayer,
                    new SimpleMenuProvider((id, inv, p) -> new HeatCrafterMenu(id, inv, be), new TextComponent("Heat Crafter")),
                    pos
            );
        }
        return InteractionResult.SUCCESS;
    }
}
