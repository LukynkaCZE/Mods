package cz.lukynka.mods.configsync.mixin;

import cz.lukynka.mods.configsync.ConfigSyncMod;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Options.class)
public class OptionMixin {

    @Inject(at = @At("TAIL"), method = "save")
    public void save(CallbackInfo ci) {
        ConfigSyncMod.Companion.writeSettings();
    }

}
