package cz.lukynka.mods.configsync.settings

import io.github.dockyardmc.tide.codec.Codec
import io.github.dockyardmc.tide.codec.StructCodec

data class VideoSettings(
    val renderDistance: Int,
    val simulationDistance: Int,
    val brightness: Double,
    val guiScale: Int,
    val fullscreen: Boolean,
    val vsync: Boolean,
    val maxFramerate: Int,
    val viewBobbing: Boolean,
    val attackIndicator: AttackIndicator,
    val autosaveIndicator: Boolean,
    val clouds: CloudStatus,
    val distortionEffects: Double,
    val fovEffects: Double,
) {
    companion object {
        val CODEC = StructCodec.of(
            "render_distance", Codec.INT, VideoSettings::renderDistance,
            "simulation_distance", Codec.INT, VideoSettings::simulationDistance,
            "brightness", Codec.DOUBLE, VideoSettings::brightness,
            "gui_scale", Codec.INT, VideoSettings::guiScale,
            "fullscreen", Codec.BOOLEAN, VideoSettings::fullscreen,
            "vsync", Codec.BOOLEAN, VideoSettings::vsync,
            "max_framerate", Codec.INT, VideoSettings::maxFramerate,
            "view_bobbing", Codec.BOOLEAN, VideoSettings::viewBobbing,
            "attack_indicator", Codec.enum(), VideoSettings::attackIndicator,
            "autosave_indicator", Codec.BOOLEAN, VideoSettings::autosaveIndicator,
            "clouds", Codec.enum(), VideoSettings::clouds,
            "distortion_effects", Codec.DOUBLE, VideoSettings::distortionEffects,
            "fov_effects", Codec.DOUBLE, VideoSettings::fovEffects,
            ::VideoSettings
        )
    }

    enum class AttackIndicator {
        CROSSHAIR,
        HOTBAR,
        OFF
    }

    enum class CloudStatus {
        OFF,
        FAST,
        FANCY
    }
}