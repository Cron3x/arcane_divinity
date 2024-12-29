package de.abq.arcane_divinity.world.level.block.entity;

import de.abq.arcane_divinity.world.level.block.ArcaneObeliskBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

import java.util.concurrent.ThreadLocalRandom;

public class ArcaneObeliskBlockEntity extends AbstractBlockEntity {

    private BlockPos altarPos;
    private int ticks = 0;

    public ArcaneObeliskBlockEntity(BlockPos pos, BlockState state) {
        super(ZBlockEntities.ARCANE_OBELISK_BLOCK_ENTITY.get(), pos, state);
        this.altarPos = BlockPos.ZERO;
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider $$1) {
        super.loadAdditional(tag, $$1);
        this.altarPos = BlockPos.of(tag.getLong("AltarLocation"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider $$1) {
        super.saveAdditional(tag, $$1);
        save(tag);
    }

    public CompoundTag save(CompoundTag nbt){
        if (this.altarPos != null) nbt.putLong("AltarLocation",  this.altarPos.asLong());
        return nbt;
    }

    public void setAltarPos(BlockPos pos) {
        this.altarPos = pos;
    }
    public BlockPos getAltarPos() {
        return this.altarPos;
    }

    public void update() {
        BlockState state = level.getBlockState(this.worldPosition);
        this.level.sendBlockUpdated(this.worldPosition, state, state, 3);
        this.setChanged();
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider $$0) {
        return save(super.getUpdateTag($$0));
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, ArcaneObeliskBlockEntity self) {
        if (blockState.getValue(ArcaneObeliskBlock.HALF) == DoubleBlockHalf.UPPER) return;

        ++self.ticks;

        ServerLevel serverLevel = (ServerLevel) level;

        if ((self.ticks % 2) == 0) {
            self.flameParticle(serverLevel, blockPos.below(), 5);
        }

        if ((self.ticks % 20) != 0) return;

        self.ticks = 0;

        if (serverLevel.getBlockEntity(self.altarPos) == null) return;
        if (!(serverLevel.getBlockEntity(self.altarPos).getType().equals(ZBlockEntities.ARCANE_SHRINE_BLOCK_ENTITY))) return;
        ArcaneShrineBlockEntity altar = (ArcaneShrineBlockEntity) serverLevel.getBlockEntity(self.altarPos);

        if (altar.isDay) {
            //self.flameParticle(self.getBlockPos().offset(0,3,0), new ParticleColor(255,255,255), new ParticleColor(255,150,0), 15);
        } else {

        }
    }
    public static void clientTick(Level level, BlockPos blockPos, BlockState blockState, ArcaneObeliskBlockEntity self){}

    public void flameParticle(ServerLevel serverLevel, BlockPos pos, int intensity) {
        double xzOffset = 0.25;

        for (int i = 0; i < intensity; i++) {
            //TODO: add light to pillar tip

            serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                    pos.getX() + 0.5 + ThreadLocalRandom.current().nextDouble(-xzOffset / 2, xzOffset / 2),
                    pos.getY() + 3.5 + ThreadLocalRandom.current().nextDouble(-0.05, 0.2),
                    pos.getZ() + 0.5 + ThreadLocalRandom.current().nextDouble(-xzOffset / 2, xzOffset / 2),
                    0, ThreadLocalRandom.current().nextDouble(0.0, 0.05), 0, 0, 0);
        }
        for (int i = 0; i < intensity; i++) {
            serverLevel.sendParticles(
                    ParticleTypes.ENCHANT,
                    pos.getX() + 0.5 + ThreadLocalRandom.current().nextDouble(-xzOffset, xzOffset), pos.getY() + 3.5 + ThreadLocalRandom.current().nextDouble(0, 0.7), pos.getZ() + 0.5 + ThreadLocalRandom.current().nextDouble(-xzOffset, xzOffset),
                    0, ThreadLocalRandom.current().nextDouble(0.0, 0.05f), 0,0,0);
        }
    }
}