package de.abq.arcane_divinity.world.level.block;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.platform.Services;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public final class ZBlocks {
    public static final Map<String, Block> BLOCKS = new LinkedHashMap<>();

    public static final Block arcaneShrineBlock     = build(Locations.ARCANE_SHRINE , new ArcaneShrineBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).strength(3).noOcclusion()));
    public static final Block arcaneObeliskBlock    = build(Locations.ARCANE_OBELISK, new ArcaneObeliskBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).strength(3).noOcclusion()));
    public static final Block pedestalBlock         = build(Locations.PEDESTAL, new PedestalBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).strength(1).noOcclusion()));

    private static Block build(String key, Block block){
        BLOCKS.put(key, block);
        return block;
    }

    public static void registerBlocks(BiConsumer<Block, ResourceLocation> register){
        BLOCKS.forEach((rl, block) -> register.accept(block, ArcaneDivinity.path(rl)));
    }

    public static void registerBlockItems(BiConsumer<Item, ResourceLocation> register){
        Item.Properties props = Services.PLATFORM.defaultItemBuilder();
        BLOCKS.forEach((rl, block) -> register.accept(new BlockItem(block, props), ArcaneDivinity.path(rl)));
        //register.accept(blockItemHelper(pedestalBlock, props), BuiltInRegistries.BLOCK.getKey(pedestalBlock));
    }


    public static class Locations {
        public static final String ARCANE_SHRINE = "arcane_shrine";
        public static final String ARCANE_OBELISK =  "arcane_obelisk";
        public static final String PEDESTAL =  "pedestal";
    }
}
