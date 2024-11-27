package de.abq.arcane_divinity.mixin;

import de.abq.arcane_divinity.ArcaneDivinity;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    
    @Inject(at = @At("TAIL"), method = "<init>")
    private void init(CallbackInfo info) {
        
        ArcaneDivinity.LOG.info("This line is printed by an example mod common mixin!");
        ArcaneDivinity.LOG.info("MC Version: {}", Minecraft.getInstance().getVersionType());
    }
}