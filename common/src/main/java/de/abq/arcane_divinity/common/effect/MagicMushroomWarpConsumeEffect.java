package de.abq.arcane_divinity.common.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.abq.arcane_divinity.ArcaneDivinityCommon;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public record MagicMushroomWarpConsumeEffect(float diameter) implements ConsumeEffect {
    private static final float DEFAULT_DIAMETER = 8.0F;

    public MagicMushroomWarpConsumeEffect(){
        this(DEFAULT_DIAMETER);
    }

    public static final MapCodec<MagicMushroomWarpConsumeEffect> CODEC = RecordCodecBuilder.mapCodec(
            p_366612_ -> p_366612_.group(ExtraCodecs.POSITIVE_FLOAT.optionalFieldOf("diameter", DEFAULT_DIAMETER).forGetter(MagicMushroomWarpConsumeEffect::diameter))
                    .apply(p_366612_, MagicMushroomWarpConsumeEffect::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, MagicMushroomWarpConsumeEffect> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, MagicMushroomWarpConsumeEffect::diameter, MagicMushroomWarpConsumeEffect::new
    );

    public ConsumeEffect.Type<MagicMushroomWarpConsumeEffect> getType() {
        return ZConsumableEffects.MAGIC_MUSHROOM_WARP;
    }

    @Override
    public boolean apply(Level level, ItemStack stack, LivingEntity user) {
        int radius = (int) (this.diameter / 2); //TODO: make this more beautiful
        int rSquared = radius * radius;

        boolean isMagic = false;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x * x + y * y + z * z <= rSquared) {
                        BlockPos pos = user.getOnPos().offset(x,y,z);
                        if (level.getBlockState(pos).is(Blocks.CAMPFIRE)) {
                            CampfireBlockEntity campfireBlockEntity = (CampfireBlockEntity) level.getBlockEntity(pos);


                            for (ItemStack onFire : campfireBlockEntity.getItems()) {
                                if (onFire.getItem().equals(Items.PORKCHOP)) {
                                    isMagic = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!isMagic) return isMagic;

        user.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 5));

        return isMagic;
    }
}
