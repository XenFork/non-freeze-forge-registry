package io.github.xenfork.nonfreezeforgeregistry.forge;

import io.github.xenfork.nonfreezeforgeregistry.mixin.forge.access.FreezeRegistryAccess;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.NamespacedWrapper;

import java.util.HashSet;

import static io.github.xenfork.nonfreezeforgeregistry.forge.Non_freeze_forge_registryForge.modid;

@Mod(modid)
public class Non_freeze_forge_registryForge {
    public static final String modid = "non_freeze_forge_registry";
    public static final HashSet<String> namespaces = new HashSet<>();

    static {
        namespaces.add(Identifier.DEFAULT_NAMESPACE);
    }

    public Non_freeze_forge_registryForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.register(this);
    }
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void clientEvent(TickEvent.ClientTickEvent event) {
        NamespacedWrapper<? extends Registry<?>> registries = (NamespacedWrapper<? extends Registry<?>>)Registries.REGISTRIES;
        ((FreezeRegistryAccess) registries).setFreeze(true);
    }
    @SubscribeEvent
    @OnlyIn(Dist.DEDICATED_SERVER)
    public void serverEvent(TickEvent.ServerTickEvent event) {
        NamespacedWrapper<? extends Registry<?>> registries = (NamespacedWrapper<? extends Registry<?>>)Registries.REGISTRIES;
        ((FreezeRegistryAccess) registries).setFreeze(true);
    }
}