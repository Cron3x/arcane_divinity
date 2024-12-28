package de.abq.arcane_divinity.world.level.block;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.world.level.block.entity.PedestalBlockEntity;
import de.abq.arcane_divinity.world.level.block.entity.SimpleInventoryBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PedestalBlock extends AbstractBlock implements EntityBlock {
    public PedestalBlock() {
        super(Properties.of().noOcclusion().destroyTime(3));
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
        ArcaneDivinity.LOG.debug("onRemove");
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
        ArcaneDivinity.LOG.debug("newBlockEntity");
        return new PedestalBlockEntity(blockPos, blockState);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ArcaneDivinity.LOG.debug("used Item: called");
        if (level.isClientSide) return ItemInteractionResult.SUCCESS;
        ArcaneDivinity.LOG.debug("ServerSide");
        if (!(level.getBlockEntity(pos) instanceof PedestalBlockEntity pedestalBlockEntity)) return ItemInteractionResult.FAIL;
        ArcaneDivinity.LOG.debug("used with item: exec");

        Container container = pedestalBlockEntity.getItemHandler();
        ItemStack returned = container.getItem(0);
        //container.clearContent();
        stack.setCount(stack.getCount()-1);
        container.setItem(0,new ItemStack(stack.getItem(), 1));
        player.getInventory().add(returned);
        pedestalBlockEntity.setChanged();

        return ItemInteractionResult.CONSUME;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        ArcaneDivinity.LOG.debug("used without item: called");
        if (level.isClientSide) return InteractionResult.FAIL;
        ArcaneDivinity.LOG.debug("ServerSide");
        if (!(level.getBlockEntity(pos) instanceof PedestalBlockEntity pedestalBlockEntity)) return InteractionResult.FAIL;
        ArcaneDivinity.LOG.debug("used without item: exec");

        Container container = pedestalBlockEntity.getItemHandler();
        player.addItem(container.getItem(0));
        container.clearContent();
        pedestalBlockEntity.setChanged();

        return InteractionResult.SUCCESS_NO_ITEM_USED;
    }
}
