package de.abq.arcane_divinity.common.item;

import de.abq.arcane_divinity.ArcaneDivinity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class DefaultItemModel<T extends Item & GeoAnimatable> extends GeoModel<T> {

    private final String name;

    public DefaultItemModel(String name) {
        this.name = name;
    }

    @Override
    public ResourceLocation getModelResource(T animatable) {
        return ResourceLocation.fromNamespaceAndPath(ArcaneDivinity.MOD_ID, "%s.geo.json".formatted(name));
    }

    @Override
    public ResourceLocation getTextureResource(T animatable) {
        return ResourceLocation.fromNamespaceAndPath(ArcaneDivinity.MOD_ID, "%s.png".formatted(name));
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return ResourceLocation.fromNamespaceAndPath(ArcaneDivinity.MOD_ID, "%s.animation.json".formatted(name));
    }
}
