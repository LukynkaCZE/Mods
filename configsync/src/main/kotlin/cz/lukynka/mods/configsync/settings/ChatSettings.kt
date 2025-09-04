package cz.lukynka.mods.configsync.settings

import io.github.dockyardmc.tide.codec.Codec
import io.github.dockyardmc.tide.codec.StructCodec

data class ChatSettings(
    val mode: Mode,
    val webLinks: Boolean,
    val chatTextOpacity: Double,
    val chatTextSize: Double,
    val chatDelay: Double,
    val focusedHeight: Double,
    val hideMatchedNames: Boolean,
    val onlyShowSecureChat: Boolean,
    val colors: Boolean,
    val promptOnLinks: Boolean,
    val textBackgroundOpacity: Double,
    val lineSpacing: Double,
    val width: Double,
    val unfocusedHeight: Double,
    val commandSuggestions: Boolean,
    val reducedDebugInfo: Boolean
) {
    enum class Mode {
        SHOWN,
        COMMANDS_ONLY,
        HIDDEN
    }

    companion object {
        val CODEC = StructCodec.of(
            "mode", Codec.enum(), ChatSettings::mode,
            "web_links", Codec.BOOLEAN, ChatSettings::webLinks,
            "chat_text_opacity", Codec.DOUBLE, ChatSettings::chatTextOpacity,
            "chat_text_size", Codec.DOUBLE, ChatSettings::chatTextSize,
            "chat_delay", Codec.DOUBLE, ChatSettings::chatDelay,
            "focused_height", Codec.DOUBLE, ChatSettings::focusedHeight,
            "hide_matched_names", Codec.BOOLEAN, ChatSettings::hideMatchedNames,
            "only_show_secure_chat", Codec.BOOLEAN, ChatSettings::onlyShowSecureChat,
            "colors", Codec.BOOLEAN, ChatSettings::colors,
            "prompt_on_links", Codec.BOOLEAN, ChatSettings::promptOnLinks,
            "text_background_opacity", Codec.DOUBLE, ChatSettings::textBackgroundOpacity,
            "line_spacing", Codec.DOUBLE, ChatSettings::lineSpacing,
            "width", Codec.DOUBLE, ChatSettings::width,
            "unfocused_height", Codec.DOUBLE, ChatSettings::unfocusedHeight,
            "command_suggestions", Codec.BOOLEAN, ChatSettings::commandSuggestions,
            "reduced_debug_info", Codec.BOOLEAN, ChatSettings::reducedDebugInfo,
            ::ChatSettings
        )
    }
}