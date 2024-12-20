package de.abq.arcane_divinity.common.effect;

import de.abq.arcane_divinity.ArcaneDivinity;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ZMobEffects {
    private static final Map<String, MobEffect> toRegister = new LinkedHashMap<>();

    public static Holder<MobEffect> MAGIC_MUSHROOM_WARP_VISION_EFFECT = prepare("warp_vision", new MagicMushroomMobEffect(MobEffectCategory.NEUTRAL, 0xffff00, ParticleTypes.WITCH));

    private static Holder<MobEffect> prepare(String name, MobEffect effect){
                toRegister.put(name, effect);
                return Holder.direct(effect);
    }

    public static void register(BiConsumer<MobEffect, ResourceLocation> consumer){
        toRegister.forEach((k, v) ->{
            consumer.accept(v, ArcaneDivinity.path(k));
        });
    }

    public static void reassignEffect(ResourceLocation rl, Holder<MobEffect> holder){
        if (rl.getPath().equals("warp_vision")) {
            MAGIC_MUSHROOM_WARP_VISION_EFFECT = holder;
        } else {
            ArcaneDivinity.LOG.error("resource Location: `{}` is not assigned!", rl);
        }
    }
}
