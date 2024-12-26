package de.abq.arcane_divinity;


import de.abq.arcane_divinity.client.renderer.Renderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = ArcaneDivinity.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public final class ArcaneDivinityClientEntry {
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        Renderers.Entities.register(event::registerEntityRenderer);
        Renderers.Blocks.register(event::registerBlockEntityRenderer);
    }
}
