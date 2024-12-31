package de.abq.arcane_divinity.client.renderer.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import de.abq.arcane_divinity.client.model.block.entity.ArcaneShrineBlockEntityModel;
import de.abq.arcane_divinity.config.Config;
import de.abq.arcane_divinity.world.level.block.entity.AbstractGeoBlockEntity;
import de.abq.arcane_divinity.world.level.block.entity.ArcaneShrineBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ArcaneShrineBlockEntityRenderer<T extends AbstractGeoBlockEntity> extends GeoBlockRenderer<ArcaneShrineBlockEntity> {
    private float angle = 0f;
    private final BlockEntityRendererProvider.Context context;

    public ArcaneShrineBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new ArcaneShrineBlockEntityModel());
        this.context = context;
    }

    @Override
    public void render(ArcaneShrineBlockEntity animatable, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        super.render(animatable, partialTick, poseStack, bufferSource, packedLight, packedOverlay);

        if (Minecraft.getInstance().isPaused()) return;
        if (angle >= 2*Math.PI) angle = 0;
        if (animatable.isRemoved()) return;

        final ItemStack item = animatable.getDisplayItem();


        poseStack.translate(0.5f, 1.15f, 0.5f);
        poseStack.scale(1,1,1);
        poseStack.mulPose(new Quaternionf(0, 0, 0, 1).rotateAxis(angle, 0, 0.8f, 0));
        angle += Config.PEDESTAL_SPEED;
        poseStack.pushPose();

        context.getItemRenderer().renderStatic(item, ItemDisplayContext.GROUND, packedLight, packedOverlay, poseStack, bufferSource, animatable.getLevel(), 1);
        poseStack.popPose();
    }
}
