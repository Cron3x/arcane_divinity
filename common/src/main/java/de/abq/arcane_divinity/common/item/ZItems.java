package de.abq.arcane_divinity.common.item;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.common.item.armor.WingsArmorItem;
import de.abq.arcane_divinity.platform.Services;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.BiConsumer;

public class ZItems {
    private static final ResourceLocation WINGSRL = ResourceLocation.fromNamespaceAndPath(ArcaneDivinity.MOD_ID, "wings");

    public static final Item WINGS = new WingsArmorItem(Services.PLATFORM.defaultItemBuilder().rarity(Rarity.EPIC).setId(ResourceKey.create(Registries.ITEM, WINGSRL)));

    public static void registerItems(BiConsumer<Item, ResourceLocation> register){
        register.accept(WINGS, WINGSRL);
    }
}
