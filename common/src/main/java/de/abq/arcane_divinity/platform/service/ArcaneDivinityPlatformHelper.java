package de.abq.arcane_divinity.platform.service;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
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

    <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(BiFunction<BlockPos, BlockState, T> fn, Block... blocks);

    default Item.Properties defaultItemBuilderWithCustomDamageOnFabric(){
        return defaultItemBuilder();
    }

    /**
     * @param original
     * @return
     */
    BakedModel wrapPlatformModel(BakedModel original);

    /**
     *
     * @param texture
     * @param filter
     * @param mipmap
     */
    void setFilterSave(AbstractTexture texture, boolean filter, boolean mipmap);

    /**
     * @param texture
     */
    void restoreLastFilter(AbstractTexture texture);

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
