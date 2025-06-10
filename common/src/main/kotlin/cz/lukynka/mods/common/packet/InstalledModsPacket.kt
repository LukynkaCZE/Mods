package cz.lukynka.mods.common.packet

import cz.lukynka.mods.common.LukynkaMod
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation

class InstalledModsPacket(val mods: List<LukynkaMod>) : CustomPacketPayload {
    companion object {
        val TYPE: CustomPacketPayload.Type<InstalledModsPacket> = CustomPacketPayload.Type<InstalledModsPacket>(ResourceLocation.fromNamespaceAndPath("debugrender", "add"))

        val STREAM_CODEC = StreamCodec.composite(
            LukynkaMod.STREAM_CODEC.apply(ByteBufCodecs.list()), InstalledModsPacket::mods,
            ::InstalledModsPacket
        )!!
    }

    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> {
        return TYPE
    }
}