package com.himataku.nmo.block.entity;

import com.himataku.nmo.Main;
import com.himataku.nmo.block.AllBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {

    // BlockEntityType 用の DeferredRegister
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Main.MOD_ID);

    // CrushBlockEntity の登録
    public static final Supplier<BlockEntityType<CrushBlockEntity>> CRUSH_BE =
            BLOCK_ENTITIES.register("crush_be", () -> BlockEntityType.Builder.of(
                    // BlockEntity のコンストラクタ参照
                    CrushBlockEntity::new,
                    // 対応するブロック
                    AllBlock.CRUSH.get()
            ).build(null)); // DataFixer は null で OK

    // DeferredRegister をイベントバスに登録するメソッド
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
