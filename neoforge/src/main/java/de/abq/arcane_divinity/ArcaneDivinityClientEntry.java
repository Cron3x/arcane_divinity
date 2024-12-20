package de.abq.arcane_divinity;


import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = ArcaneDivinity.MOD_ID, dist = Dist.CLIENT)
public class ArcaneDivinityClientEntry {
    public ArcaneDivinityClientEntry(IEventBus eventBus) {
        ArcaneDivinity.initClient();
    }
}
