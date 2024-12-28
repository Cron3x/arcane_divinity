package de.abq.arcane_divinity.config;

import de.abq.arcane_divinity.world.item.ZItems;
import net.minecraft.world.item.Item;

import java.util.Arrays;
import java.util.List;

public final class CONFIG {
    public static final List<Item> ALLOWED_FLIGHT_ITEMS = Arrays.stream(new Item[]{
            ZItems.WINGS.get(),
    }).toList();

    public static final double ELYTRA__MAX_SPEED = 5;
    public static final double ELYTRA__MIN_SPEED = 0;
    public static final double ELYTRA__SPRINT_MULTIPLIER = 0.3;
    public static final double ELYTRA__NON_SPRINT_MULTIPLIER = 0.1;
}
