package de.abq.arcane_divinity.common.effect;

import de.abq.arcane_divinity.ArcaneDivinity;
import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ZMobEffects {
    private static final Map<String, MobEffect> toRegister = new HashMap<>();

    public static final Holder<MobEffect> MAGIC_MUSHROOM_WARP_VISION_EFFECT = prepare("magic_mushroom_warp_vision_effect", new MagicMushroomMobEffect(MobEffectCategory.NEUTRAL, 0xffff00, ParticleTypes.DRAGON_BREATH));

    private static Holder<MobEffect> prepare(String name, MobEffect effect){
                toRegister.put(name, effect);
                return Holder.direct(effect);
    }

    public static void register(BiConsumer<MobEffect, ResourceLocation> consumer){
        toRegister.forEach((k, v) ->{
            consumer.accept(v, ArcaneDivinity.defaultResourceLocation(k));
        });
    }
}
