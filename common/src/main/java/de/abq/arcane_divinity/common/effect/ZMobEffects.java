package de.abq.arcane_divinity.common.effect;

import de.abq.arcane_divinity.ArcaneDivinity;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ZMobEffects {
    public static final Holder<MobEffect> MAGIC_MUSHROOM_WARP_VISION =  register("magic_mushroom_warp", new MagicMushroomMobEffect(MobEffectCategory.NEUTRAL, 0xffff00, ParticleTypes.DRAGON_BREATH));

    private static Holder<MobEffect> register(String id, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, ArcaneDivinity.defaultResourceLocation(id), effect);
    }
}
