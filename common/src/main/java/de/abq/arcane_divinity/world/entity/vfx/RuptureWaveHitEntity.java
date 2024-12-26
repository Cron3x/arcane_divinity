package de.abq.arcane_divinity.world.entity.vfx;

import de.abq.arcane_divinity.world.entity.ZEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class RuptureWaveHitEntity extends Entity implements GeoAnimatable, GeoEntity {
    protected static final RawAnimation HIT_ANIMATION = RawAnimation.begin().thenLoop("test");
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public RuptureWaveHitEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }
    public RuptureWaveHitEntity( Level level,double x, double y, double z) {
        super(ZEntityType.RUPTURE_WAVE_HIT, level);
        this.setPos(x, y, z);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Hitting", 5, this::hitAnimationController));
    }

    private <E extends RuptureWaveHitEntity> PlayState hitAnimationController(final AnimationState<E> event){
        return event.setAndContinue(HIT_ANIMATION);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    public void tick() {
        super.tick();
    }
}
