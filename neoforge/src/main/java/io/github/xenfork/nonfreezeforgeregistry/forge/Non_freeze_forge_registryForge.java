package io.github.xenfork.nonfreezeforgeregistry.forge;

import io.github.xenfork.nonfreezeforgeregistry.Non_freeze_forge_registry;
import net.minecraftforge.fml.common.Mod;

@Mod(Non_freeze_forge_registry.MOD_ID)
public class Non_freeze_forge_registryForge {
    public Non_freeze_forge_registryForge() {
        Non_freeze_forge_registry.init();
    }
}