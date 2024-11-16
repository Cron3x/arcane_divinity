package de.abq.arcane_divinity.common.item.armor;

import de.abq.arcane_divinity.ArcaneDivinity;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.object.Color;
import software.bernie.geckolib.renderer.specialty.DyeableGeoArmorRenderer;


public class WingsArmorRenderer extends DyeableGeoArmorRenderer<WingsArmorItem> {
    public WingsArmorRenderer() {
        super(new DefaultedItemGeoModel<>(ResourceLocation.fromNamespaceAndPath(ArcaneDivinity.MOD_ID, "armor/wings"))); //new WingsArmorModel()
        //addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public void setupAnim(HumanoidRenderState p_361833_) {
        System.out.printf("setupanim");
        super.setupAnim(p_361833_);
    }

    @Override
    protected boolean isBoneDyeable(GeoBone geoBone) {
        return true;
    }

    @Override
    protected @NotNull Color getColorForBone(GeoBone geoBone) {
        return Color.WHITE;
    }
}
