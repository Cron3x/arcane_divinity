package de.abq.arcane_divinity.client.defaulted.model;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.world.level.block.entity.AbstractGeoBlockEntity;
import de.abq.arcane_divinity.world.level.block.entity.ZBlockEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class DefaultBlockEntityModel<T extends AbstractGeoBlockEntity> extends GeoModel<T> {

    private static String id;

    public DefaultBlockEntityModel(BlockEntityType<T> type){
        id = ZBlockEntities.getId(type);
    }

    @Override
    public ResourceLocation getModelResource(T animatable) {
        return ArcaneDivinity.path("geo/block/%s.geo.json".formatted(id));
    }

    @Override
    public ResourceLocation getTextureResource(T animatable) {
        return ArcaneDivinity.path("textures/block/%s.png".formatted(id));
    }

    @Override
    public ResourceLocation getTextureResource(T animatable, @Nullable GeoRenderer<T> renderer) {
        return ArcaneDivinity.path("textures/block/%s.png".formatted(id));
    }

    @Override
    public ResourceLocation getModelResource(T animatable, @Nullable GeoRenderer<T> renderer) {
        return ArcaneDivinity.path("geo/block/%s.geo.json".formatted(id));
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return ArcaneDivinity.path("animations/block/%s.animation.json".formatted(id));
    }
}
