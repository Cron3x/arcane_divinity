package de.abq.arcane_divinity;

import de.abq.arcane_divinity.common.item.ZItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ArcaneDivinityEntry implements ModInitializer {
    
    @Override
    public void onInitialize() {
        bindItems(ZItems::registerItems);

        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        ArcaneDivinity.LOG.info("Hello Fabric world!");
        ArcaneDivinity.init();
    }

    private void bindItems(Consumer<BiConsumer<Item, ResourceLocation>> source){
            source.accept((t, rl) ->{
                //TODO: Add to inv
                Registry.register(BuiltInRegistries.ITEM, rl, t);
            });
    }
}