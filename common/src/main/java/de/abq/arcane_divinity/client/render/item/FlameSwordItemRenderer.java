package de.abq.arcane_divinity.client.render.item;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.world.item.FlameSwordItem;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.shader.program.ShaderProgram;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class FlameSwordItemRenderer extends GeoItemRenderer<FlameSwordItem> {
    private static final ResourceLocation SHADER_ID = ArcaneDivinity.path( "flame_blade");

    public FlameSwordItemRenderer(GeoModel<FlameSwordItem> model) {
        super(model);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext transformType, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        super.renderByItem(stack, transformType, poseStack, bufferSource, packedLight, packedOverlay);

        ShaderProgram shader = VeilRenderSystem.setShader(SHADER_ID);
        if (shader == null) return;

        shader.bind();
        shader.setVector("bladeColor", new Vector3f(0,100,255));
        shader.setFloat("glowIntensity", 1f);

        // rendering code here
        poseStack.pushPose();
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.translate(-0.5, -0.5, -0.5);

        Matrix4f pose = poseStack.last().pose();
        BufferBuilder builder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP);
        builder.addVertex(pose, 0, 0, 0.125F).setColor(1.0F, 1.0F, 1.0F, 1.0F).setUv(1.0F, 0.0F).setLight(1);
        builder.addVertex(pose, 12, 0, 0.125F).setColor(1.0F, 1.0F, 1.0F, 1.0F).setUv(0.0F, 0.0F).setLight(1);
        builder.addVertex(pose, 12, 12, 0.125F).setColor(1.0F, 1.0F, 1.0F, 1.0F).setUv(0.0F, 1.0F).setLight(1);
        builder.addVertex(pose, 0, 12, 0.125F).setColor(1.0F, 1.0F, 1.0F, 1.0F).setUv(1.0F, 1.0F).setLight(1);

        VeilRenderSystem.drawScreenQuad();

        poseStack.popPose();
        ShaderProgram.unbind();
    }



}
