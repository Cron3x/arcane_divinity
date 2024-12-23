package de.abq.arcane_divinity.common.block.block_entity.renderers;

import de.abq.arcane_divinity.common.block.block_entity.AbstractGeoBlockEntity;
import de.abq.arcane_divinity.common.block.block_entity.ArcaneShrineBlockEntity;
import de.abq.arcane_divinity.common.block.block_entity.ZBlockEntities;
import de.abq.arcane_divinity.common.defaulted.model.DefaultBlockEntityModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class DefaultBlockEntityRenderer<T extends AbstractGeoBlockEntity> extends GeoBlockRenderer<ArcaneShrineBlockEntity> {
    public DefaultBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new DefaultBlockEntityModel<>(ZBlockEntities.ARCANE_SHRINE_BLOCK_ENTITY));
    }
}
