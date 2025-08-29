package com.himataku.nmo;

import com.himataku.nmo.block.AllBlock;
import com.himataku.nmo.fluid.AllFluid;
import com.himataku.nmo.fluid.ReactionFluid;
import com.himataku.nmo.item.ALLItem;
import com.himataku.nmo.item.NmoCreativeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;


@Mod("nmo")
public class Main {
    public static final String MOD_ID = "nmo";


    public Main(IEventBus modEventBus) {
        ALLItem.register(modEventBus);
        AllBlock.register(modEventBus);
        NmoCreativeTab.register(modEventBus);
        AllFluid.registerAll(modEventBus);
        //AllTool.register(modEventBus);

        modEventBus.addListener(this::commonSetup); // 共通セットアップ

        if (FMLEnvironment.dist.isClient()) {
            ClientSetup.register(modEventBus);

        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ReactionFluid.registerFluidInteractions();
    }
}
