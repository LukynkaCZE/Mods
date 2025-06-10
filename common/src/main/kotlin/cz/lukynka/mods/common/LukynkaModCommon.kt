package cz.lukynka.mods.common

import cz.lukynka.mods.common.protocol.InstalledModsPacket
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry

class LukynkaModCommon : LukynkaMod("lukynka_mods_common") {

    override fun onInitializeClient() {
        registerMod()

        PayloadTypeRegistry.playC2S().register(InstalledModsPacket.TYPE, InstalledModsPacket.STREAM_CODEC)

        ClientPlayConnectionEvents.JOIN.register { _, sender, _ ->
            sender.sendPacket(ModRegistry.getPacket())
        }
    }
}