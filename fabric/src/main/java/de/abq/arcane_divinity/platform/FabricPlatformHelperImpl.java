package de.abq.arcane_divinity.platform;

import com.mojang.blaze3d.vertex.PoseStack;
import de.abq.arcane_divinity.ArcaneDivinityCommon;
import de.abq.arcane_divinity.platform.service.ArcaneDivinityPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class FabricPlatformHelperImpl implements ArcaneDivinityPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public Item.Properties defaultItemBuilder() {
        return new Item.Properties();
    }

    @Override
    public <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(BiFunction<BlockPos, BlockState, T> fn, Block... blocks) {
        return null;
    }

    @Override
    public BakedModel wrapPlatformModel(BakedModel original) {
        return null;
    }

    @Override
    public void setFilterSave(AbstractTexture texture, boolean filter, boolean mipmap) {

    }

    @Override
    public void restoreLastFilter(AbstractTexture texture) {

    }

    @Override
    public void tessellateBlock(Level level, BlockState state, BlockPos pos, PoseStack ps, MultiBufferSource buffers, int overlay) {

    }


    private static <T, R extends Registry<? super T>> Holder<T> registerHolder(R registry, String id, Supplier<T> object) {
        return Registry.registerForHolder((Registry<T>)registry, ResourceLocation.fromNamespaceAndPath(ArcaneDivinityCommon.MOD_ID, id), object.get());
    }
}
