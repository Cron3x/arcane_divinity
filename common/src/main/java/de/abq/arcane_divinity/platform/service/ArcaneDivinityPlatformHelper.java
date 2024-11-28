package de.abq.arcane_divinity.platform.service;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.types.Type;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;

/**
 * Loader-agnostic service interface for general loader-specific functions
 */
public interface ArcaneDivinityPlatformHelper {
    //<T extends ArmorMaterial> T registerArmorMaterial(String id, Supplier<T> armorMaterial);

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {
        return isDevelopmentEnvironment() ? "development" : "production";
    }

    Item.Properties defaultItemBuilder();

    <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(BiFunction<BlockPos, BlockState, T> fn, Type<?> type, Block... blocks);

    default Item.Properties defaultItemBuilderWithCustomDamageOnFabric(){
        return defaultItemBuilder();
    }

    /**
     * @param level
     * @param state
     * @param pos
     * @param ps
     * @param buffers
     * @param overlay
     */
    void tessellateBlock(Level level, BlockState state, BlockPos pos, PoseStack ps, MultiBufferSource buffers, int overlay);
}
