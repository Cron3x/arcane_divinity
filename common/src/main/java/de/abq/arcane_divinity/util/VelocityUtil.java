package de.abq.arcane_divinity.util;

import de.abq.arcane_divinity.config.Config;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.Vec3;

public class VelocityUtil {
    public static Vec3 calcAcceleration(LocalPlayer player ) {
        Vec3 velocity = player.getDeltaMovement();
        Vec3 look = player.getLookAngle();

        double slowingFactor = player.isSprinting() ? Config.WING.SPRINT_MULTIPLIER : Config.WING.NON_SPRINT_MULTIPLIER;
        double forImpulse = player.input.forwardImpulse;
        double parallelSpeed = look.dot(velocity);
        double forSpeed = Math.max(Config.WING.MIN_SPEED, forImpulse * (Config.WING.MAX_SPEED - parallelSpeed) * slowingFactor);

        return look.scale(forSpeed);
    }
}
