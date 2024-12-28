package de.abq.arcane_divinity.client.renderer.block.entity;

import de.abq.arcane_divinity.client.model.block.entity.ArcaneShrineBlockEntityModel;
import de.abq.arcane_divinity.world.level.block.entity.AbstractGeoBlockEntity;
import de.abq.arcane_divinity.world.level.block.entity.ArcaneShrineBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ArcaneShrineBlockEntityRenderer<T extends AbstractGeoBlockEntity> extends GeoBlockRenderer<ArcaneShrineBlockEntity> {
    public ArcaneShrineBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new ArcaneShrineBlockEntityModel());
    }
}
