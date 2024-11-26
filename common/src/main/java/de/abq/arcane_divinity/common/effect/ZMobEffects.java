package de.abq.arcane_divinity.common.effect;

import com.mojang.serialization.MapCodec;
import de.abq.arcane_divinity.ArcaneDivinityCommon;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ZMobEffects {
    public static final Holder<MobEffect> MAGIC_MUSHROOM_WARP_VISION =  register("magic_mushroom_warp", new MagicMushroomMobEffect(MobEffectCategory.NEUTRAL, 0xffff00, ParticleTypes.DRAGON_BREATH));

    private static Holder<MobEffect> register(String id, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, ArcaneDivinityCommon.defaultResourceLocation(id), effect);
    }
}
