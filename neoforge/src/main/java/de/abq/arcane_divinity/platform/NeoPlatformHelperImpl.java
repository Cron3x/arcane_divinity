package de.abq.arcane_divinity.platform;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.types.Type;
import de.abq.arcane_divinity.platform.service.ArcaneDivinityPlatformHelper;
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
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

import java.util.Arrays;
import java.util.function.BiFunction;

public final class NeoPlatformHelperImpl implements ArcaneDivinityPlatformHelper {

    /*
    @Override
    public <T extends ArmorMaterial> T registerArmorMaterial(String id, Supplier<T> armorMaterial) {
        //return ArcaneDivinityNF.ARMOR_MATERIALS.register(id, armorMaterial).get();
        return null;
    }*/

    @Override
    public String getPlatformName() {
        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

    @Override
    public Item.Properties defaultItemBuilder() {
        return new Item.Properties();
    }

    @Override
    public <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(BiFunction<BlockPos, BlockState, T> fn, Type<?> type, Block ...blocks) {
        return BlockEntityType.Builder.of(fn::apply, blocks).build(type);
    }

    @Override
    public void tessellateBlock(Level level, BlockState state, BlockPos pos, PoseStack ps, MultiBufferSource buffers,
                                int overlay) {
        System.out.println("UNIMPLEMENTED: tesselationBlock (NeoForgePlatform)");
    }

    @Override
    public void restoreLastFilter(AbstractTexture texture) {
        System.out.println("UNIMPLEMENTED: resoreLastFilter (NeoForgePlatform)");
    }

    @Override
    public BakedModel wrapPlatformModel(BakedModel original) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setFilterSave(AbstractTexture texture, boolean filter, boolean mipmap) {
        // TODO Auto-generated method stub
    }
}