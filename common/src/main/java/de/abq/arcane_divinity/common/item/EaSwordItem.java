package de.abq.arcane_divinity.common.item;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.common.defaulted.renderer.DefaultedItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class EaSwordItem extends SwordItem implements GeoItem {
    public static final String IDENTIFIER = "ea_sword";

    private static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("idle");
    private static final RawAnimation USE_ANIM = RawAnimation.begin().thenPlayXTimes("idle",10);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private boolean triggeredUse = false;

    public EaSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers
                .add(new AnimationController<>(this, "Idle", 0, this::idleAnimationController))
                .add(new AnimationController<>(this, "Use", 0, state -> PlayState.STOP)
                        .triggerableAnim("use", USE_ANIM).setAnimationSpeed(100f));
    }

    protected <S extends EaSwordItem> PlayState idleAnimationController(final AnimationState<S> event){
        if (triggeredUse){
            event.setControllerSpeed(1.9f);
            triggeredUse = false;
        }
        return event.setAndContinue(IDLE_ANIM);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept( new GeoRenderProvider() {
                             private DefaultedItemRenderer<EaSwordItem> renderer;

                             @Override
                             public @NotNull BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                                 if (this.renderer == null)
                                     this.renderer = new DefaultedItemRenderer<>(IDENTIFIER);
                                 return this.renderer;
                             }
                         }
        );
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {

        if (level instanceof ServerLevel serverLevel)
            triggerAnim(player, GeoItem.getOrAssignId(player.getItemInHand(usedHand), serverLevel), "Use", "use");
        return super.use(level, player, usedHand);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        ArcaneDivinity.LOG.debug("hurtEnemy");
        if (attacker.level() instanceof ServerLevel serverLevel)
            triggerAnim(attacker, GeoItem.getOrAssignId(attacker.getItemInHand(InteractionHand.MAIN_HAND), serverLevel), "Use", "use");
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        ArcaneDivinity.LOG.debug("postHurtEnemy");
        if (attacker.level() instanceof ServerLevel serverLevel)
            triggerAnim(attacker, GeoItem.getOrAssignId(attacker.getItemInHand(InteractionHand.MAIN_HAND), serverLevel), "Use", "use");
        super.postHurtEnemy(stack, target, attacker);
    }
}
