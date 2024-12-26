package de.abq.arcane_divinity.world.item;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.world.item.armor.WingsArmorItem;
import de.abq.arcane_divinity.platform.Services;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ZItems {
    public static final Map<String, Item> ITEMS = new LinkedHashMap<>();

    public static final Item WINGS = build("wings", new WingsArmorItem(Services.PLATFORM.defaultItemBuilder().rarity(Rarity.EPIC)));
    public static final Item MAGIC_MUSHROOM = build("magic_mushroom", new MagicMushroomItem(Services.PLATFORM.defaultItemBuilder().food(new FoodProperties.Builder().alwaysEdible().nutrition(0).build()), 10));
    public static final Item HALLUCINOGENIC_GRASS = build("hallucinogenic_grass", new Item(Services.PLATFORM.defaultItemBuilder()));
    public static final Item ASH = build("ash", new Item(Services.PLATFORM.defaultItemBuilder()));
    public static final Item BOTTLED_JINN = build(BottledJinnItem.IDENTIFIER, new BottledJinnItem(Services.PLATFORM.defaultItemBuilder()));

    public static final Item EA_SWORD = build(EaSwordItem.IDENTIFIER, new EaSwordItem(Tiers.NETHERITE, Services.PLATFORM.defaultItemBuilder().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 5, -2f))));

    private static Item build(String key, Item item){
        ITEMS.put(key, item);
        return item;
    }
    public static void registerItems(BiConsumer<Item, ResourceLocation> register){
        ITEMS.forEach((rl, i) -> register.accept(i, ArcaneDivinity.path(rl)));
    }
}