package de.abq.arcane_divinity.common.util;

import de.abq.arcane_divinity.config.CONFIG;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.Vec3;

public class VelocityUtil {
    public static Vec3 calcAcceleration(LocalPlayer player ) {
        Vec3 velocity = player.getDeltaMovement();
        Vec3 look = player.getLookAngle();

        double slowingFactor = player.isSprinting() ? CONFIG.ELYTRA__SPRINT_MULTIPLIER : CONFIG.ELYTRA__NON_SPRINT_MULTIPLIER;
        double forImpulse = player.input.forwardImpulse;
        double parallelSpeed = look.dot(velocity);
        double forSpeed = Math.max(CONFIG.ELYTRA__MIN_SPEED, forImpulse * (CONFIG.ELYTRA__MAX_SPEED - parallelSpeed) * slowingFactor);

        return look.scale(forSpeed);
    }
}
