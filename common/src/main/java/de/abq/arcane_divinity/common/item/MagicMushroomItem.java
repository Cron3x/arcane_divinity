package de.abq.arcane_divinity.common.item;

import de.abq.arcane_divinity.common.effect.ZMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;

public class MagicMushroomItem extends Item {
    private final int diameter;

    public MagicMushroomItem(Properties properties, int diameter) {
        super(properties);
        this.diameter = diameter;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        ItemStack returnStack = super.finishUsingItem(stack, level, user);
        if (!level.isClientSide){
            int radius = (int) (this.diameter / 2); //TODO: make this more beautiful
            int rSquared = radius * radius;

            int grassCount = 0;

            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        if (x * x + y * y + z * z <= rSquared) {
                            BlockPos pos = user.getOnPos().offset(x, y, z);
                            if (level.getBlockState(pos).is(Blocks.CAMPFIRE)) {
                                CampfireBlockEntity campfireBlockEntity = (CampfireBlockEntity) level.getBlockEntity(pos);

                                for (ItemStack onFire : campfireBlockEntity.getItems()) {
                                    if (onFire.getItem().equals(ZItems.HALLUCINOGENIC_GRASS)) {
                                        grassCount++;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (grassCount < 4) return returnStack;

            user.addEffect(new MobEffectInstance(ZMobEffects.MAGIC_MUSHROOM_WARP_VISION, 50*20, grassCount));
        }
        //TODO: Implement wierd vision

        return returnStack;
    }
}
