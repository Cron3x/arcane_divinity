package de.abq.arcane_divinity.util;

import de.abq.arcane_divinity.ArcaneDivinity;
import de.abq.arcane_divinity.config.Config;
import de.abq.arcane_divinity.platform.Services;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public final class SharedMixins {
    public static Item fixElytraItemCheck(Item item, ItemStack itemStack){
        if (Services.PLATFORM.defaultItemBuilder() == null) {
            ArcaneDivinity.LOG.error("Services.PLATFORM.defaultItemBuilder() == null");
            return item;
        }
        if (Config.ALLOWED_FLIGHT_ITEMS.contains( itemStack.getItem() )) return itemStack.getItem();
        return item;
    }

    public static void isFlyableArmor(LocalPlayer self, ItemStack itemStack) {
        itemStack.getEnchantments().keySet().forEach( (ench) -> {
            //FIXME
            /*if (ench.value().equals(Enchantments.UNBREAKING)){ //TODO: replace with custom enchantment
                System.out.println("I want to fly");
                WingsArmorRenderer renderer = new WingsArmorRenderer();
                renderer.setupAnim(self,0,0,0,0,0);
                System.out.println(self.getName() + ": "+self.getDisplayName());
                self.connection.send(new ServerboundPlayerCommandPacket(self, ServerboundPlayerCommandPacket.Action.START_FALL_FLYING));
            }*/
        });
    }
}