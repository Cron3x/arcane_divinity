package de.abq.arcane_divinity;

import de.abq.arcane_divinity.client.renderer.Renderers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ArcaneDivinityClientEntry implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Renderers.Entities.register(EntityRendererRegistry::register);
    }
}
