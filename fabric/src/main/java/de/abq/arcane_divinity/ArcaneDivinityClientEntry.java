package de.abq.arcane_divinity;

import de.abq.arcane_divinity.client.render.Renderers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public final class ArcaneDivinityClientEntry implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Renderers.Entities.register(EntityRendererRegistry::register);
        Renderers.Blocks.register(BlockEntityRenderers::register);
    }
}
