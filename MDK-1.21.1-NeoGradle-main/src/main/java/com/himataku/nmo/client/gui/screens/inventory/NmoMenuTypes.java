package com.himataku.nmo.world.inventory;

import com.himataku.nmo.Main;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class NmoMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(net.minecraft.core.registries.Registries.MENU, Main.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<HeatedWorkbenchMenu>> HEATED_WORKBENCH =
            MENUS.register("heated_workbench",
                    () -> new MenuType<>(HeatedWorkbenchMenu::new, FeatureFlags.VANILLA_SET)); // ← これ！

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
