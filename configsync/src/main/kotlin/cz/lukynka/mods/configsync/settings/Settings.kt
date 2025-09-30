package cz.lukynka.mods.configsync.settings

import cz.lukynka.mods.configsync.keybinds.Keybinds
import io.github.dockyardmc.tide.codec.Codec
import io.github.dockyardmc.tide.codec.StructCodec

data class Settings(
    val fov: Int,
    val skinCustomization: SkinCustomization,
    val videoSettings: VideoSettings,
    val language: String,
    val musicAndSounds: MusicAndSounds,
    val chatSettings: ChatSettings,
    val accessibilitySettings: AccessibilitySettings,
    val server: List<Server>,
    val keybinds: Keybinds,
) {
    companion object {
        val CODEC = StructCodec.of(
            "fov", Codec.INT, Settings::fov,
            "skin_customization", SkinCustomization.CODEC, Settings::skinCustomization,
            "video_settings", VideoSettings.CODEC, Settings::videoSettings,
            "language", Codec.STRING, Settings::language,
            "music_and_sounds", MusicAndSounds.CODEC, Settings::musicAndSounds,
            "chat_settings", ChatSettings.CODEC, Settings::chatSettings,
            "accessibility_settings", AccessibilitySettings.CODEC, Settings::accessibilitySettings,
            "server", Server.CODEC.list(), Settings::server,
            "keybinds", Keybinds.CODEC, Settings::keybinds,
            ::Settings
        )
    }
}