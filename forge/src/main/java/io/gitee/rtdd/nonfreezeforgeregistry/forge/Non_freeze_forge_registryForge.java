package io.gitee.rtdd.nonfreezeforgeregistry.forge;

import io.gitee.rtdd.nonfreezeforgeregistry.Non_freeze_forge_registry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Non_freeze_forge_registry.MOD_ID)
public class Non_freeze_forge_registryForge {
    public Non_freeze_forge_registryForge() {
        Non_freeze_forge_registry.init();
    }
}