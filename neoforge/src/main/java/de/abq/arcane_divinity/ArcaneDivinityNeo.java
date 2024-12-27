package de.abq.arcane_divinity;


import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod(ArcaneDivinity.MOD_ID)
public final class ArcaneDivinityNeo {
    public static final DeferredRegister<Item> ITEM_DEFERRED_REGISTER = DeferredRegister.create(Registries.ITEM, ArcaneDivinity.MOD_ID);
    public static final DeferredRegister<Block> BLOCK_DEFERRED_REGISTER  = DeferredRegister.create(Registries.BLOCK, ArcaneDivinity.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_DEFERRED_REGISTER  = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ArcaneDivinity.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE_DEFERRED_REGISTER = DeferredRegister.create(Registries.ENTITY_TYPE, ArcaneDivinity.MOD_ID);
    public static final DeferredRegister<MobEffect> MOB_EFFECT_DEFERRED_REGISTER = DeferredRegister.create(Registries.MOB_EFFECT, ArcaneDivinity.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB_DEFERRED_REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ArcaneDivinity.MOD_ID);
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIAL_DEFERRED_REGISTER = DeferredRegister.create(Registries.ARMOR_MATERIAL, ArcaneDivinity.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENT_DEFERRED_REGISTER = DeferredRegister.create(Registries.SOUND_EVENT, ArcaneDivinity.MOD_ID);

    public ArcaneDivinityNeo(IEventBus eventBus) {

        BLOCK_ENTITY_DEFERRED_REGISTER.register(eventBus);
        BLOCK_DEFERRED_REGISTER.register(eventBus);
        ENTITY_TYPE_DEFERRED_REGISTER.register(eventBus);
        ITEM_DEFERRED_REGISTER.register(eventBus);
        MOB_EFFECT_DEFERRED_REGISTER.register(eventBus);
        CREATIVE_MODE_TAB_DEFERRED_REGISTER.register(eventBus);
        SOUND_EVENT_DEFERRED_REGISTER.register(eventBus);

        ArcaneDivinity.registerEverything();
    }
}