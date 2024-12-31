package de.abq.arcane_divinity.world.level.block.entity;


import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class PedestalBlockEntity extends SimpleInventoryBlockEntity {

    private boolean shouldAnimate = false;

    public PedestalBlockEntity(BlockPos pos, BlockState state) {
        super(ZBlockEntities.PEDESTAL_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public SimpleContainer createItemHandler() {
        return new SimpleContainer(1) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        };
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider $$1) {
        super.loadAdditional(tag, $$1);
        this.shouldAnimate = tag.getBoolean("shouldAnimate");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider $$1) {
        super.saveAdditional(tag, $$1);
        tag.putBoolean("shouldAnimate", this.shouldAnimate);
    }

    public boolean isShouldAnimate() {
        return shouldAnimate;
    }
    public void setShouldAnimate(boolean shouldAnimate) {
        this.shouldAnimate = shouldAnimate;
    }
}
