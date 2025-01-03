package de.abq.arcane_divinity;

import de.abq.arcane_divinity.world.entity.ZEntityType;
import de.abq.arcane_divinity.world.item.ZCreativeTabs;
import de.abq.arcane_divinity.world.item.ZItems;
import de.abq.arcane_divinity.world.level.block.ZBlocks;
import de.abq.arcane_divinity.world.level.block.entity.ZBlockEntities;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public final class ArcaneDivinity {
    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.
    public static final String MOD_ID = "arcane_divinity";
    public static final String MOD_NAME = "arcane_divinity";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static ResourceLocation path(String name){
        return ResourceLocation.fromNamespaceAndPath(ArcaneDivinity.MOD_ID, name);
    }

    public static void registerEverything() {
        ZBlocks.init();
        ZBlockEntities.init();
        ZItems.init();
        ZEntityType.init();
        ZCreativeTabs.init();

        //ZEntityType.ENTITIES.forEach((id, toReg) -> Services.PLATFORM_REGISTER.registerEntity(id, () -> toReg));
    }
}