package de.abq.arcane_divinity.client.renderer;

import de.abq.arcane_divinity.client.renderer.block.entity.DefaultBlockEntityRenderer;
import de.abq.arcane_divinity.client.renderer.entity.vfx.RuptureBeamRenderer;
import de.abq.arcane_divinity.world.entity.ZEntityType;
import de.abq.arcane_divinity.world.level.block.entity.ZBlockEntities;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.BiConsumer;

public class Renderers {

    public static class Entities {
        public static void register(BiConsumer<EntityType<? extends Entity>, EntityRendererProvider> entityRenderers) {
            entityRenderers.accept(ZEntityType.RUPTURE_BEAM, RuptureBeamRenderer::new);
        }
    }

    public static class Blocks {
        public static void register(BiConsumer<BlockEntityType<? extends BlockEntity>, BlockEntityRendererProvider> entityRenderers) {
            entityRenderers.accept(ZBlockEntities.ARCANE_SHRINE_BLOCK_ENTITY.get(), DefaultBlockEntityRenderer::new);
        }
    }
}
