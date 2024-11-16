package de.abq.arcane_divinity.mixin;

import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class MixinPlayer {

    /*@Inject(
            method = "tryToStartFallFlying()Z",
            at = @At(
                    value = "INVOKE",
                    shift = At.Shift.BY,
                    by = 2,
                    target = "Lnet/minecraft/world/entity/player/Player;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"),
            cancellable = true)*/
    @Inject(method = "tryToStartFallFlying()Z", at = @At(value = "HEAD"), cancellable = true)
    protected void arcane_divinity$tryToFly(CallbackInfoReturnable<Boolean> cir) {
        System.out.println("injected (Player)");
        Player self = (Player) (Object) this;
        cir.setReturnValue(true);

        System.out.println(self);
        /*if (itemstack.getItem() == ZItems.WINGS || itemstack.getItem() == Items.ELYTRA) cir.cancel();

        System.out.println("HI (Player)");

        Player self = (Player) (Object) this;

        System.out.println("unreachable? (Player)");

        ICuriosItemHandler curiosInventory = CuriosApi.getCuriosInventory(self).get();
        Map<String, ICurioStacksHandler> curios = curiosInventory.getCurios();
        ICurioStacksHandler slotInventory = curios.get("wings");
        itemstack = slotInventory.getStacks().getStackInSlot(0);
        System.out.println(itemstack);
        cir.setReturnValue(true);*/
    }
}