package de.abq.arcane_divinity.world.level.block;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.world.item.catalyst.ICatalystItem;
import de.abq.arcane_divinity.world.level.block.entity.ArcaneShrineBlockEntity;
import de.abq.arcane_divinity.world.level.block.entity.SimpleInventoryGeoBlockEntity;
import de.abq.arcane_divinity.world.level.block.entity.ZBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArcaneShrineBlock extends AbstractContainerBlock {
    public ArcaneShrineBlock() {
        super(Properties.of().noOcclusion());
    }

    @NotNull
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new ArcaneShrineBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) {
            return createTickerHelper(type, ZBlockEntities.ARCANE_SHRINE_BLOCK_ENTITY.get(), ArcaneShrineBlockEntity::clientTick);
        } else {
            return createTickerHelper(type, ZBlockEntities.ARCANE_SHRINE_BLOCK_ENTITY.get(), ArcaneShrineBlockEntity::serverTick);
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide) return ItemInteractionResult.SUCCESS;
        if (!(level.getBlockEntity(pos) instanceof ArcaneShrineBlockEntity blockEntity)) return ItemInteractionResult.FAIL;

        if (!(stack.getItem() instanceof ICatalystItem)) {
            handleInteraction((ServerPlayer) player, blockEntity, stack);
            return ItemInteractionResult.CONSUME;
        }

        blockEntity.triggerActivation(level);
        if (!blockEntity.isActive() && !blockEntity.shouldBeActive()){
        } else {
            blockEntity.initCrafting();
        }

        return ItemInteractionResult.CONSUME;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) return InteractionResult.FAIL;
        if (!(level.getBlockEntity(pos) instanceof ArcaneShrineBlockEntity blockEntity)) return InteractionResult.FAIL;

        handleInteraction((ServerPlayer) player, blockEntity);

        return InteractionResult.SUCCESS_NO_ITEM_USED;
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof SimpleInventoryGeoBlockEntity inventory) {
                Containers.dropContents(world, pos, inventory.getItemHandler());
            }
            super.onRemove(state, world, pos, newState, isMoving);
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState $$0) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}
