package com.himataku.nmo.item;

import com.himataku.nmo.Main;
import com.himataku.nmo.block.AllBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.himataku.nmo.block.AllBlock.BLOCKS;


public class NmoCreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Main.MOD_ID);

    public static final Supplier<CreativeModeTab> NewMineOrder_TAB = CREATIVE_MODE_TAB.register("newmineorder_tab",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(ALLItem.NMO_ICON.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "newmineorder"))
                    .title(Component.translatable("creativetab.nmo.newmineorder_tab"))
                    .displayItems((Param, output)-> {
                        output.accept(ALLItem.NMO_ICON.get());
                        ALLItem.RAW_ITEMS.values().forEach(item -> output.accept(item.get()));
                        ALLItem.INGOT_ITEMS.values().forEach(item -> output.accept(item.get()));
                        BLOCKS.getEntries().forEach(entry -> output.accept(entry.get()));

                    })
                    .build());
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
