package de.abq.arcane_divinity.world.item;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;

public abstract class GeoSwordItem extends SwordItem implements GeoItem {
    public GeoSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }
}
