package de.abq.arcane_divinity;


import de.abq.arcane_divinity.common.block.ZBlocks;
import de.abq.arcane_divinity.common.block.block_entity.ZBlockEntities;
import de.abq.arcane_divinity.common.block.block_entity.renderers.DefaultBlockEntityRenderer;
import de.abq.arcane_divinity.common.item.ZItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod(ArcaneDivinity.MOD_ID)
public class ArcaneDivinityEntry {
    //public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(BuiltInRegistries., ArcaneDivinity.MOD_ID);
    public ArcaneDivinityEntry(IEventBus eventBus) {
        ArcaneDivinity.init();
        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        ArcaneDivinity.LOG.info("Hello NeoForge world!");

        //ARMOR_MATERIALS.register(eventBus);

        eventBus.addListener((RegisterEvent event) -> {
            bind(event, Registries.BLOCK, ZBlocks::registerBlocks);
            bindItems(event, ZBlocks::registerBlockItems);
            bindItems(event, ZItems::registerItems );
            bind(event, Registries.BLOCK_ENTITY_TYPE, ZBlockEntities::registerBlockEntities);

        });
        eventBus.addListener((EntityRenderersEvent.RegisterRenderers event) -> {
            event.registerBlockEntityRenderer(ZBlockEntities.ARCANE_ALTAR_BLOCK_ENTITY, DefaultBlockEntityRenderer::new);
        });
        eventBus.addListener(this::registerCapabilities);
    }

    private void registerCapabilities(final RegisterCapabilitiesEvent evt) {
        /*evt.registerItem(
                CuriosCapability.ITEM,
                (stack, context) -> new ICurio() {

                    @Override
                    public ItemStack getStack() {
                        return stack;
                    }

                    @Override
                    public void curioTick(SlotContext slotContext) {
                        // ticking logic here
                    },

                    );
                }
        );*/
    }

    private static <E extends RegisterEvent, T> void bind(E event, ResourceKey<Registry<T>> registry, Consumer<BiConsumer<T, ResourceLocation>> source){
        if (registry.equals(event.getRegistryKey())){
            source.accept((t, rl) -> event.register(registry, rl, () -> t));
        }
    }
    private void bindItems(RegisterEvent event, Consumer<BiConsumer<Item, ResourceLocation>> source){
        if (event.getRegistryKey().equals(Registries.ITEM)){
            source.accept( (t, rl) ->{
                //TODO: Add to inv
                event.register(Registries.ITEM, rl, () -> t);
            });
        }
    }
}