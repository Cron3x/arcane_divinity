package de.abq.arcane_divinity.common.defaulted.renderer;

import de.abq.arcane_divinity.ArcaneDivinity;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DefaultedItemRenderer<T extends Item & GeoAnimatable> extends GeoItemRenderer<T> {
    public DefaultedItemRenderer(String id) {
        super(new DefaultedItemGeoModel<>(ArcaneDivinity.path(id)));
    }
}
