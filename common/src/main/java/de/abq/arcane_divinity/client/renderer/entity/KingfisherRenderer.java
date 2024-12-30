package de.abq.arcane_divinity.client.renderer.entity;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.common.entity.KingfisherEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class KingfisherRenderer extends GeoEntityRenderer<KingfisherEntity> {
    public KingfisherRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(ArcaneDivinity.path(KingfisherEntity.KEY)));
    }
}
