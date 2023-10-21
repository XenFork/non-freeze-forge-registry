package io.github.xenfork.nonfreezeforgeregistry.mixin.forge;

import io.github.xenfork.nonfreezeforgeregistry.forge.Non_freeze_forge_registryForge;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraftforge.registries.NamespacedWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = {NamespacedWrapper.class})
public class FreezeRegistryMixin<T> {
    @Inject(method = "freeze", at = @At("HEAD"), cancellable = true)
    private void freeze(CallbackInfoReturnable<Registry<T>> cir) {
        NamespacedWrapper<T> ts = (NamespacedWrapper<T>) (Object) this;
        if (non_freeze_forge_registry$freeze(ts)) {
            cir.setReturnValue(ts);
        }
    }

    @Inject(method = "lock", at = @At("HEAD"), cancellable = true)
    private void freeze(CallbackInfo ci) {
        NamespacedWrapper<T> ts = (NamespacedWrapper<T>) (Object) this;
        if (non_freeze_forge_registry$freeze(ts)) {
            ci.cancel();
        }
    }

    @Unique
    private static boolean non_freeze_forge_registry$freeze(Registry<?> registry) {
        RegistryKey<? extends Registry<?>> key = registry.getKey();
        return Non_freeze_forge_registryForge.namespaces.contains(key.getRegistry().getNamespace());
    }
}
