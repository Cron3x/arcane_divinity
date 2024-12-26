package de.abq.arcane_divinity;

import de.abq.arcane_divinity.world.entity.ZEntityType;
import de.abq.arcane_divinity.world.level.block.ZBlocks;
import de.abq.arcane_divinity.world.level.block.entity.ZBlockEntities;
import de.abq.arcane_divinity.client.renderer.block.entity.DefaultBlockEntityRenderer;
import de.abq.arcane_divinity.world.item.ZItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ArcaneDivinityEntry implements ModInitializer {
    
    @Override
    public void onInitialize() {
        bindItemLike(BuiltInRegistries.ITEM, ZItems::registerItems);

        bindItemLike(BuiltInRegistries.ITEM, ZBlocks::registerBlockItems);
        bind(BuiltInRegistries.BLOCK, ZBlocks::registerBlocks);
        bind(BuiltInRegistries.BLOCK_ENTITY_TYPE, ZBlockEntities::registerBlockEntities);
        bind(BuiltInRegistries.ENTITY_TYPE, ZEntityType::registerEntities);

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);
        ArcaneDivinity.init();

    }

    private <T extends ItemLike> void bindItemLike(Registry<T> registry, Consumer<BiConsumer<T, ResourceLocation>> source){
        source.accept((t, rl) -> {
            Registry.register(registry, rl, t);

            ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register((content) ->{
                content.accept(t);
            });
        });
    }

    private <T> void bind(Registry<T> reg, Consumer<BiConsumer<T, ResourceLocation>> source){
            source.accept((item, rl) ->{
                Registry.register(reg, rl, item);
            });
    }

    public static final ResourceKey<CreativeModeTab> CUSTOM_ITEM_GROUP_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ArcaneDivinity.path("arcane_group"));
    public static final CreativeModeTab CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ZItems.BOTTLED_JINN))
            .title(Component.translatable("itemGroup." + ArcaneDivinity.MOD_ID + ".arcane_tab"))
            .build();
}