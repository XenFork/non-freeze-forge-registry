package io.github.xenfork.nonfreezeforgeregistry.forge;

import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.versions.forge.ForgeVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

import static io.github.xenfork.nonfreezeforgeregistry.forge.Non_freeze_forge_registryForge.modid;

@Mod(modid)
public class Non_freeze_forge_registryForge {
    public static final String modid = "non_freeze_forge_registry";
    public static final HashSet<String> namespaces = new HashSet<>();

    public static final Logger logger = LoggerFactory.getLogger(Non_freeze_forge_registryForge.class);

    static {
        namespaces.add(Identifier.DEFAULT_NAMESPACE);
        namespaces.add(ForgeVersion.MOD_ID);
        namespaces.add(modid);
    }

    public Non_freeze_forge_registryForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.register(this);
    }
    @SubscribeEvent
    public void loaded(FMLLoadCompleteEvent event) {
        Registries.freezeRegistries();
        logger.info("registry is refreeze");
    }
}