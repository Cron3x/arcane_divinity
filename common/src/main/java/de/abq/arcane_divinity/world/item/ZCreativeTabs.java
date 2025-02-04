package de.abq.arcane_divinity.world.item;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.platform.Services;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class ZCreativeTabs {
    public static void init(){}

    public static final Supplier<CreativeModeTab> ARCANE_TAB = Services.PLATFORM_REGISTER.registerCreativeModeTab("arcane_group", () -> Services.PLATFORM_REGISTER.newCreativeTabBuilder()
            .title(Component.translatable("itemGroup." + ArcaneDivinity.MOD_ID + ".arcane_group"))
            .icon(() -> new ItemStack(ZItems.PEDESTAL_BLOCK_ITEM.get()))
            .displayItems((enabledFeatures, entries) -> {
                entries.accept(ZItems.ARCANE_OBELISK_BLOCK_ITEM.get());
                entries.accept(ZItems.ARCANE_SHRINE_BLOCK_ITEM.get());
                entries.accept(ZItems.PEDESTAL_BLOCK_ITEM.get());
                entries.accept(ZItems.EA_SWORD.get());
                entries.accept(ZItems.FLAME_SWORD.get());
                entries.accept(ZItems.HALLUCINOGENIC_GRASS.get());
                entries.accept(ZItems.MAGIC_MUSHROOM.get());
            })
            .build());

}
