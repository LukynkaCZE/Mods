package cz.lukynka.mods.configsync.settings

import io.github.dockyardmc.tide.codec.Codec
import io.github.dockyardmc.tide.codec.StructCodec

data class Server(
    val name: String,
    val address: String,
    val resourcePackMode: ResourcePackMode
) {
    enum class ResourcePackMode {
        PROMPT,
        ENABLED,
        DISABLED,
    }

    companion object {
        val CODEC = StructCodec.of(
            "name", Codec.STRING, Server::name,
            "address", Codec.STRING, Server::address,
            "resource_pack_mode", Codec.enum(), Server::resourcePackMode,
            ::Server
        )
    }
}
