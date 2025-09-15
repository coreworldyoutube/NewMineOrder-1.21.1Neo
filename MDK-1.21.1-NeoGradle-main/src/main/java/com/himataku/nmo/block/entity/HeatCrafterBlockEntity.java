package com.himataku.nmo.block.entity;

import com.himataku.nmo.item.ALLItem;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class HeatCrafterBlockEntity extends BlockEntity {

    // スロット 0: 入力, 1: 燃料, 2: 出力
    private final ItemStackHandler inventory = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private int progress = 0; // 作業進行度
    private int fuel = 0;     // 燃料量

    public HeatCrafterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.HEAT_CRAFTER.get(), pos, state);
    }

    // 毎tick呼ばれる処理
    public void tick() {
        if (!inventory.getStackInSlot(1).isEmpty() && progress < 100) {
            progress++;
            fuel++;
            inventory.extractItem(1, 1, false); // 燃料消費
        } else if (progress >= 100) {
            inventory.setStackInSlot(2, new ItemStack(ALLItem.HEAT_CRAFTER_ITEM.get()));
            progress = 0;
        }
    }

    public int getProgress() { return progress; }
    public int getFuel() { return fuel; }

    public ItemStackHandler getInventory() { return inventory; }

    public void drops() {
        for (int i = 0; i < inventory.getSlots(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), stack);
            }
        }
    }

    public void clearContents() {
        for (int i = 0; i < inventory.getSlots(); i++)
            inventory.setStackInSlot(i, ItemStack.EMPTY);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, net.minecraft.core.HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("progress", progress);
        tag.putInt("fuel", fuel);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, net.minecraft.core.HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        progress = tag.getInt("progress");
        fuel = tag.getInt("fuel");
    }




    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

}
