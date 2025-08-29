package com.himataku.nmo.fluid;

import com.himataku.nmo.Main;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import com.himataku.nmo.fluid.FireLiquid;

import java.util.HashMap;
import java.util.Map;

public class AllFluid {

    public static final String[] METALS = { "copper", "zinc", "tin", "silver", "beryllium" };
    public static final Map<String, Integer> METAL_COLORS = Map.of(
            "copper", 0xFFB87333,
            "zinc", 0xFF7F8C8D,
            "tin", 0xFFB0C4DE,
            "silver", 0xFFC0C0C0,
            "beryllium", 0xFF33CC99
    );

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, Main.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, Main.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(Main.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(Main.MOD_ID);

    public static final Map<String, FluidData> FLUID_MAP = new HashMap<>();

    public static class FluidData {
        DeferredHolder<FluidType, FluidType> type;
        DeferredHolder<Fluid, FlowingFluid> source;
        DeferredHolder<Fluid, FlowingFluid> flowing;
        DeferredHolder<Item, BucketItem> bucket;
        DeferredHolder<Block, LiquidBlock> block;

        FluidData(DeferredHolder<FluidType, FluidType> type,
                  DeferredHolder<Fluid, FlowingFluid> source,
                  DeferredHolder<Fluid, FlowingFluid> flowing,
                  DeferredHolder<Item, BucketItem> bucket,
                  DeferredHolder<Block, LiquidBlock> block) {
            this.type = type;
            this.source = source;
            this.flowing = flowing;
            this.bucket = bucket;
            this.block = block;
        }
    }

    public static void registerAll(IEventBus modEventBus) {
        for (String metal : METALS) {
            registerMetalFluid(metal);
        }
        FLUID_TYPES.register(modEventBus);
        FLUIDS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
    }

    private static void registerMetalFluid(String metal) {
        DeferredHolder<FluidType, FluidType> type = FLUID_TYPES.register("molten_" + metal + "_type",
                () -> new FluidType(FluidType.Properties.create()
                        .density(2000)
                        .viscosity(6000)
                        .temperature(1300)
                ));

        final BaseFlowingFluid.Properties[] propsHolder = new BaseFlowingFluid.Properties[1];

        DeferredHolder<Fluid, FlowingFluid> source = FLUIDS.register("molten_" + metal + "_source",
                () -> new BaseFlowingFluid.Source(propsHolder[0]));

        DeferredHolder<Fluid, FlowingFluid> flowing = FLUIDS.register("molten_" + metal + "_flowing",
                () -> new BaseFlowingFluid.Flowing(propsHolder[0]));

        DeferredHolder<Item, BucketItem> bucket = ITEMS.register("molten_" + metal + "_bucket",
                () -> new BucketItem(source.get(), new Item.Properties()
                        .craftRemainder(Items.BUCKET)
                        .stacksTo(1)));

        DeferredHolder<Block, LiquidBlock> block = BLOCKS.register("molten_" + metal + "_block",
                () -> new FireLiquid(source.get(), BlockBehaviour.Properties.of()
                        .noCollission()
                        .strength(100f)
                        .lightLevel(s -> 15)
                        .liquid()));

        propsHolder[0] = new BaseFlowingFluid.Properties(type, source, flowing)
                .bucket(bucket)
                .block(block);

        FLUID_MAP.put(metal, new FluidData(type, source, flowing, bucket, block));
    }

    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        for (String metal : METALS) {
            final String metalName = metal;
            FluidData data = FLUID_MAP.get(metalName);
            if (data == null) continue;

            event.registerFluidType(new IClientFluidTypeExtensions() {
                @Override
                public ResourceLocation getStillTexture() {
                    return ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "block/lavality_still");
                }

                @Override
                public ResourceLocation getFlowingTexture() {
                    return ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "block/lavality_flow");
                }

                @Override
                public int getTintColor() {
                    return METAL_COLORS.getOrDefault(metalName, 0xFFFFFFFF);
                }
            }, data.type.get());
        }
    }
}
