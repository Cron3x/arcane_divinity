package de.abq.arcane_divinity.common.effect;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class MagicMushroomMobEffect extends MobEffect {
    protected MagicMushroomMobEffect(MobEffectCategory category, int color, ParticleOptions particle) {
        super(category, color, particle);
    }

    @Override
    public void onEffectAdded(LivingEntity livingEntity, int amplifier) {
        super.onEffectAdded(livingEntity, amplifier);

        if (Minecraft.getInstance().level == null || !Minecraft.getInstance().level.isClientSide) return;


    }
}
