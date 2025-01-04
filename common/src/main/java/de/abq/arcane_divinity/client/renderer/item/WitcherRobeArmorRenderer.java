package de.abq.arcane_divinity.client.renderer.item;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.world.item.armor.WingsArmorItem;
import de.abq.arcane_divinity.world.item.armor.WitcherRobeItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.specialty.DyeableGeoArmorRenderer;
import software.bernie.geckolib.util.Color;


public class WitcherRobeArmorRenderer extends DyeableGeoArmorRenderer<WitcherRobeItem> {
    public WitcherRobeArmorRenderer() {
        super(new DefaultedItemGeoModel<>(ResourceLocation.fromNamespaceAndPath(ArcaneDivinity.MOD_ID, "armor/witcher_robe")));
    }

    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

    @Override
    protected boolean isBoneDyeable(GeoBone geoBone) {
        return geoBone.getName().contains("dye");
    }

    @Override
    protected @NotNull Color getColorForBone(GeoBone geoBone) {
        if(geoBone.getName().contains("dye_primary"))
            return this.getAnimatable().getPrimaryColor(currentStack);
        return new Color(0xffffffff);
    }
}
