package de.abq.arcane_divinity.common.block.block_entity;

import com.mojang.datafixers.types.Type;
import de.abq.arcane_divinity.common.block.ZBlockNames;
import de.abq.arcane_divinity.common.block.ZBlocks;
import de.abq.arcane_divinity.platform.Services;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class ZBlockEntities {
    private static final Map<ResourceLocation, BlockEntityType<?>> ALL = new HashMap<>();

    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_BLOCK_ENTITY = assign(ZBlockNames.PEDESTAL, PedestalBlockEntity::new, ZBlocks.pedestalBlock);
    public static final BlockEntityType<ArcaneShrineBlockEntity> ARCANE_ALTAR_BLOCK_ENTITY = assign(ZBlockNames.ARCANE_SHRINE, ArcaneShrineBlockEntity::new, ZBlocks.arcaneShrineBlock);
    public static final BlockEntityType<ArcaneObeliskBlockEntity> ARCANE_OBELISK_BLOCK_ENTITY = assign(ZBlockNames.ARCANE_OBELISK, ArcaneObeliskBlockEntity::new, ZBlocks.arcaneObeliskBlock);

    private static <T extends BlockEntity> BlockEntityType<T> assign(ResourceLocation id, BiFunction<BlockPos, BlockState, T> fn, Block... blocks){

        Type<?> type = Util.fetchChoiceType(References.BLOCK_ENTITY, id.getPath());
        BlockEntityType<T> ret = Services.PLATFORM.createBlockEntityType(fn, type, blocks);
        var old = ALL.put(id, ret);
        if (old != null) throw new IllegalArgumentException("ID duplicated" + id);
        return ret;
    }

    public static void registerBlockEntities(BiConsumer<BlockEntityType<?>, ResourceLocation> r){
        for (var be : ALL.entrySet()){
            r.accept(be.getValue(),be.getKey());
        }
    }

    public static <T extends AbstractGeoBlockEntity> String getId(BlockEntityType<T> be) {
        for (var type : ALL.entrySet()){
            if (type.getValue().equals(be)){
                return type.getKey().getPath();
            }
        }
        return null;
    }
}

