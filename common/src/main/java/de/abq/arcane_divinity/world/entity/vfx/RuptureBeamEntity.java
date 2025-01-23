package de.abq.arcane_divinity.world.entity.vfx;

import de.abq.arcane_divinity.util.SimpleVec;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.joml.Vector3f;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import static de.abq.arcane_divinity.util.SimpleVec.vec3f;

public class RuptureBeamEntity extends Entity implements GeoAnimatable, GeoEntity {
    protected static final RawAnimation ATTACK_ANIMATION = RawAnimation.begin().thenLoop("attack");
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    private static final EntityDataAccessor<Vector3f> DATA_SCALE_ID = SynchedEntityData.defineId(RuptureBeamEntity.class, EntityDataSerializers.VECTOR3);

    public RuptureBeamEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "ATTACKING", 5, this::attackController));
    }

    private <E extends RuptureBeamEntity> PlayState attackController(final AnimationState<E> event){
        if (event.isMoving()){
            return event.setAndContinue(ATTACK_ANIMATION);
        }

        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_SCALE_ID, SimpleVec.vec3f(0.2f,0.2f,0.2f));
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        this.setSize(vec3f(
                compoundTag.getFloat("height"),
                compoundTag.getFloat("length"),
                compoundTag.getFloat("width"))
        );
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        Vector3f s = getSize();
        compoundTag.putFloat("height", s.y);
        compoundTag.putFloat("length", s.x);
        compoundTag.putFloat("width",  s.z);
    }

    public void setSize(Vector3f scale){
        this.entityData.set(DATA_SCALE_ID, scale);
    }
    public Vector3f getSize(){
        return this.entityData.get(DATA_SCALE_ID);
    }

    public void changeHitBoxAndRotation(float xRot, float yRot){

       this.setXRot(xRot);
       this.setYRot(yRot);

        double originalWidth = this.getDimensions(this.getPose()).width();
        double originalDepth = this.getDimensions(this.getPose()).width(); // Assuming width = depth for simplicity

        // Calculate rotated width and depth using trigonometry
        double rotatedWidth = Math.abs(originalWidth * Math.cos(yRot)) + Math.abs(originalDepth * Math.sin(yRot));
        double rotatedDepth = Math.abs(originalWidth * Math.sin(xRot)) + Math.abs(originalDepth * Math.cos(getXRot()));

        this.setBoundingBox(new AABB(
                this.getX() - rotatedWidth / 2,
                this.getY(),
                this.getZ() - rotatedDepth / 2,
                this.getX() + rotatedWidth / 2,
                this.getY() + this.getDimensions(this.getPose()).height(),
                this.getZ() + rotatedDepth / 2
        ));
    }

    @Override
    public void tick() {
        super.tick();

        level().getEntities(null, getBoundingBox()).forEach(entity -> {
            entity.hurt(new DamageSource((Holder<DamageType>) DamageTypes.MAGIC),1000f);
        });

    }
}
