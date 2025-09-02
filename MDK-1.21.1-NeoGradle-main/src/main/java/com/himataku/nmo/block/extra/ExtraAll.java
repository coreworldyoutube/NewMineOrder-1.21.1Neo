package com.himataku.nmo.block.extra;

import com.himataku.nmo.Main;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;

public class ExtraAll {

    // ブロック用 DeferredRegister
    public static final DeferredRegister<Block> EXTRA_BLOCKS =
            DeferredRegister.create(BuiltInRegistries.BLOCK, Main.MOD_ID);

    // Crusher ブロック登録（Block型として保持せずDeferredRegisterに任せる）
    public static void registerBlocks() {
        EXTRA_BLOCKS.register("crusher",
                () -> new Crush(BlockBehaviour.Properties.of()
                        .strength(4f)
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.METAL)
                        .noOcclusion())
        );
    }

    // イベントバスに登録
    public static void register(IEventBus eventBus) {
        registerBlocks();
        EXTRA_BLOCKS.register(eventBus);
    }
}
