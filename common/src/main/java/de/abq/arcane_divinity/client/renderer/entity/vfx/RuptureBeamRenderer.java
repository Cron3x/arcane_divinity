package de.abq.arcane_divinity.client.renderer.entity.vfx;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.world.entity.vfx.RuptureBeamEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RuptureBeamRenderer extends GeoEntityRenderer<RuptureBeamEntity> {
    public RuptureBeamRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(ArcaneDivinity.path("rupture_beam"), false));
    }
}
