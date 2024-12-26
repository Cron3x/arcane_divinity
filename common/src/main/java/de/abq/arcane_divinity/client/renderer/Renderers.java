package de.abq.arcane_divinity.client.renderer;

import de.abq.arcane_divinity.client.renderer.entity.vfx.RuptureBeamRenderer;
import de.abq.arcane_divinity.world.entity.ZEntityType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.BiConsumer;

public class Renderers {

    static class Entities {
        public static void register(BiConsumer<EntityType<? extends Entity>, EntityRendererProvider> entityRenderers) {
            entityRenderers.accept(ZEntityType.RUPTURE_BEAM, RuptureBeamRenderer::new);
        }
    }

    static class block {
        public static void register(BiConsumer<EntityType<? extends Entity>, EntityRendererProvider> entityRenderers) {
            entityRenderers.accept(ZEntityType.RUPTURE_BEAM, RuptureBeamRenderer::new);
        }
    }
}
