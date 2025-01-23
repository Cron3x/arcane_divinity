package de.abq.arcane_divinity.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractBlockEntity extends BlockEntity {
    public AbstractBlockEntity(BlockEntityType<?> $$0, BlockPos $$1, BlockState $$2) {
        super($$0, $$1, $$2);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider $$1) {
        super.saveAdditional(tag, $$1);
        writePacketNBT(tag,$$1);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider $$1) {
        super.loadAdditional(tag, $$1);
        readPacketNBT(tag,$$1);
    }

    public void writePacketNBT(CompoundTag cmp, HolderLookup.Provider provider) {}

    public void readPacketNBT(CompoundTag cmp, HolderLookup.Provider provider) {}

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider $$0) {
        var tag = super.getUpdateTag($$0);
        saveAdditional(tag, $$0);
        return tag;

    }
}
