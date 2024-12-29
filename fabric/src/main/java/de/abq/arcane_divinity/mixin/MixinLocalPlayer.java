package de.abq.arcane_divinity.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import de.abq.arcane_divinity.util.SharedMixins;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class MixinLocalPlayer {
    @Inject(method = "aiStep()V", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/player/LocalPlayer;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;",
            shift = At.Shift.BY,
            by = 2
    ))
    protected void arcane_divinity$isFlyableArmor(CallbackInfo ci, @Local ItemStack itemStack) {
        SharedMixins.isFlyableArmor((LocalPlayer) (Object) this, itemStack);
    }

    @ModifyArg(
            method = "aiStep()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z")
    )
    protected Item arcane_divinity$onIsElytraCheck(Item item, @Local ItemStack itemStack) {
        return SharedMixins.fixElytraItemCheck(item, itemStack);
    }
}