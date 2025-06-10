package cz.lukynka.mods.common

import cz.lukynka.mods.common.protocol.InstalledModsPacket

object ModRegistry {

    private val registeredMods: MutableList<LukynkaMod> = mutableListOf()
    val entries get() = registeredMods.toList()

    fun register(mod: LukynkaMod) {
        registeredMods.add(mod)
    }

    fun getPacket(): InstalledModsPacket {
        return InstalledModsPacket(registeredMods)
    }

}