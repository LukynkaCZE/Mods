package cz.lukynka.mods.configsync.settings

import io.github.dockyardmc.tide.codec.Codec
import io.github.dockyardmc.tide.codec.StructCodec

data class MusicAndSounds(
    val master: Float,
    val music: Float,
    val weather: Float,
    val hostileCreatures: Float,
    val players: Float,
    val voice: Float,
    val jukebox: Float,
    val blocks: Float,
    val friendlyCreatures: Float,
    val ambient: Float,
    val showSubtitles: Boolean,
    val directionalAudio: Boolean
) {
    companion object {
        val CODEC = StructCodec.of(
            "master", Codec.FLOAT, MusicAndSounds::master,
            "music", Codec.FLOAT, MusicAndSounds::music,
            "weather", Codec.FLOAT, MusicAndSounds::weather,
            "hostile_creatures", Codec.FLOAT, MusicAndSounds::hostileCreatures,
            "players", Codec.FLOAT, MusicAndSounds::players,
            "voice", Codec.FLOAT, MusicAndSounds::voice,
            "jukebox", Codec.FLOAT, MusicAndSounds::jukebox,
            "blocks", Codec.FLOAT, MusicAndSounds::blocks,
            "friendly_creatures", Codec.FLOAT, MusicAndSounds::friendlyCreatures,
            "ambient", Codec.FLOAT, MusicAndSounds::ambient,
            "show_subtitles", Codec.BOOLEAN, MusicAndSounds::showSubtitles,
            "directional_audio", Codec.BOOLEAN, MusicAndSounds::directionalAudio,
            ::MusicAndSounds
        )
    }
}
