package cz.lukynka.mods.configsync.settings

import io.github.dockyardmc.tide.codec.Codec
import io.github.dockyardmc.tide.codec.StructCodec

data class Controls(
    val mouseSettings: MouseSettings,
    val sneak: SneakSprintAction,
    val autoJump: Boolean,
    val sprint: SneakSprintAction,
    val operatorItemsTab: Boolean,
    val keybinds: Map<String, String>
) {
    companion object {
        val CODEC = StructCodec.of(
            "mouse_settings", MouseSettings.CODEC, Controls::mouseSettings,
            "sneak", Codec.enum(), Controls::sneak,
            "auto_jump", Codec.BOOLEAN, Controls::autoJump,
            "sprint", Codec.enum(), Controls::sprint,
            "operator_items_tab", Codec.BOOLEAN, Controls::operatorItemsTab,
            "keybinds", Codec.STRING.mapTo(Codec.STRING), Controls::keybinds,
            ::Controls
        )
    }

    data class MouseSettings(
        val sensitivity: Int,
        val scrollSensitivity: Double,
        val invertMouse: Boolean,
        val rawInput: Boolean,
        val discreteScrolling: Boolean
    ) {
        companion object {
            val CODEC = StructCodec.of(
                "sensitivity", Codec.INT, MouseSettings::sensitivity,
                "scroll_sensitivity", Codec.DOUBLE, MouseSettings::scrollSensitivity,
                "invert_mouse", Codec.BOOLEAN, MouseSettings::invertMouse,
                "raw_input", Codec.BOOLEAN, MouseSettings::rawInput,
                "discrete_scrolling", Codec.BOOLEAN, MouseSettings::discreteScrolling,
                ::MouseSettings
            )
        }
    }

    enum class SneakSprintAction {
        HOLD,
        TOGGLE
    }
}