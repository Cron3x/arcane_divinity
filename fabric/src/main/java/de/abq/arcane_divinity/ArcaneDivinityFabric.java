package de.abq.arcane_divinity;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.world.InteractionResult;

public class ArcaneDivinityFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        ArcaneDivinity.registerEverything();

        AttackEntityCallback.EVENT.register((player, level, interactionHand, entity, hitResult) -> {
            player.teleportRelative(0,5,0);
            return InteractionResult.PASS;
        });
    }
}