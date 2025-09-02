package com.himataku.nmo.block;

import com.himataku.nmo.Main;
import com.himataku.nmo.block.extra.Crush;
import com.himataku.nmo.item.ALLItem;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AllBlock {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Main.MOD_ID);

    public static final Map<String, DeferredBlock<Block>> REGISTERED_BLOCKS = new HashMap<>();

    private static final String[] ORE_NAMES = {
            "beryllium", "tungsten", "bismuth", "titanium",
            "mercury", "lead", "cobalt", "tin",
            "aluminum", "cesium", "rubidium", "lithium",
            "magnesium", "uranium", "thorium", "silver",
            "copper", "gold", "iron", "netherite", "steel",
            "pyrite", "platinum",
            "strontian", "barium", "radium", "chromium"
    };
    private static final String[] STONE_NAMES = {
            "monazite_block", "polymetallic_nodules","beryllium_copper_block"
    };
    private static final BlockBehaviour.Properties STONE_PROPERTIES =
            BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE);
    private static void registerStoneBlocks() {
        for (String name : STONE_NAMES) {
            String blockId = name;
            DeferredBlock<Block> block = registerBlock(blockId,
                    () -> new Block(STONE_PROPERTIES));
            REGISTERED_BLOCKS.put(blockId, block);
        }
    }

    public static final Supplier<Block> CRUSH = BLOCKS.register("crusher",
            () -> new Crush(BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
                    .noOcclusion())
    );







    private static final BlockBehaviour.Properties ORE_PROPERTIES =
            BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE);

    private static final BlockBehaviour.Properties DEEPSLATE_ORE_PROPERTIES =
            BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE);

    private static final BlockBehaviour.Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL);

    private static final BlockBehaviour.Properties BLOCK_CASINGS =
            BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().sound(SoundType.METAL);

    private static final BlockBehaviour.Properties SLAG_PROPERTIES =
            BlockBehaviour.Properties.of()
                    .strength(2f) // 金属ブロックより柔らかい
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GRAVEL);
    // スラグは砕けた鉱滓なので、砂利や砂に近い音が合う.

    private static DeferredBlock<Block> registerBlock(String id, Supplier<Block> supplier) {
        DeferredBlock<Block> block = BLOCKS.register(id, supplier);
        ALLItem.ITEMS.register(id, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static void registerOres() {
        for (String name : ORE_NAMES) {
            String oreId = name + "_ore";
            DeferredBlock<Block> block = registerBlock(oreId,
                    () -> new DropExperienceBlock(UniformInt.of(2, 5), ORE_PROPERTIES));
            REGISTERED_BLOCKS.put(oreId, block);
        }
    }

    private static void registerDeepslateOres() {
        for (String name : ORE_NAMES) {
            String deepslateOreId = name + "_deepslate_ore";
            DeferredBlock<Block> block = registerBlock(deepslateOreId,
                    () -> new DropExperienceBlock(UniformInt.of(4, 5), DEEPSLATE_ORE_PROPERTIES));
            REGISTERED_BLOCKS.put(deepslateOreId, block);
        }
    }

    private static void registerBlocks() {
        for (String name : ORE_NAMES) {
            String blockId = name + "_block";
            DeferredBlock<Block> block = registerBlock(blockId,
                    () -> new Block(BLOCK_PROPERTIES));
            REGISTERED_BLOCKS.put(blockId, block);
        }
    }

    private static void registerCasings() {
        for (String name : ORE_NAMES) {
            String blockId = name + "_casing";
            DeferredBlock<Block> block = registerBlock(blockId,
                    () -> new Block(BLOCK_CASINGS));
            REGISTERED_BLOCKS.put(blockId, block);
        }
    }

    private static void registerSlagBlocks() {
        String[] slagNames = {
                "bronze_slag",
                "brass_slag",
                "beryllium_copper_slag"
        };

        for (String slag : slagNames) {
            DeferredBlock<Block> block = registerBlock(slag, () -> new Block(SLAG_PROPERTIES));
            REGISTERED_BLOCKS.put(slag, block);
        }
    }


    public static void registerBlocksAll() {
        registerOres();
        registerDeepslateOres();
        registerBlocks();
        registerSlagBlocks();
        registerCasings();
        registerStoneBlocks();
    }

    public static void register(IEventBus eventBus) {
        registerBlocksAll();
        BLOCKS.register(eventBus);

    }
}
