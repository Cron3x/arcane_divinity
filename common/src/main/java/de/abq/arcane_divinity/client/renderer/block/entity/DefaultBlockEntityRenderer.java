package de.abq.arcane_divinity.client.renderer.block.entity;

import de.abq.arcane_divinity.world.level.block.entity.AbstractGeoBlockEntity;
import de.abq.arcane_divinity.world.level.block.entity.ArcaneShrineBlockEntity;
import de.abq.arcane_divinity.world.level.block.entity.ZBlockEntities;
import de.abq.arcane_divinity.client.defaulted.model.DefaultBlockEntityModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class DefaultBlockEntityRenderer<T extends AbstractGeoBlockEntity> extends GeoBlockRenderer<ArcaneShrineBlockEntity> {
    public DefaultBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new DefaultBlockEntityModel<>(ZBlockEntities.ARCANE_SHRINE_BLOCK_ENTITY));
    }
}
