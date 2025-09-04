package cz.lukynka.mods.configsync.settings

import io.github.dockyardmc.tide.codec.Codec
import io.github.dockyardmc.tide.codec.StructCodec

data class AccessibilitySettings(
    val highContrast: Boolean,
    val menuBackgroundBlur: Int,
    val backgroundForChatOnly: Boolean,
    val lineSpacing: Double,
    val notificationTime: Double,
    val distortionEffects: Double,
    val darknessEffects: Double,
    val glintSpeed: Double,
    val hideLightingFlashes: Boolean,
    val narratorHotKey: Boolean,
    val showSubtitles: Boolean,
    val autoJump: Boolean,
    val monochromeLogo: Boolean,
    val hideSplashTexts: Boolean
) {

    companion object {
        val CODEC = StructCodec.of(
            "high_contrast", Codec.BOOLEAN, AccessibilitySettings::highContrast,
            "menu_background_blur", Codec.INT, AccessibilitySettings::menuBackgroundBlur,
            "background_for_chat_only", Codec.BOOLEAN, AccessibilitySettings::backgroundForChatOnly,
            "line_spacing", Codec.DOUBLE, AccessibilitySettings::lineSpacing,
            "notification_time", Codec.DOUBLE, AccessibilitySettings::notificationTime,
            "distortion_effects", Codec.DOUBLE, AccessibilitySettings::distortionEffects,
            "darkness_effects", Codec.DOUBLE, AccessibilitySettings::darknessEffects,
            "glint_speed", Codec.DOUBLE, AccessibilitySettings::glintSpeed,
            "hide_lighting_flashes", Codec.BOOLEAN, AccessibilitySettings::hideLightingFlashes,
            "narrator_hot_key", Codec.BOOLEAN, AccessibilitySettings::narratorHotKey,
            "show_subtitles", Codec.BOOLEAN, AccessibilitySettings::showSubtitles,
            "auto_jump", Codec.BOOLEAN, AccessibilitySettings::autoJump,
            "monochrome_logo", Codec.BOOLEAN, AccessibilitySettings::monochromeLogo,
            "hide_splash_texts", Codec.BOOLEAN, AccessibilitySettings::hideSplashTexts,
            ::AccessibilitySettings
        )
    }
}