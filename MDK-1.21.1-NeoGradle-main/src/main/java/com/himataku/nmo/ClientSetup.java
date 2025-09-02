package com.himataku.nmo;

import com.himataku.nmo.fluid.AllFluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

public class ClientSetup {
    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(ClientSetup::onRegisterClientExtensions);
    }

    private static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        AllFluid.registerClientExtensions(event);

    }
}
