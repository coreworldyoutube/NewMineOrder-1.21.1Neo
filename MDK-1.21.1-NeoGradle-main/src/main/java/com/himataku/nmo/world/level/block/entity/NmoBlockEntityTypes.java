package com.himataku.nmo.world.level.block.entity;

import com.himataku.nmo.Main;
import com.himataku.nmo.block.AllBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class NmoBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(net.minecraft.core.registries.Registries.BLOCK_ENTITY_TYPE, Main.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HeatedWorkbenchBlockEntity>> HEATED_WORKBENCH =
            BLOCK_ENTITIES.register("heated_workbench",
                    () -> BlockEntityType.Builder.of(
                            HeatedWorkbenchBlockEntity::new,
                            AllBlock.REGISTERED_BLOCKS.get("heated_workbench").get()
                    ).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
