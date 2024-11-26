package de.abq.arcane_divinity.common.item.armor;

import de.abq.arcane_divinity.ArcaneDivinityCommon;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class WingsArmorModel extends GeoModel<WingsArmorItem> {
    @Override
    public ResourceLocation getModelResource(WingsArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ArcaneDivinityCommon.MOD_ID, "armor/wings.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WingsArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ArcaneDivinityCommon.MOD_ID, "armor/wings.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WingsArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(ArcaneDivinityCommon.MOD_ID, "armor/wings.animation.json");
    }
}
