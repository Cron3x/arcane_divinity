package de.abq.arcane_divinity.common.block.model;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.common.block.block_entity.AbstractGeoBlockEntity;
import de.abq.arcane_divinity.common.block.block_entity.ZBlockEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import software.bernie.geckolib.model.GeoModel;

// TODO: make less boilerplate code, make this the default Class and make it of some type, than
public class DefaultBlockModel<T extends AbstractGeoBlockEntity> extends GeoModel<T> {

    private String id = null;

    public DefaultBlockModel(BlockEntityType<T> type){
        this.id = ZBlockEntities.getId(type);
    }

    @Override
    public ResourceLocation getModelResource(T animatable) {
        return ResourceLocation.fromNamespaceAndPath(ArcaneDivinity.MOD_ID, "geo/block/%s.geo.json".formatted(id));
    }

    @Override
    public ResourceLocation getTextureResource(T animatable) {
        return ResourceLocation.fromNamespaceAndPath(ArcaneDivinity.MOD_ID, "textures/block/%s.png".formatted(id));
    }


    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return ResourceLocation.fromNamespaceAndPath(ArcaneDivinity.MOD_ID, "animations/block/%s.animation.json".formatted(id));
    }
}
