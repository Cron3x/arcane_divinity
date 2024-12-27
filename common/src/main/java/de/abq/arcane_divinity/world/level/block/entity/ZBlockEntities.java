package de.abq.arcane_divinity.world.level.block.entity;

import com.mojang.datafixers.types.Type;
import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.world.level.block.ZBlocks;
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
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class ZBlockEntities {
    private static final Map<String, BlockEntityType<?>> BLOCK_ENTITIES = new HashMap<>();

    public static final Supplier<BlockEntityType<PedestalBlockEntity>>      PEDESTAL_BLOCK_ENTITY       = registerBlockEntity(ZBlocks.Locations.PEDESTAL, () ->
            BlockEntityType.Builder.of(PedestalBlockEntity::new, ZBlocks.PEDESTAL_BLOCK_SUPPLIER.get()).build(null));
    public static final Supplier<BlockEntityType<ArcaneShrineBlockEntity>>  ARCANE_SHRINE_BLOCK_ENTITY  = registerBlockEntity(ZBlocks.Locations.ARCANE_SHRINE, () ->
            BlockEntityType.Builder.of(ArcaneShrineBlockEntity::new, ZBlocks.ARCANE_SHRINE_BLOCK_SUPPLIER.get()).build(null));
    public static final Supplier<BlockEntityType<ArcaneObeliskBlockEntity>> ARCANE_OBELISK_BLOCK_ENTITY = registerBlockEntity(ZBlocks.Locations.ARCANE_OBELISK, () ->
            BlockEntityType.Builder.of(ArcaneObeliskBlockEntity::new, ZBlocks.ARCANE_OBELISK_BLOCK_SUPPLIER.get()).build(null));

    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> assign(String key, BiFunction<BlockPos, BlockState, T> fn, Block... blocks){
        Type<?> type = Util.fetchChoiceType(References.BLOCK_ENTITY, key);
        BlockEntityType<T> ret = Services.PLATFORM.createBlockEntityType(fn, type, blocks);
        var old = BLOCK_ENTITIES.put(key, ret);
        if (old != null) throw new IllegalArgumentException("ID duplicated" + key);
        return Services.PLATFORM_REGISTER.registerBlockEntity(key, () -> ret);
    }

    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(String id, Supplier<BlockEntityType<T>> blockEntity) {
        return Services.PLATFORM_REGISTER.registerBlockEntity(id, blockEntity);
    }

    public static void registerBlockEntities(BiConsumer<BlockEntityType<?>, ResourceLocation> r){
        BLOCK_ENTITIES.forEach((rl, entity) -> r.accept(entity, ArcaneDivinity.path(rl)));
    }

    public static <T extends AbstractGeoBlockEntity> String getId(BlockEntityType<T> be) {
        AtomicReference<String> returnKey = new AtomicReference<>();
        BLOCK_ENTITIES.forEach((key, entity) -> {
                    if (entity.equals(be)){
                        returnKey.set(key);            }
        });
        ArcaneDivinity.LOG.error("no id for BlockEntity: {}", be);
        return returnKey.get();
    }

    public static void init() {}
}

