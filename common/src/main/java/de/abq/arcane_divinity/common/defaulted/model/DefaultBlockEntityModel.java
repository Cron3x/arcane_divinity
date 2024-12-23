package de.abq.arcane_divinity.common.defaulted.model;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.common.block.block_entity.AbstractGeoBlockEntity;
import de.abq.arcane_divinity.common.block.block_entity.ZBlockEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import software.bernie.geckolib.model.GeoModel;

public class DefaultBlockEntityModel<T extends AbstractGeoBlockEntity> extends GeoModel<T> {

    private static String id;

    public DefaultBlockEntityModel(BlockEntityType<T> type){
        id = ZBlockEntities.getId(type);
    }

    @Override
    public ResourceLocation getModelResource(T animatable) {
        ArcaneDivinity.LOG.debug("getModelResource: {}",id);
        return ArcaneDivinity.path("geo/block/%s.geo.json".formatted(id));
    }

    @Override
    public ResourceLocation getTextureResource(T animatable) {
        ArcaneDivinity.LOG.debug("getTextureResource: {}",id);
        return ArcaneDivinity.path("textures/block/%s.png".formatted(id));
    }


    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        ArcaneDivinity.LOG.debug("getAnimationResource: {}",id);
        return ArcaneDivinity.path("animations/block/%s.animation.json".formatted(id));
    }
}
