package de.abq.arcane_divinity.util;

import net.minecraft.core.Vec3i;
import org.joml.Vector3f;

public final class SimpleVec {
    public static Vec3i vec3i(int x, int y, int z){
        return new Vec3i(x,y,z);
    }

    public static Vector3f vec3f(float x, float y, float z){
        return new Vector3f(x,y,z);
    }
}
