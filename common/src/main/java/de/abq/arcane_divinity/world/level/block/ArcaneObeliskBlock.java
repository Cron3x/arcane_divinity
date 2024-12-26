package de.abq.arcane_divinity.world.level.block;

import com.mojang.serialization.MapCodec;
import de.abq.arcane_divinity.world.level.block.entity.ArcaneObeliskBlockEntity;
import de.abq.arcane_divinity.world.level.block.entity.ZBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class ArcaneObeliskBlock extends BaseEntityBlock {
    private static final VoxelShape SHAPE_LOWER = makeShapeLower();
    private static final VoxelShape SHAPE_UPPER = makeShapeUpper();

    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public ArcaneObeliskBlock(Properties properties)
    {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(HALF);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ZBlockEntities.ARCANE_OBELISK_BLOCK_ENTITY.create(pos, state);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        if (state.getValue(HALF).equals(DoubleBlockHalf.LOWER)) return SHAPE_LOWER;
        return SHAPE_UPPER;
    }

    public static VoxelShape makeShapeLower(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.1875, 0.8125, 0.9375, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0.625, 0.375, 1, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.625, 0, 0.625, 1, 1, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.625, 0, 0, 1, 1, 0.375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 0.375, 1, 0.375), BooleanOp.OR);

        return shape;
    }

    public static VoxelShape makeShapeUpper(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.625, 0, 0.625, 0.96875, 0.3125, 0.96875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.625, 0, 0.03125, 0.96875, 0.3125, 0.375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.03125, 0, 0.03125, 0.375, 0.3125, 0.375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.03125, 0, 0.625, 0.375, 0.3125, 0.96875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, 0.3125, 0.625, 0.375, 0.625, 0.9375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.09375, 0.625, 0.625, 0.375, 0.9375, 0.90625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.15625, 0.9375, 0.625, 0.375, 1, 0.84375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.15625, 0.9375, 0.15625, 0.375, 1, 0.375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.09375, 0.625, 0.09375, 0.375, 0.9375, 0.375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, 0.3125, 0.0625, 0.375, 0.625, 0.375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.625, 0.3125, 0.0625, 0.9375, 0.625, 0.375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.625, 0.625, 0.09375, 0.90625, 0.9375, 0.375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.625, 0.9375, 0.15625, 0.84375, 1, 0.375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.625, 0.9375, 0.625, 0.84375, 1, 0.84375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.625, 0.625, 0.625, 0.90625, 0.9375, 0.90625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.625, 0.3125, 0.625, 0.9375, 0.625, 0.9375), BooleanOp.OR);

        return shape;
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, LivingEntity pPlacer, ItemStack pStack) {
        pLevel.setBlock(pPos.above(), pState.setValue(HALF, DoubleBlockHalf.UPPER), 3);
    }

    @Override
    public BlockState playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
        return pState;
    }


    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        DoubleBlockHalf doubleblockhalf = pState.getValue(HALF);
        if (pFacing.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.LOWER == (pFacing == Direction.UP)) {
            return pFacingState.is(this) && pFacingState.getValue(HALF) != doubleblockhalf ? pState : Blocks.AIR.defaultBlockState();
        } else {
            return doubleblockhalf == DoubleBlockHalf.LOWER && pFacing == Direction.DOWN && !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
        }
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        BlockState blockstate = pLevel.getBlockState(blockpos);
        return pState.getValue(HALF) == DoubleBlockHalf.LOWER ? blockstate.isFaceSturdy(pLevel, blockpos, Direction.UP) : blockstate.is(this);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) {
            return createTickerHelper(type, ZBlockEntities.ARCANE_OBELISK_BLOCK_ENTITY, ArcaneObeliskBlockEntity::clientTick);
        } else {
            return createTickerHelper(type, ZBlockEntities.ARCANE_OBELISK_BLOCK_ENTITY, ArcaneObeliskBlockEntity::serverTick);
        }

    }
}