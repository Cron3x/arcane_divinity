package de.abq.arcane_divinity.world.item;

import de.abq.arcane_divinity.platform.Services;
import de.abq.arcane_divinity.world.item.armor.WingsArmorItem;
import de.abq.arcane_divinity.world.item.armor.WitcherRobeItem;
import de.abq.arcane_divinity.world.level.block.ZBlocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ZItems {
    public static void init(){}
    public static final Map<String, Item> ITEMS = new LinkedHashMap<>();

    public static final Supplier<Item> WINGS = build("wings", () -> new WingsArmorItem(Services.PLATFORM.defaultItemBuilder().rarity(Rarity.EPIC)));

    public static final Supplier<Item> WITCHER_ROBE_HOOD = build("witcher_robe_hood", () -> new WitcherRobeItem(Services.PLATFORM.defaultItemBuilder().rarity(Rarity.EPIC), ArmorItem.Type.HELMET));
    public static final Supplier<Item> WITCHER_ROBE_CHESTPLATE = build("witcher_robe_chestplate", () -> new WitcherRobeItem(Services.PLATFORM.defaultItemBuilder().rarity(Rarity.EPIC), ArmorItem.Type.CHESTPLATE));
    public static final Supplier<Item> WITCHER_ROBE_LEGGINGS = build("witcher_robe_leggings", () -> new WitcherRobeItem(Services.PLATFORM.defaultItemBuilder().rarity(Rarity.EPIC), ArmorItem.Type.LEGGINGS));
    public static final Supplier<Item> WITCHER_ROBE_BOOTS = build("witcher_robe_boots", () -> new WitcherRobeItem(Services.PLATFORM.defaultItemBuilder().rarity(Rarity.EPIC), ArmorItem.Type.BOOTS));

    public static final Supplier<Item> MAGIC_MUSHROOM = build("magic_mushroom", () -> new MagicMushroomItem(Services.PLATFORM.defaultItemBuilder().food(new FoodProperties.Builder().alwaysEdible().nutrition(0).build()), 10));
    public static final Supplier<Item> HALLUCINOGENIC_GRASS = build("hallucinogenic_grass", () -> new Item(Services.PLATFORM.defaultItemBuilder()));
    public static final Supplier<Item> ASH = build("ash", () -> new Item(Services.PLATFORM.defaultItemBuilder()));
    public static final Supplier<Item> BOTTLED_JINN = build(BottledJinnItem.IDENTIFIER, () -> new BottledJinnItem(Services.PLATFORM.defaultItemBuilder()));

    //WEAPONS
    public static final Supplier<EaSwordItem> EA_SWORD = build(EaSwordItem.IDENTIFIER, () -> new EaSwordItem(Tiers.NETHERITE, Services.PLATFORM.defaultItemBuilder().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 5, -2f))));

    //ITEMS
    public static final Supplier<BlockItem> ARCANE_OBELISK_BLOCK_ITEM = build(ZBlocks.Locations.ARCANE_OBELISK, () -> new BlockItem(ZBlocks.ARCANE_OBELISK_BLOCK.get(), new Item.Properties()));
    public static final Supplier<BlockItem> ARCANE_SHRINE_BLOCK_ITEM = build(ZBlocks.Locations.ARCANE_SHRINE, () -> new BlockItem(ZBlocks.ARCANE_SHRINE_BLOCK.get(), new Item.Properties()));
    public static final Supplier<BlockItem> PEDESTAL_BLOCK_ITEM = build(ZBlocks.Locations.PEDESTAL, () -> new BlockItem(ZBlocks.PEDESTAL_BLOCK.get(), new Item.Properties()));


    private static <T extends Item> Supplier<T> build(String key, Supplier<T> item){
        return Services.PLATFORM_REGISTER.registerItem(key, item);
    }
}