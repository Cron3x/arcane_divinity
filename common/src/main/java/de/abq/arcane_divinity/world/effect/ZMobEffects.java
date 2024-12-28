package de.abq.arcane_divinity.world.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

//TODO: FIX REGISTRATION
public class ZMobEffects {
    public static Holder<MobEffect> MAGIC_MUSHROOM_WARP_VISION_EFFECT = prepare("warp_vision", new MagicMushroomMobEffect(MobEffectCategory.NEUTRAL, 0xffff00, ParticleTypes.WITCH));

    private static Holder<MobEffect> prepare(String name, MobEffect effect){
                return Holder.direct(effect);
    }
}
