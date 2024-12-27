package de.abq.arcane_divinity.client.model.block.entity;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.client.defaulted.model.DefaultBlockEntityModel;
import de.abq.arcane_divinity.world.level.block.ArcaneShrineBlock;
import de.abq.arcane_divinity.world.level.block.ZBlocks;
import de.abq.arcane_divinity.world.level.block.entity.AbstractGeoBlockEntity;
import de.abq.arcane_divinity.world.level.block.entity.ArcaneShrineBlockEntity;
import de.abq.arcane_divinity.world.level.block.entity.ZBlockEntities;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ArcaneShrineBlockEntityModel extends DefaultedBlockGeoModel<ArcaneShrineBlockEntity> {
    public ArcaneShrineBlockEntityModel(){
        super(ArcaneDivinity.path(ZBlocks.Locations.ARCANE_SHRINE));
    }
}
