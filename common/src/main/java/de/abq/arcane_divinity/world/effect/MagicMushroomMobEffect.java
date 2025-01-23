package de.abq.arcane_divinity.world.effect;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class MagicMushroomMobEffect extends MobEffect {
    public MagicMushroomMobEffect(MobEffectCategory category, int color, ParticleOptions particle) {
        super(category, color, particle);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier){
        super.onEffectAdded(livingEntity, amplifier);

        if (livingEntity.level().isClientSide && livingEntity instanceof Player player) {

            //WarpingRenderer.render(levelRenderer, bufferSource, matrixStack, matrix4fc, matrix4fc2, partialTicks, deltaTracker, camera, frustum);
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }


}
