package de.abq.arcane_divinity.world.item.armor;

import de.abq.arcane_divinity.ArcaneDivinity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WingsArmorModel extends GeoModel<WingsArmorItem> {
    @Override
    public ResourceLocation getModelResource(WingsArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ArcaneDivinity.MOD_ID, "armor/wings.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WingsArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ArcaneDivinity.MOD_ID, "armor/wings.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WingsArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ArcaneDivinity.MOD_ID, "armor/wings.animation.json");
    }
}
