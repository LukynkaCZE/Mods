package cz.lukynka.mods.configsync.mixin;

import cz.lukynka.mods.configsync.ConfigSyncMod;
import net.minecraft.client.Options;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Options.class)
public class OptionsMixin {

    @Shadow
    @Final
    static Logger LOGGER;

    @Inject(at = @At("TAIL"), method = "save")
    public void save(CallbackInfo ci) {
        if (!ConfigSyncMod.Companion.getWRITE_LOCKED()) {
            ConfigSyncMod.Companion.writeSettings();
        } else {
            LOGGER.error("Minecraft tried to save settings but config sync is write-locked until fully loaded");
        }
    }

    @Inject(at = @At("HEAD"), method = "load")
    public void loadHead(CallbackInfo ci) {
        ConfigSyncMod.Companion.setWRITE_LOCKED(true);
    }


//    @Inject(at = @At("TAIL"), method = "<init>")
//    public void init(CallbackInfo ci) {
//        ConfigSyncMod.Companion.readSettings((Options) (Object) this);
//        ConfigSyncMod.Companion.setWRITE_LOCKED(false);
//    }

}
