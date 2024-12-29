package de.abq.arcane_divinity.client.model.block.entity;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.world.level.block.ZBlocks;
import de.abq.arcane_divinity.world.level.block.entity.ArcaneShrineBlockEntity;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;

public class ArcaneShrineBlockEntityModel extends DefaultedBlockGeoModel<ArcaneShrineBlockEntity> {
    public ArcaneShrineBlockEntityModel(){
        super(ArcaneDivinity.path(ZBlocks.Locations.ARCANE_SHRINE));
    }
}
