package de.abq.arcane_divinity.client.item.render;

import com.mojang.blaze3d.vertex.PoseStack;
import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.common.item.FlameSwordItem;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.VeilRenderer;
import foundry.veil.api.client.render.shader.definition.ShaderPreDefinitions;
import foundry.veil.api.client.render.shader.program.ShaderProgram;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4f;
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
        if (shader == null) {
            return;
        }
        shader.bind();
        // rendering code here

        ShaderProgram.unbind();
    }
}
