package de.abq.arcane_divinity;


import com.jcraft.jorbis.Block;
import de.abq.arcane_divinity.common.block.ZBlocks;
import de.abq.arcane_divinity.common.block.block_entity.ZBlockEntities;
import de.abq.arcane_divinity.common.block.block_entity.renderers.DefaultBlockEntityRenderer;
import de.abq.arcane_divinity.common.effect.ZMobEffects;
import de.abq.arcane_divinity.common.item.ZItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Mod(ArcaneDivinity.MOD_ID)
public class ArcaneDivinityEntry {
    //public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(BuiltInRegistries., ArcaneDivinity.MOD_ID);
    private IEventBus bus;

    public ArcaneDivinityEntry(IEventBus eventBus) {
        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        ArcaneDivinity.LOG.info("Hello NeoForge world!");

        //ARMOR_MATERIALS.register(eventBus);

        eventBus.addListener((RegisterEvent event) -> {
            bindMobEffect(event, ZMobEffects::register);
            bind(event, Registries.BLOCK, ZBlocks::registerBlocks);
            bindItems(event, ZBlocks::registerBlockItems);
            bindItems(event, ZItems::registerItems );
            bind(event, Registries.BLOCK_ENTITY_TYPE, ZBlockEntities::registerBlockEntities);

            //Very ugly but it works?
            Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ARCANE_TAB,
                    CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup." + ArcaneDivinity.MOD_ID + ".arcane_tab"))
                            .icon(() -> new ItemStack(ZBlocks.arcaneShrineBlock))
                            .build()
            );
        });
        eventBus.addListener((EntityRenderersEvent.RegisterRenderers event) -> {
            event.registerBlockEntityRenderer(ZBlockEntities.ARCANE_SHRINE_BLOCK_ENTITY, DefaultBlockEntityRenderer::new);
        });
        eventBus.addListener(this::registerCapabilities);

        eventBus.addListener((BuildCreativeModeTabContentsEvent event) ->{
            if (event.getTabKey() == ARCANE_TAB){
                ZBlocks.BLOCKS.forEach((_rl, block) -> event.accept(block));
                ZItems.getItems().forEach((_rl, item) -> event.accept(item));
            }
        });

        ArcaneDivinity.init();
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
            source.accept((t, rl) -> {
                event.register(registry, rl, () -> t);
            });
        }
    }
    private void bindItems(RegisterEvent event, Consumer<BiConsumer<Item, ResourceLocation>> source){
        if (event.getRegistryKey().equals(Registries.ITEM)){
            source.accept( (t, rl) ->{
                event.register(Registries.ITEM, rl, () -> t);
            });
        }
    }

    private static <E extends RegisterEvent> void bindMobEffect(E event, Consumer<BiConsumer<MobEffect, ResourceLocation>> source){
        if (event.getRegistryKey() == Registries.MOB_EFFECT){
            source.accept((effect, rl) ->{
                event.register(Registries.MOB_EFFECT, rl, () -> effect);
                ZMobEffects.reassignEffect(rl, (Holder.Reference<MobEffect>) event.getRegistry().getHolder(rl).get());
            });
        }
    }

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderLevelStageEvent event) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY) {
            // Render the shader effect here
            if (Minecraft.getInstance().player.hasEffect(ZMobEffects.MAGIC_MUSHROOM_WARP_VISION_EFFECT)){
                ArcaneDivinity.LOG.debug("Hee");

                //WarpingRenderer.render();
            }
        }
    }

    public static final ResourceKey<CreativeModeTab> ARCANE_TAB= ResourceKey.create(Registries.CREATIVE_MODE_TAB, ArcaneDivinity.path("arcane_tab"));
}