package cz.lukynka.mods.configsync.keybinds

import com.mojang.blaze3d.platform.InputConstants
import cz.lukynka.mods.configsync.mixin.KeyMappingAccessor
import io.github.dockyardmc.tide.codec.Codec
import io.github.dockyardmc.tide.codec.StructCodec
import net.minecraft.client.Minecraft

data class Keybinds(val keys: Map<String, String?>) {
    companion object {

        val CODEC = StructCodec.of(
            "keys", Codec.STRING.mapTo(Codec.STRING.optional()), Keybinds::keys,
            ::Keybinds
        )

        fun get(): Keybinds {
            val map = mutableMapOf<String, String?>()
            Minecraft.getInstance().options.keyMappings.forEach { keyMapping ->
                val accessor = keyMapping as KeyMappingAccessor
                if (keyMapping.isUnbound) {
                    map[keyMapping.name] = null
                } else {
                    map[keyMapping.name] = accessor.key.name
                }
            }
            return Keybinds(map)
        }

        fun set(keybinds: Keybinds) {
            keybinds.keys.forEach { storedKey ->
                Minecraft.getInstance().options.keyMappings.forEach { keyMapping ->
                    if (keyMapping.name == storedKey.key && storedKey.value != null) {
                        keyMapping.setKey(InputConstants.getKey(storedKey.value!!))
                    }
                }
            }
        }
    }
}
