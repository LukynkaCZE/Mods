package cz.lukynka.mods.configsync.settings

import io.github.dockyardmc.tide.codec.Codec
import io.github.dockyardmc.tide.codec.StructCodec

data class SkinCustomization(
    val cape: Boolean,
    val leftSleeve: Boolean,
    val leftPantsLeg: Boolean,
    val hat: Boolean,
    val jacket: Boolean,
    val rightPantsLeg: Boolean,
    val rightSleeve: Boolean,
    val mainHand: Hand
) {
    companion object {
        val CODEC = StructCodec.of(
            "cape", Codec.BOOLEAN, SkinCustomization::cape,
            "left_sleeve", Codec.BOOLEAN, SkinCustomization::leftSleeve,
            "left_pants_leg", Codec.BOOLEAN, SkinCustomization::leftPantsLeg,
            "hat", Codec.BOOLEAN, SkinCustomization::hat,
            "jacket", Codec.BOOLEAN, SkinCustomization::jacket,
            "right_pants_leg", Codec.BOOLEAN, SkinCustomization::rightPantsLeg,
            "right_sleeve", Codec.BOOLEAN, SkinCustomization::rightSleeve,
            "main_hand", Codec.enum(), SkinCustomization::mainHand,
            ::SkinCustomization
        )
    }

    enum class Hand {
        RIGHT,
        LEFT
    }
}