package de.abq.arcane_divinity.common.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class KingfisherRenderer extends GeoEntityRenderer<KingfisherEntity> {
    public KingfisherRenderer(EntityRendererProvider.Context renderManager, GeoModel model) {
        super(renderManager, model);
    }
}
