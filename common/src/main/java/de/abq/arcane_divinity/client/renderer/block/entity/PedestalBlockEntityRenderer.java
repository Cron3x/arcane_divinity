package de.abq.arcane_divinity.client.renderer.block.entity;


import com.mojang.blaze3d.vertex.PoseStack;
import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.config.Config;
import de.abq.arcane_divinity.world.item.GeoSwordItem;
import de.abq.arcane_divinity.world.level.block.entity.PedestalBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.*;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;

public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity> {
    private float angle = 0f;
    private final BlockEntityRendererProvider.Context context;
    public PedestalBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public boolean shouldRender(PedestalBlockEntity blockEntity, Vec3 cameraPos) {
        return BlockEntityRenderer.super.shouldRender(blockEntity, cameraPos) && !Minecraft.getInstance().isPaused(); //TODO: test
    }

    //TODO: implement more performant. only reassign item and shouldAnimate when block updated
    @Override
    public void render(PedestalBlockEntity pedestal, float partialTicks, PoseStack poseStack, MultiBufferSource buffers, int light, int overlay) {
        if (Minecraft.getInstance().isPaused()) return;
        if (angle >= 2*Math.PI) angle = 0;
        if (pedestal.isRemoved()) return;

        final ItemStack item = pedestal.getDisplayItem();
        final boolean shouldAnimate = pedestal.isShouldAnimate();
        final boolean isBlockItem = item.getItem() instanceof BlockItem;

        final float tZ = isBlockItem || item.getItem() instanceof GeoSwordItem || shouldAnimate ? 0.5f: 0.39f;
        final float tY = isBlockItem ? 0.945f : shouldAnimate ? 1.15f : 1f;

        poseStack.translate(0.5f, tY, tZ);
        poseStack.scale(1,1,1);
        if (!shouldAnimate && !isBlockItem) poseStack.mulPose(new Quaternionf(0, 0, 0, 1).rotateX((float) (Math.PI / 2f)));


        if (shouldAnimate) {
            poseStack.mulPose(new Quaternionf(0, 0, 0, 1).rotateAxis(angle, 0, 0.8f, 0));
            angle += Config.PEDESTAL_SPEED;
        }
        poseStack.pushPose();

        context.getItemRenderer().renderStatic(item, ItemDisplayContext.GROUND, light, overlay, poseStack, buffers, pedestal.getLevel(), 1);
        poseStack.popPose();
    }
}
