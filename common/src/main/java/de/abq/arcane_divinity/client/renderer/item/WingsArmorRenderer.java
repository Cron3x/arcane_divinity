package de.abq.arcane_divinity.client.renderer.item;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.world.item.armor.WingsArmorItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.specialty.DyeableGeoArmorRenderer;
import software.bernie.geckolib.util.Color;


public class WingsArmorRenderer extends DyeableGeoArmorRenderer<WingsArmorItem> {
    public WingsArmorRenderer() {
        super(new DefaultedItemGeoModel<>(ResourceLocation.fromNamespaceAndPath(ArcaneDivinity.MOD_ID, "armor/wings"))); //new WingsArmorModel()
        //addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }


    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        System.out.printf("setupanim");
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
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
