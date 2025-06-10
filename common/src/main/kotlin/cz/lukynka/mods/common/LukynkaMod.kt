package cz.lukynka.mods.common

import net.fabricmc.api.ClientModInitializer
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec

open class LukynkaMod(val name: String, val id: String, val version: Int) : ClientModInitializer {

    companion object {
        val STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, LukynkaMod::name,
            ByteBufCodecs.STRING_UTF8, LukynkaMod::id,
            ByteBufCodecs.VAR_INT, LukynkaMod::version,
            ::LukynkaMod
        )!!
    }

    protected fun registerMod() {
        ModRegistry.register(this)
    }

    override fun onInitializeClient() {}

}