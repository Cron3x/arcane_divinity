package de.abq.arcane_divinity.client.renderer.entity.vfx;

import com.mojang.blaze3d.vertex.PoseStack;
import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.world.entity.vfx.RuptureBeamEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.joml.Vector3f;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RuptureBeamRenderer extends GeoEntityRenderer<RuptureBeamEntity> {
    public RuptureBeamRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(ArcaneDivinity.path("rupture_beam"), false));
    }

    @Override
    public void render(RuptureBeamEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

        Vector3f scale = entity.getSize();
        ArcaneDivinity.LOG.debug("scale: {}", scale);
        poseStack.scale(scale.x, scale.y, scale.z);
        poseStack.pushPose();
        poseStack.popPose();
    }
}
