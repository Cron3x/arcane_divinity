package de.abq.arcane_divinity.world.level.block;

import de.abq.arcane_divinity.world.level.block.entity.PedestalBlockEntity;
import de.abq.arcane_divinity.world.level.block.entity.SimpleInventoryBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PedestalBlock extends AbstractBlock implements EntityBlock {
    public PedestalBlock(Properties $$0) {
        super($$0);
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
        if (!newState.is(state.getBlock())) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof SimpleInventoryBlockEntity inventory) {
                Containers.dropContents(world, pos, inventory.getItemHandler());
            }
            super.onRemove(state, world, pos, newState, isMoving);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PedestalBlockEntity(blockPos, blockState);
    }
}
