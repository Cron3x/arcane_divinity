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
import java.util.function.Supplier;

public class ZEntityType {
    public static void init(){}
    public static final Map<String, EntityType<?>> ENTITIES = new LinkedHashMap<>();

    public static final Supplier<EntityType<RuptureBeamEntity>> RUPTURE_BEAM = build("rupture_beam", RuptureBeamEntity::new, MobCategory.MISC, 2, 2);

    private static <T extends Entity> Supplier<EntityType<T>> build(String key, EntityType.EntityFactory<T> factory, MobCategory category){
        return Services.PLATFORM_REGISTER.registerEntity(key, () ->
                EntityType.Builder.of(factory, category).build(key));
    }
    private static <T extends Entity> Supplier<EntityType<T>> build(String key, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height){
        return Services.PLATFORM_REGISTER.registerEntity(key, () ->
                EntityType.Builder.of(factory, category).sized(width, height).build(key));
    }

    public static void registerEntities(BiConsumer<EntityType<?>, ResourceLocation> register){
        ENTITIES.forEach((key, i) -> register.accept(i, ArcaneDivinity.path(key)));
    }
}
