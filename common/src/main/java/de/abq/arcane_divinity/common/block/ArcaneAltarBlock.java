package de.abq.arcane_divinity.common.block;

import de.abq.arcane_divinity.common.block.block_entity.ArcaneAltarBlockEntity;
import de.abq.arcane_divinity.common.block.block_entity.SimpleInventoryGeoBlockEntity;
import de.abq.arcane_divinity.common.block.block_entity.ZBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArcaneAltarBlock extends AbstractWaterLoggableBlock implements EntityBlock{
    public ArcaneAltarBlock(Properties $$0) {
        super($$0);
    }
    @NotNull
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new ArcaneAltarBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) {
            return createTickerHelper(type, ZBlockEntities.ARCANE_ALTAR_BLOCK_ENTITY, ArcaneAltarBlockEntity::clientTick);
        } else {
            return createTickerHelper(type, ZBlockEntities.ARCANE_ALTAR_BLOCK_ENTITY, ArcaneAltarBlockEntity::serverTick);
        }

    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        if (level.isClientSide) {
            return InteractionResult.PASS;
        }
        if (!(level.getBlockEntity(pos) instanceof ArcaneAltarBlockEntity altar)) return InteractionResult.PASS;
        altar.setShouldBeActive(true);
        altar.setChanged();
        return InteractionResult.SUCCESS;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult blockHitResult) {
        if (level.isClientSide) {
            return InteractionResult.PASS;
        }
        if (!(level.getBlockEntity(pos) instanceof ArcaneAltarBlockEntity altar)) return InteractionResult.PASS;
        altar.setShouldBeActive(true);
        altar.setChanged();
        return InteractionResult.SUCCESS;
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
