package de.abq.arcane_divinity.common.item.armor;

import de.abq.arcane_divinity.common.util.VelocityUtil;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentModel;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.constant.DefaultAnimations;
//import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.function.Consumer;


public final class WingsArmorItem extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private boolean isFlying = false;
    private boolean isFlyingForward = false;

    public static boolean isFlyEnabled(ItemStack $$0) {
        return true;
    }

    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        return isFlyEnabled(stack);
    }

    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        if (!entity.level().isClientSide) {
            int nextFlightTick = flightTicks + 1;
            if (nextFlightTick % 10 == 0) {
                entity.gameEvent(GameEvent.ELYTRA_GLIDE);
            }
        }
        else {
            int nextFlightTick = flightTicks + 1;
            if (nextFlightTick % 10 == 0) {
                if (entity instanceof LocalPlayer player) {
                    Vec3 acceleration = VelocityUtil.calcAcceleration(player);
                    isFlyingForward = (acceleration.x > 0 || acceleration.y > 0 || acceleration.z > 0);
                    player.setDeltaMovement(player.getDeltaMovement().add(acceleration));
                }
            }
        }
        return true;
    }

    public WingsArmorItem(Properties properties) {
        super(ZArmorMaterials.WINGS, ArmorType.CHESTPLATE, properties);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, 10, state -> {
            // Apply our generic idle animation.
            // Whether it plays or not is decided down below.

            Entity self = state.getData(DataTickets.ENTITY);

            if (self instanceof Player player) isFlying = player.isFallFlying();

            if (isFlying)
                if (isFlyingForward)
                    state.setAnimation(DefaultAnimations.FLY);
                else state.setAnimation(DefaultAnimations.ATTACK_BITE);
            else
                state.setAnimation(DefaultAnimations.IDLE);

            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private WingsArmorRenderer renderer;

            @Override
            public @Nullable <E extends LivingEntity, S extends HumanoidRenderState> HumanoidModel<?> getGeoArmorRenderer(@Nullable E livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, EquipmentModel.LayerType type, HumanoidModel<S> original) {
                //return GeoRenderProvider.super.getGeoArmorRenderer(livingEntity, itemStack, equipmentSlot, type, original);
                if (this.renderer == null)
                    this.renderer = new WingsArmorRenderer();
                // Defer creation of our renderer then cache it so that it doesn't get instantiated too early
                return this.renderer;
            }
        });
    }
}