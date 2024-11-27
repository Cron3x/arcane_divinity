package de.abq.arcane_divinity.common.item;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.common.effect.ZMobEffects;
import de.abq.arcane_divinity.common.item.armor.WingsArmorItem;
import de.abq.arcane_divinity.platform.Services;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.function.BiConsumer;

public class ZItems {
    private static final ResourceLocation WINGS_RL = ResourceLocation.fromNamespaceAndPath(ArcaneDivinity.MOD_ID, "wings");
    public static final Item WINGS = new WingsArmorItem(Services.PLATFORM.defaultItemBuilder().rarity(Rarity.EPIC));

    private static final ResourceLocation MAGIC_MUSHROOM_RL = ResourceLocation.fromNamespaceAndPath(ArcaneDivinity.MOD_ID, "magic_mushroom");
    public static final Item MAGIC_MUSHROOM = new MagicMushroomItem(Services.PLATFORM.defaultItemBuilder().food(new FoodProperties.Builder().alwaysEdible().fast().nutrition(0).build()), 10);

    private static final ResourceLocation HALLUCINOGENIC_GRASS_RL = ResourceLocation.fromNamespaceAndPath(ArcaneDivinity.MOD_ID, "hallucinogenic_grass");
    public static final Item HALLUCINOGENIC_GRASS = new Item(Services.PLATFORM.defaultItemBuilder());

    private static final ResourceLocation ASH_RL = ResourceLocation.fromNamespaceAndPath(ArcaneDivinity.MOD_ID, "ash");
    public static final Item ASH = new Item(Services.PLATFORM.defaultItemBuilder());


    public static void registerItems(BiConsumer<Item, ResourceLocation> register){
        register.accept(WINGS, WINGS_RL);
        register.accept(MAGIC_MUSHROOM, MAGIC_MUSHROOM_RL);
        register.accept(HALLUCINOGENIC_GRASS, HALLUCINOGENIC_GRASS_RL);
        register.accept(ASH, ASH_RL);
    }
}