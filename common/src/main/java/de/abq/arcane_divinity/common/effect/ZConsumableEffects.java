package de.abq.arcane_divinity.common.effect;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.consume_effects.ConsumeEffect;

public class ZConsumableEffects {
    public static final ConsumeEffect.Type<MagicMushroomWarpConsumeEffect> MAGIC_MUSHROOM_WARP = register(
            "magic_mushroom_warp", MagicMushroomWarpConsumeEffect.CODEC, MagicMushroomWarpConsumeEffect.STREAM_CODEC
    );

    private static <T extends ConsumeEffect> ConsumeEffect.Type<T> register(String id, MapCodec<T> codec, StreamCodec<RegistryFriendlyByteBuf, T> streamCodec) {
        return Registry.register(BuiltInRegistries.CONSUME_EFFECT_TYPE, id, new ConsumeEffect.Type<>(codec, streamCodec));
    }
}
