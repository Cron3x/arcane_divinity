package de.abq.arcane_divinity.config;

import de.abq.arcane_divinity.world.item.ZItems;
import net.minecraft.world.item.Item;

import java.util.Arrays;
import java.util.List;

public final class Config {
    public static final List<Item> ALLOWED_FLIGHT_ITEMS = Arrays.stream(new Item[]{
            ZItems.WINGS.get(),
    }).toList();

    public static final boolean PEDESTAL_ANIMATION = false;
    public static final float PEDESTAL_SPEED = 0.01f;

    public static final class WING {
        public static final double MAX_SPEED = 5;
        public static final double MIN_SPEED = 0;
        public static final double SPRINT_MULTIPLIER = 0.3;
        public static final double NON_SPRINT_MULTIPLIER = 0.1;
    }
}
