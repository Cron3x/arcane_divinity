package de.abq.arcane_divinity.common.block.block_entity;

import de.abq.arcane_divinity.ArcaneDivinity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ArcaneShrineBlockEntity extends SimpleInventoryGeoBlockEntity implements GeoBlockEntity {

    protected static final RawAnimation ACTIVATE = RawAnimation.begin().thenPlay("animation.arcane_shrine.misc.activate").thenLoop("animation.arcane_shrine.misc.active_idle");
    protected static final RawAnimation DEACTIVATE = RawAnimation.begin().thenPlay("animation.arcane_shrine.misc.deactivate");
    protected static final RawAnimation ACTIVE_IDLE = RawAnimation.begin().thenLoop("animation.arcane_shrine.misc.active_idle");

    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);

    private int ticks = 0;
    private boolean shouldBeActive = false;
    private boolean isActive = false;
    public boolean isDay = false;

    public ArcaneShrineBlockEntity(BlockPos pos, BlockState state) {
        super(ZBlockEntities.ARCANE_ALTAR_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected SimpleContainer createItemHandler() {
        return new SimpleContainer(2) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        };
    }

    public static void clientTick(Level level, BlockPos blockPos, BlockState blockState, ArcaneShrineBlockEntity self) {}
    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, ArcaneShrineBlockEntity self) {
        ++self.ticks;

        if (self.shouldBeActive && !self.isActive){
            self.particleAnimationActivation((ServerLevel) level, blockPos);
        } else if (!self.shouldBeActive && self.isActive) {
            //TODO: Deactivation Animation
            System.out.println("//TODO: Deactivation Animation");
        }
    }

    private void particleAnimationActivation(ServerLevel level, BlockPos blockPos){
        double radius = 1.25;

        if (ticks <= 100){
            double x = radius * Math.cos(ticks);
            double z = radius * Math.sin(ticks);

            double fx = radius * Math.cos(ticks+1);
            double fz = radius * Math.sin(ticks+1);

            level.sendParticles(ParticleTypes.WAX_OFF, blockPos.getX()+0.5+x, blockPos.getY() + 1, blockPos.getZ()+0.5+z, 1, 0, 0, 0, 0);
            level.sendParticles(ParticleTypes.ENCHANT, blockPos.getX()+0.5+fx, blockPos.getY() + 1, blockPos.getZ()+0.5+fz, 1, 0, 0, 0, 20);
        }
        if (ticks >= 100 && ticks <= 230){
            animateToPillar(level, blockPos.getCenter(), blockPos.offset( 3, 3, 3).getCenter());
            animateToPillar(level, blockPos.getCenter(), blockPos.offset(-3, 3, 3).getCenter());
            animateToPillar(level, blockPos.getCenter(), blockPos.offset( 3, 3,-3).getCenter());
            animateToPillar(level, blockPos.getCenter(), blockPos.offset(-3, 3,-3).getCenter());
        }
        if (ticks > 230){
            level.sendParticles(ParticleTypes.FLASH, blockPos.getX()+.5, blockPos.getY()+1.5,blockPos.getZ()+.5, 1,0,0,0,0);
            ticks = 0;
            isActive = true;
            shouldBeActive = true;
            level.sendBlockUpdated(blockPos, this.getBlockState(), this.getBlockState(), 2);
            setChanged();
        }
    }

    private void animateToPillar(ServerLevel level, Vec3 altarPos, Vec3 pillarPos){

        double dx = pillarPos.x() - (altarPos.x() + .5);
        double dy = pillarPos.y() - (altarPos.y() + .5);
        double dz = pillarPos.z() - (altarPos.z() + .5);

        double px = (dx * (ticks-100)) / 100;
        double py = (dy * (ticks-100)) / 100;
        double pz = (dz * (ticks-100)) / 100;

        ArcaneDivinity.LOG.debug("dx = {}", dx);
        ArcaneDivinity.LOG.debug("px: {} » py: {} » pz: {}", px , py, pz);

        level.sendParticles(ParticleTypes.ELECTRIC_SPARK, altarPos.x()+px, altarPos.y()+py, altarPos.z()+pz, 1, 0, 0, 0, 1);
    }

    public void setShouldBeActive(boolean shouldBeActive) {
        this.shouldBeActive = shouldBeActive;
        this.isActive = false;
        this.ticks = 0;
        if ( level != null) level.sendBlockUpdated(getBlockPos(), getBlockState(),getBlockState(),2);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, state -> {
            if (this.shouldBeActive && !this.isActive) {
                return state.setAndContinue(ACTIVATE);
            } else if (this.shouldBeActive && this.isActive) {
                return state.setAndContinue(ACTIVE_IDLE);
            } else {
                return state.setAndContinue(DEACTIVATE);
            }
        }));
    }

    public static <T extends ArcaneShrineBlockEntity> boolean getIsDay(T altar){
        return altar.isDay;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.animationCache;
    }

    @Override
    public void loadAdditional(@NotNull CompoundTag tag,  HolderLookup.Provider provider) {
        this.isActive = tag.getBoolean("is_active");
        this.shouldBeActive = tag.getBoolean("should_be_active");
        this.ticks = tag.getInt("ticks");
        this.isDay = tag.getBoolean("is_day");
        super.loadAdditional(tag, provider);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider $$1) {
        super.saveAdditional(tag, $$1);
        tag.putBoolean("is_active", this.isActive);
        tag.putBoolean("should_be_active", this.shouldBeActive);
        tag.putBoolean("is_day", this.isDay);
        tag.putInt("ticks", this.ticks);
    }
}
