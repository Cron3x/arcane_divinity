package de.abq.arcane_divinity.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import de.abq.arcane_divinity.common.util.SharedMixins;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;


//** UNUSED **//
@Mixin(Player.class)
public abstract class MixinPlayer {
    @ModifyArg(
            method = "tryToStartFallFlying",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z")
    )
    protected Item arcane_divinity$onIsElytraCheck(Item item, @Local ItemStack itemStack) {
        return SharedMixins.fixElytraItemCheck(item, itemStack);
    }
}