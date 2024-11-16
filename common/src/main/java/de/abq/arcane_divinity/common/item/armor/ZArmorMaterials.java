package de.abq.arcane_divinity.common.item.armor;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.platform.Services;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.EnumMap;
import java.util.function.Supplier;

public class ZArmorMaterials {
    public static void init(){}

    public static final ArmorMaterial WINGS = new ArmorMaterial(1200, Util.make(new EnumMap<>(ArmorType.class), attribute -> {
        attribute.put(ArmorType.HELMET, 5);
        attribute.put(ArmorType.CHESTPLATE, 9);
        attribute.put(ArmorType.LEGGINGS, 7);
        attribute.put(ArmorType.BOOTS, 5);
        attribute.put(ArmorType.BODY, 11);
    }), 16, SoundEvents.ARMOR_EQUIP_ELYTRA,2f, 0.7f, ItemTags.REPAIRS_LEATHER_ARMOR, ArcaneDivinity.defaultResourceLocation("wings"));

    //**UNUSED**//
    /*
    private static <T extends ArmorMaterial> T registerArmorMaterial(String id, Supplier<T> armorMaterial) {
        return Services.PLATFORM.registerArmorMaterial(id, armorMaterial);
    }*/
}