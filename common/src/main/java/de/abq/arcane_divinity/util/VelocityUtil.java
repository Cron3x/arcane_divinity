package de.abq.arcane_divinity.util;

import de.abq.arcane_divinity.config.CONFIG;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.Vec3;

public class VelocityUtil {
    public static Vec3 calcAcceleration(LocalPlayer player ) {
        Vec3 velocity = player.getDeltaMovement();
        Vec3 look = player.getLookAngle();

        double slowingFactor = player.isSprinting() ? CONFIG.ELYTRA.SPRINT_MULTIPLIER : CONFIG.ELYTRA.NON_SPRINT_MULTIPLIER;
        double forImpulse = player.input.forwardImpulse;
        double parallelSpeed = look.dot(velocity);
        double forSpeed = Math.max(CONFIG.ELYTRA.MIN_SPEED, forImpulse * (CONFIG.ELYTRA.MAX_SPEED - parallelSpeed) * slowingFactor);

        return look.scale(forSpeed);
    }
}
