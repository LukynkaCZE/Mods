package cz.lukynka.mods.common

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import org.slf4j.LoggerFactory

abstract class LukynkaMod(modId: String) : ClientModInitializer {

    val name: String
    val id: String
    val version: String

    init {
        val metadata = FabricLoader.getInstance().getModContainer(modId)
            .orElseThrow { IllegalStateException("Mod container for mod with id $modId does not exist") }
            .metadata

        this.name = metadata.name
        this.id = metadata.id
        this.version = metadata.version.friendlyString
    }

    companion object {
        val LOGGER = LoggerFactory.getLogger(LukynkaMod::class.java)
        val STREAM_CODEC = object : StreamCodec<FriendlyByteBuf, LukynkaMod> {

            override fun decode(buffer: FriendlyByteBuf): LukynkaMod {
                throw IllegalStateException("LukynkaMod is encode only")
            }

            override fun encode(buffer: FriendlyByteBuf, mod: LukynkaMod) {
                buffer.writeUtf(mod.id)
                buffer.writeUtf(mod.name)
                buffer.writeUtf(mod.version)
            }
        }
    }

    protected fun registerMod() {
        ModRegistry.register(this)
        LOGGER.info("Registered $this")
    }

    override fun toString(): String {
        return "LukynkaMod(name=$name, id=$id, version=$version)"
    }
}