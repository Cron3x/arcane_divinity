package de.abq.arcane_divinity.world.level.block.entity;


import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.block.state.BlockState;

public class PedestalBlockEntity extends SimpleInventoryBlockEntity {

    public PedestalBlockEntity(BlockPos pos, BlockState state) {
        super(ZBlockEntities.PEDESTAL_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected SimpleContainer createItemHandler() {
        return new SimpleContainer(1) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        };
    }
}
