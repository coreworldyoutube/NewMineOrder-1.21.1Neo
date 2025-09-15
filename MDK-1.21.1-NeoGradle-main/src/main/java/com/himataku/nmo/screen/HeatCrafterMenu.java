package com.himataku.nmo.screen;

import com.himataku.nmo.block.entity.HeatCrafterBlockEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class HeatCrafterMenu extends AbstractContainerMenu {

    private final HeatCrafterBlockEntity blockEntity;
    private final IItemHandler inventory;

    public HeatCrafterMenu(int id, Inventory playerInventory, HeatCrafterBlockEntity blockEntity) {
        super(ModMenuTypes.HEAT_CRAFTER_MENU.get(), id);
        this.blockEntity = blockEntity;
        this.inventory = blockEntity.inventory;

        // ブロックスロット（0: 入力, 1: 燃料, 2: 出力）
        this.addSlot(new SlotItemHandler(inventory, 0, 56, 17)); // 入力
        this.addSlot(new SlotItemHandler(inventory, 1, 56, 53)); // 燃料
        this.addSlot(new SlotItemHandler(inventory, 2, 116, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) { return false; } // 出力スロットには入れられない
        });

        // プレイヤーインベントリ
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        // ホットバー
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return blockEntity.getLevel().getBlockState(blockEntity.getBlockPos()).getBlock() == blockEntity.getBlockState().getBlock();
    }
}
