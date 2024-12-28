package de.abq.arcane_divinity;

import de.abq.arcane_divinity.world.entity.ZEntityType;
import de.abq.arcane_divinity.world.level.block.ZBlocks;
import de.abq.arcane_divinity.world.level.block.entity.ZBlockEntities;
import de.abq.arcane_divinity.world.item.ZItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
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

public class ArcaneDivinityFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        ArcaneDivinity.init();
    }
}