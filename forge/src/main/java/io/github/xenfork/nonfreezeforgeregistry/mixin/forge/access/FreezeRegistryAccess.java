package io.github.xenfork.nonfreezeforgeregistry.mixin.forge.access;

import net.minecraftforge.registries.NamespacedWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NamespacedWrapper.class)
public interface FreezeRegistryAccess {
    @Accessor(value = "locked", remap = false)
    void setFreeze(boolean freeze);
}
