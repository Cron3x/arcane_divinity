package de.abq.arcane_divinity.common.item;

import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DefaultItemRenderer<T extends Item & GeoAnimatable> extends GeoItemRenderer<T> {
    public DefaultItemRenderer(GeoModel<T> model) {
        super(model);
    }
}
