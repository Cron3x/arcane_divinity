package de.abq.arcane_divinity.common.item;

import de.abq.arcane_divinity.ArcaneDivinityCommon;
import de.abq.arcane_divinity.common.item.armor.WingsArmorItem;
import de.abq.arcane_divinity.platform.Services;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.Consumables;

import java.util.function.BiConsumer;

public class ZItems {
    private static final ResourceLocation WINGS_RL = ResourceLocation.fromNamespaceAndPath(ArcaneDivinityCommon.MOD_ID, "wings");
    public static final Item WINGS = new WingsArmorItem(Services.PLATFORM.defaultItemBuilder().rarity(Rarity.EPIC).setId(ResourceKey.create(Registries.ITEM, WINGS_RL)));

    private static final ResourceLocation MAGIC_MUSHROOM_RL = ResourceLocation.fromNamespaceAndPath(ArcaneDivinityCommon.MOD_ID, "magic_mushroom");
    public static final Item MAGIC_MUSHROOM = new Item(Services.PLATFORM.defaultItemBuilder().food(Foods.DRIED_KELP, ZConsumables.MAGIC_MUSHROOM).setId(ResourceKey.create(Registries.ITEM, MAGIC_MUSHROOM_RL)));

    public static void registerItems(BiConsumer<Item, ResourceLocation> register){
        register.accept(WINGS, WINGS_RL);
        register.accept(MAGIC_MUSHROOM, MAGIC_MUSHROOM_RL);
    }
}
