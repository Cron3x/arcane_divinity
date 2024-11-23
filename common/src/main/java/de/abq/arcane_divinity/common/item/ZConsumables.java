package de.abq.arcane_divinity.common.item;

import de.abq.arcane_divinity.common.effect.MagicMushroomWarpConsumeEffect;
import net.minecraft.sounds.SoundEvents;

public class ZConsumables {
    public static final Consumable MAGIC_MUSHROOM = defaultConsumable().onConsume(
            new MagicMushroomWarpConsumeEffect()
    ).build();

    private static Consumable.Builder defaultConsumable(){
        return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.DRINK).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true);
    }
}