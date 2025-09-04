package cz.lukynka.mods.common.mixin;

import net.minecraft.core.Registry;
import net.minecraft.network.protocol.configuration.ClientConfigurationPacketListener;
import net.minecraft.network.protocol.configuration.ClientboundRegistryDataPacket;
import net.minecraft.resources.ResourceKey;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientboundRegistryDataPacket.class)
public class RegistryDataPacketMixin {

    @Shadow @Final private ResourceKey<? extends Registry<?>> registry;

    @Inject(at = @At("HEAD"), method = "handle(Lnet/minecraft/network/protocol/configuration/ClientConfigurationPacketListener;)V")
    public void handle(ClientConfigurationPacketListener clientConfigurationPacketListener, CallbackInfo ci) {
        System.out.println("Handling registry packet for " + this.registry.toString());
    }

}
