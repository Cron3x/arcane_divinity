package de.abq.arcane_divinity.world.item.armor;

import de.abq.arcane_divinity.client.renderer.item.WingsArmorRenderer;
import de.abq.arcane_divinity.client.renderer.item.WitcherRobeArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.DyedItemColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.Color;

import java.util.function.Consumer;

public class WitcherRobeItem extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public WitcherRobeItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    public WitcherRobeItem(Properties properties, ArmorItem.Type type) {
        super(ArmorMaterials.TURTLE, type, properties);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }


    public @NotNull Color getPrimaryColor(ItemStack stack) {
        DyeColor color = stack.get(DataComponents.BASE_COLOR);
        if (color == null) color = DyeColor.BLACK;
        return new Color(0xff000000);
    }

    public @NotNull Color getSecondaryColor(ItemStack stack) {
        DyedItemColor color = stack.get(DataComponents.DYED_COLOR);
        if (color == null) color = new DyedItemColor(0xffffff, true);
        return new Color(0xff000000+color.rgb());
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private WitcherRobeArmorRenderer renderer;

            @Override
            public @Nullable <T extends LivingEntity> HumanoidModel<?> getGeoArmorRenderer(@Nullable T livingEntity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, @Nullable HumanoidModel<T> original) {
                if (this.renderer == null)
                    this.renderer = new WitcherRobeArmorRenderer();
                // Defer creation of our renderer then cache it so that it doesn't get instantiated too early
                return renderer;
            }
        });
    }
}
