package de.abq.arcane_divinity.common.block.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;

public abstract class AbstractGeoBlockEntity extends AbstractBlockEntity implements GeoBlockEntity {
    public AbstractGeoBlockEntity(BlockEntityType<?> $$0, BlockPos $$1, BlockState $$2) {
        super($$0, $$1, $$2);
    }
}
