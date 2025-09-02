package com.himataku.nmo.block.entity;


import com.himataku.nmo.Main;
import com.himataku.nmo.block.AllBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Main.MOD_ID);

    public static final Supplier<BlockEntityType<CrushBlockEntity>> CRUSH_BE =
            BLOCK_ENTITIES.register("crush_be", () -> BlockEntityType.Builder.of(
                    CrushBlockEntity::new, AllBlock.CRUSH.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}