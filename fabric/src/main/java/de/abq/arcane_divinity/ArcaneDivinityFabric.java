package de.abq.arcane_divinity;

import net.fabricmc.api.ModInitializer;

public class ArcaneDivinityFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        ArcaneDivinity.registerEverything();
    }
}