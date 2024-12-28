package de.abq.arcane_divinity.world.effect;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.platform.Services;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

//TODO: FIX REGISTRATION
public class ZMobEffects {
    public static Holder<MobEffect> MAGIC_MUSHROOM_WARP_VISION_EFFECT = prepare("warp_vision", new MagicMushroomMobEffect(MobEffectCategory.NEUTRAL, 0xffff00, ParticleTypes.WITCH));

    private static Holder<MobEffect> prepare(String name, MobEffect effect){
                return Holder.direct(effect);
    }
}
