package de.abq.arcane_divinity.common.effect;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.client.WarpingRenderer;
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

        ArcaneDivinity.LOG.debug("asdf-asdfsödfjölaskjföalsdfjöasldfjöalsdjflösajfdlsajdf");

        if (livingEntity.level().isClientSide) {
            ArcaneDivinity.LOG.debug("qwer-asdfsödfjölaskjföalsdfjöasldfjöalsdjflösajfdlsajdf");
            WarpingRenderer.render();
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }


}
