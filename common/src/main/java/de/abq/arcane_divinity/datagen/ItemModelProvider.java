package de.abq.arcane_divinity.datagen;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;

import java.util.concurrent.CompletableFuture;

public class ItemModelProvider implements DataProvider {
    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }
}
