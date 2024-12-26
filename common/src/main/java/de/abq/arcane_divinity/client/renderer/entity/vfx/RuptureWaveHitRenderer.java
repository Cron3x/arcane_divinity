package de.abq.arcane_divinity.client.renderer.entity.vfx;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.world.entity.vfx.RuptureBeamEntity;
import de.abq.arcane_divinity.world.entity.vfx.RuptureWaveHitEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.specialty.DynamicGeoEntityRenderer;

public class RuptureWaveHitRenderer extends DynamicGeoEntityRenderer<RuptureWaveHitEntity> {
    public RuptureWaveHitRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(ArcaneDivinity.path("rupture_hit_particle"), false));
    }
}
