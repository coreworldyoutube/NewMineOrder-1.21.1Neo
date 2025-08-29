package com.himataku.nmo;

import com.himataku.nmo.fluid.AllFluid;
import com.himataku.nmo.world.inventory.NmoMenuTypes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;


public class ClientSetup {
    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(ClientSetup::onRegisterClientExtensions);
        modEventBus.addListener(ClientSetup::onRegisterMenuScreens); // ← GUI 登録
    }

    private static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        AllFluid.registerClientExtensions(event);
    }

    private static void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
        event.register(NmoMenuTypes.HEATED_WORKBENCH.get(), HeatedWorkbenchScreen::new);
    }
}
