package de.abq.arcane_divinity;

import de.abq.arcane_divinity.common.item.ZItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ArcaneDivinityEntry implements ModInitializer {
    
    @Override
    public void onInitialize() {
        bindItems(ZItems::registerItems);

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);
        ArcaneDivinity.init();
    }

    private void bindItems(Consumer<BiConsumer<Item, ResourceLocation>> source){
            source.accept((item, rl) ->{
                //TODO: Add to inv
                Registry.register(BuiltInRegistries.ITEM, rl, item);

                ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register((content) ->{
                    content.accept(item);
                });
            });
    }

    public static final ResourceKey<CreativeModeTab> CUSTOM_ITEM_GROUP_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ArcaneDivinity.path("arcane_group"));
    public static final CreativeModeTab CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ZItems.BOTTLED_JINN))
            .build();
}