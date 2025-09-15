package com.himataku.nmo.item;

import com.himataku.nmo.Main;
import com.himataku.nmo.block.AllBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;

public class ALLItem {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Main.MOD_ID);

    public static final DeferredItem<Item> NMO_ICON = ITEMS.register("nmo_icon",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> HAMMER = ITEMS.register("hammer",
            () -> new Item(new Item.Properties().durability(128)));

    public static final DeferredItem<Item> SCRAP_BECU = ITEMS.register("scrap_becu",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<BlockItem> CRUSHER_ITEM = ALLItem.ITEMS.register("crusher",
            () -> new BlockItem(AllBlock.CRUSHER.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> HEAT_CRAFTER_ITEM = ALLItem.ITEMS.register("heat_crafter",
            () -> new BlockItem(AllBlock.HEAT_CRAFTER.get(), new Item.Properties()));

    // 原鉱リスト（raw）
    private static final String[] RAW_METALS = {
            "beryllium", "tungsten", "bismuth", "titanium",
            "mercury", "lead", "cobalt", "tin",
            "aluminum", "cesium", "rubidium", "lithium",
            "magnesium", "uranium", "thorium", "silver",
            "copper", "gold", "iron", "netherite", "steel",
            "beryllium_copper", "bronze", "brass", "silicon", "rubber",
            "zigget", "pladiamond", "regakingstone", "adamant",
            "orichalcum", "hihiirokane", "mithril", "eleckinite",
            "qeohu", "pallnium", "maclot", "degolast", "aquored",
            "zigget_k", "zigget_p", "zigget_q", "zigget_crystal",
            "zigget_core", "zigget_procreativemode", "zigget_creativemode",
            "zigget_modern", "nullmelon", "sim_oxy", "sim_iron"
    };
    private static final String[] OTHER_ITEMS = {
            "monazite"
    };

    public static final Map<String, DeferredItem<Item>> OTHER_REGISTERED_ITEMS = new HashMap<>();


    public static final Map<String, DeferredItem<Item>> RAW_ITEMS = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> INGOT_ITEMS = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> NUGGET_ITEMS = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> SHEET_ITEMS = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> ROD_ITEMS = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> WIRE_ITEMS = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> COIL_ITEMS = new HashMap<>();
    //public static final Map<String, DeferredItem<Item>> COGWHEEL_ITEMS = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> GEAR_ITEMS = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> TINY_ITEMS = new HashMap<>();
    //public static final Map<String, DeferredItem<Item>> BALL_ITEMS = new HashMap<>();
    //public static final Map<String, DeferredItem<Item>> SHAFT_ITEMS = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> DUST_ITEMS = new HashMap<>();

    static {
        for (String metal : RAW_METALS) {
            RAW_ITEMS.put(metal, ITEMS.register(metal + "_raw", () -> new Item(new Item.Properties())));
            NUGGET_ITEMS.put(metal, ITEMS.register(metal + "_nugget", () -> new Item(new Item.Properties())));
            INGOT_ITEMS.put(metal, ITEMS.register(metal + "_ingot", () -> new Item(new Item.Properties())));
            SHEET_ITEMS.put(metal, ITEMS.register(metal + "_sheet", () -> new Item(new Item.Properties())));
            ROD_ITEMS.put(metal, ITEMS.register(metal + "_rod", () -> new Item(new Item.Properties())));
            WIRE_ITEMS.put(metal, ITEMS.register(metal + "_wire", () -> new Item(new Item.Properties())));
            //COGWHEEL_ITEMS.put(metal, ITEMS.register(metal + "_cogwheel", () -> new Item(new Item.Properties())));
            GEAR_ITEMS.put(metal, ITEMS.register(metal + "_gear", () -> new Item(new Item.Properties())));
            COIL_ITEMS.put(metal, ITEMS.register(metal + "_coil", () -> new Item(new Item.Properties())));
            TINY_ITEMS.put(metal, ITEMS.register(metal + "_tiny", () -> new Item(new Item.Properties())));
            //BALL_ITEMS.put(metal, ITEMS.register(metal + "_ball", () -> new Item(new Item.Properties())));
            //SHAFT_ITEMS.put(metal, ITEMS.register(metal + "_shaft", () -> new Item(new Item.Properties())));
            DUST_ITEMS.put(metal, ITEMS.register(metal + "_dust", () -> new Item(new Item.Properties())));
        }
        for (String item : OTHER_ITEMS) {
            OTHER_REGISTERED_ITEMS.put(item, ITEMS.register(item, () -> new Item(new Item.Properties())));
        }
    }



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

