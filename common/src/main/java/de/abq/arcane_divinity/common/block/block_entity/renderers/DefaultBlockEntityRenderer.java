package de.abq.arcane_divinity.common.block.block_entity.renderers;

import de.abq.arcane_divinity.common.block.block_entity.ArcaneShrineBlockEntity;
import de.abq.arcane_divinity.common.block.block_entity.ZBlockEntities;
import de.abq.arcane_divinity.common.block.model.DefaultBlockModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class DefaultBlockEntityRenderer<T extends BlockEntity> extends GeoBlockRenderer<ArcaneShrineBlockEntity> {
    public DefaultBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new DefaultBlockModel<>(ZBlockEntities.ARCANE_SHRINE_BLOCK_ENTITY));
    }
}
