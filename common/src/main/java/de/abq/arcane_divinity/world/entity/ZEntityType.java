package de.abq.arcane_divinity.world.entity;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.platform.Services;
import de.abq.arcane_divinity.world.entity.vfx.RuptureBeamEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ZEntityType {
    public static void init(){}
    public static final Map<String, EntityType<?>> ENTITIES = new LinkedHashMap<>();

    public static final EntityType<RuptureBeamEntity> RUPTURE_BEAM = build("rupture_beam",
            EntityType.Builder.of(RuptureBeamEntity::new, MobCategory.MISC).sized(0.7F, 0.65F).eyeHeight(0.26F).clientTrackingRange(10)
);

    private static <T extends Entity> EntityType<T> build(String key, EntityType.Builder<T> builder){
        EntityType<T> type = builder.build(key);
        ENTITIES.put(key, type);
        return Services.PLATFORM_REGISTER.registerEntity(key, () -> type).get();
    }

    public static void registerEntities(BiConsumer<EntityType<?>, ResourceLocation> register){
        ENTITIES.forEach((key, i) -> register.accept(i, ArcaneDivinity.path(key)));
    }
}
