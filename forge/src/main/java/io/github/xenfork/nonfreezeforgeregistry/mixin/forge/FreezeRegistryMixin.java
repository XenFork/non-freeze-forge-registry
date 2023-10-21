package io.github.xenfork.nonfreezeforgeregistry.mixin.forge;

import io.github.xenfork.nonfreezeforgeregistry.forge.Non_freeze_forge_registryForge;
import jdk.jfr.Experimental;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.NamespacedWrapper;
import net.minecraftforge.registries.RegistryManager;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Function;
@SuppressWarnings("ALL")
@Experimental
@ApiStatus.Experimental
@Mixin(NamespacedWrapper.class)
public class FreezeRegistryMixin<T> {
    @Unique
    public NamespacedWrapper<T> non_freeze_forge_registry$_this;
    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(ForgeRegistry<T> fowner, Function<T, RegistryEntry.Reference<T>> intrusiveHolderCallback, RegistryManager stage, CallbackInfo ci) {
        non_freeze_forge_registry$_this = (NamespacedWrapper<T>) (Object) this;
    }
    @Inject(method = "freeze", at = @At("HEAD"), cancellable = true)
    private void freeze(CallbackInfoReturnable<Registry<T>> cir) {

        if (non_freeze_forge_registry$freeze(non_freeze_forge_registry$_this)) {
            cir.setReturnValue(non_freeze_forge_registry$_this);
        }
    }

    @Inject(method = "lock", at = @At("HEAD"), cancellable = true)
    private void freeze(CallbackInfo ci) {

        if (non_freeze_forge_registry$freeze(non_freeze_forge_registry$_this)) {
            ci.cancel();
        }
    }

    @Unique
    private static boolean non_freeze_forge_registry$freeze(Registry<?> registry) {
        RegistryKey<? extends Registry<?>> key = registry.getKey();
        return Non_freeze_forge_registryForge.namespaces.contains(key.getRegistry().getNamespace());
    }
}
