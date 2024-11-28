package de.abq.arcane_divinity.client;

import de.abq.arcane_divinity.ArcaneDivinity;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.shader.program.ShaderProgram;
import net.minecraft.resources.ResourceLocation;

public class WarpingRenderer {

    private static final ResourceLocation CUSTOM_SHADER = ArcaneDivinity.defaultResourceLocation("high");

    public static void render() {
        ShaderProgram shader = VeilRenderSystem.setShader(CUSTOM_SHADER);

        if (shader == null) return;
        //shader.setFloat("CustomValue", 37.2F);
        //shader.setMatrix("CustomProjection", new Matrix4f().ortho(0, 10, 10, 0, 0.3F, 100.0F, false));

        shader.bind();
        // rendering code here
        ShaderProgram.unbind();
    }
}
