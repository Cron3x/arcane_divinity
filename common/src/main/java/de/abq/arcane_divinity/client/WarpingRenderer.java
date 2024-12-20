package de.abq.arcane_divinity.client;

import com.mojang.blaze3d.systems.RenderSystem;
import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.common.effect.ZMobEffects;
import foundry.veil.api.client.render.MatrixStack;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.shader.program.ShaderProgram;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4fStack;
import org.joml.Matrix4fc;

public class WarpingRenderer {

    private static final ResourceLocation CUSTOM_SHADER = ArcaneDivinity.path("high");

    public static void render(LevelRenderer levelRenderer, MultiBufferSource.BufferSource bufferSource, MatrixStack matrixStack, Matrix4fc matrix4fc, Matrix4fc matrix4fc2, int partialTicks, DeltaTracker deltaTracker, Camera camera, Frustum frustum) {
        if (!Minecraft.getInstance().player.hasEffect(ZMobEffects.MAGIC_MUSHROOM_WARP_VISION_EFFECT)) return;

        ShaderProgram shader = VeilRenderSystem.setShader(CUSTOM_SHADER);
        if (shader == null) return;
        shader.applyRenderSystem();
        shader.setup();

        //shader.setFloat("CustomValue", 37.2F);
        //shader.setMatrix("CustomProjection", new Matrix4f().ortho(0, 10, 10, 0, 0.3F, 100.0F, false));

        shader.bind();

        Matrix4fStack modelViewStack = RenderSystem.getModelViewStack();
        modelViewStack.pushMatrix();
        modelViewStack.mul(matrix4fc);

        modelViewStack.popMatrix();

        // rendering code here
        ShaderProgram.unbind();

        ArcaneDivinity.LOG.debug("I should be rendered");
    }
}
