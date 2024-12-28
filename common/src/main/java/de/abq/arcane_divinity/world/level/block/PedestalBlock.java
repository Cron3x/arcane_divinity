package de.abq.arcane_divinity.world.level.block;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.world.level.block.entity.PedestalBlockEntity;
import de.abq.arcane_divinity.world.level.block.entity.SimpleInventoryBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
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
        if (level.isClientSide) return ItemInteractionResult.SUCCESS;
        if (!(level.getBlockEntity(pos) instanceof PedestalBlockEntity pedestalBlockEntity)) return ItemInteractionResult.FAIL;

        handleInteraction((ServerPlayer) player, pedestalBlockEntity, stack);

        return ItemInteractionResult.CONSUME;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) return InteractionResult.FAIL;
        if (!(level.getBlockEntity(pos) instanceof PedestalBlockEntity pedestalBlockEntity)) return InteractionResult.FAIL;

        handleInteraction((ServerPlayer) player, pedestalBlockEntity);

        return InteractionResult.SUCCESS_NO_ITEM_USED;
    }

    @Override
    protected void attack(BlockState state, Level level, BlockPos pos, Player player) {
        if (level.isClientSide) return;
        if (!(level.getBlockEntity(pos) instanceof PedestalBlockEntity entity)) return;
        entity.setShouldAnimate(!entity.isShouldAnimate());
        entity.setChanged();
    }


    /**
     * *WARNING*: This function looks like it could handle multiple stacks, but it can't, just easy that way
     * @param stacks should only be set to one, it would override other stacks
    */
    private void handleInteraction(ServerPlayer player, PedestalBlockEntity entity, ItemStack... stacks){
        Container container = entity.getItemHandler();
        ItemStack returned = container.getItem(0);
        for (ItemStack stack : stacks){
            container.setItem(0,new ItemStack(stack.getItem(), 1));
            stack.setCount(stack.getCount()-1);
        }

        Inventory pInv = player.getInventory();

        if (pInv.getFreeSlot() <= 0){
            player.level().addFreshEntity(new ItemEntity(entity.getLevel(), entity.getBlockPos().getX()+0.5, entity.getBlockPos().getY()+1, entity.getBlockPos().getZ()+0.5, returned));
        } else {
            player.getInventory().add(returned);
        }
        entity.setChanged();
    }
}
