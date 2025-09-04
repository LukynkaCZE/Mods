package cz.lukynka.mods.configsync.settings

import net.minecraft.client.AttackIndicatorStatus
import net.minecraft.client.CloudStatus
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ServerData
import net.minecraft.client.multiplayer.ServerList
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.HumanoidArm
import net.minecraft.world.entity.player.ChatVisiblity
import net.minecraft.world.entity.player.PlayerModelPart

object VanillaSettingsSetter {

    private val settings = Minecraft.getInstance().options

    fun set(settings: Settings) {
        setAccessibilitySettings(settings.accessibilitySettings)
        setChatSettings(settings.chatSettings)
        setFov(settings.fov)
        this.settings.languageCode = settings.language
        setMusicAndSounds(settings.musicAndSounds)
        setServers(settings.server)
        setSkinSettings(settings.skinCustomization)
        setVideoSettings(settings.videoSettings)
    }

    fun setServers(servers: List<Server>) {
        val serverList = ServerList(Minecraft.getInstance())
        for (i in 0 until serverList.size()) {
            serverList.remove(serverList.get(i))
        }
        servers.forEach { server ->
            serverList.add(ServerData(server.name, server.address, ServerData.Type.OTHER), false)
        }
        serverList.save()
    }

    fun setChatSettings(chatSettings: ChatSettings) {
        settings.chatVisibility().set(chatSettings.mode.toVanilla())
        settings.chatLinks().set(chatSettings.webLinks)
        settings.chatOpacity().set(chatSettings.chatTextOpacity)
        settings.chatScale().set(chatSettings.chatTextSize)
        settings.chatDelay().set(chatSettings.chatDelay)
        settings.chatHeightFocused().set(chatSettings.focusedHeight)
        settings.hideMatchedNames().set(chatSettings.hideMatchedNames)
        settings.onlyShowSecureChat().set(chatSettings.onlyShowSecureChat)
        settings.chatColors().set(chatSettings.colors)
        settings.chatLinksPrompt().set(chatSettings.promptOnLinks)
        settings.textBackgroundOpacity().set(chatSettings.textBackgroundOpacity)
        settings.chatLineSpacing().set(chatSettings.lineSpacing)
        settings.chatWidth().set(chatSettings.width)
        settings.chatHeightUnfocused().set(chatSettings.unfocusedHeight)
        settings.autoSuggestions().set(chatSettings.commandSuggestions)
        settings.reducedDebugInfo().set(chatSettings.reducedDebugInfo)
    }

    fun setMusicAndSounds(musicAndSounds: MusicAndSounds) {
        settings.getSoundSourceOptionInstance(SoundSource.MASTER).set(musicAndSounds.master.toDouble())
        settings.getSoundSourceOptionInstance(SoundSource.MUSIC).set(musicAndSounds.music.toDouble())
        settings.getSoundSourceOptionInstance(SoundSource.WEATHER).set(musicAndSounds.weather.toDouble())
        settings.getSoundSourceOptionInstance(SoundSource.HOSTILE).set(musicAndSounds.hostileCreatures.toDouble())
        settings.getSoundSourceOptionInstance(SoundSource.PLAYERS).set(musicAndSounds.players.toDouble())
        settings.getSoundSourceOptionInstance(SoundSource.VOICE).set(musicAndSounds.voice.toDouble())
        settings.getSoundSourceOptionInstance(SoundSource.RECORDS).set(musicAndSounds.jukebox.toDouble())
        settings.getSoundSourceOptionInstance(SoundSource.BLOCKS).set(musicAndSounds.blocks.toDouble())
        settings.getSoundSourceOptionInstance(SoundSource.NEUTRAL).set(musicAndSounds.friendlyCreatures.toDouble())
        settings.getSoundSourceOptionInstance(SoundSource.AMBIENT).set(musicAndSounds.ambient.toDouble())
        settings.showSubtitles().set(musicAndSounds.showSubtitles)
        settings.directionalAudio().set(musicAndSounds.directionalAudio)
    }

    fun setAccessibilitySettings(accessibilitySettings: AccessibilitySettings) {
        settings.menuBackgroundBlurriness().set(accessibilitySettings.menuBackgroundBlur)
        settings.highContrast().set(accessibilitySettings.highContrast)
        settings.backgroundForChatOnly().set(accessibilitySettings.backgroundForChatOnly)
        settings.chatLineSpacing().set(accessibilitySettings.lineSpacing)
        settings.notificationDisplayTime().set(accessibilitySettings.notificationTime)
        settings.screenEffectScale().set(accessibilitySettings.distortionEffects)
        settings.darknessEffectScale().set(accessibilitySettings.darknessEffects)
        settings.glintSpeed().set(accessibilitySettings.glintSpeed)
        settings.hideLightningFlash().set(accessibilitySettings.hideLightingFlashes)
        settings.narratorHotkey().set(accessibilitySettings.narratorHotKey)
        settings.showSubtitles().set(accessibilitySettings.showSubtitles)
        settings.autoJump().set(accessibilitySettings.autoJump)
        settings.darkMojangStudiosBackground().set(accessibilitySettings.monochromeLogo)
        settings.hideSplashTexts().set(accessibilitySettings.hideSplashTexts)
    }

    fun setFov(fov: Int) {
        settings.fov().set(fov)
    }

    fun setVideoSettings(new: VideoSettings) {
        settings.renderDistance().set(new.renderDistance)
        settings.simulationDistance().set(new.simulationDistance)
        settings.gamma().set(new.brightness)
        settings.guiScale().set(new.guiScale)
        settings.fullscreen().set(new.fullscreen)
        settings.enableVsync().set(new.vsync)
        settings.framerateLimit().set(new.maxFramerate)
        settings.bobView().set(new.viewBobbing)
        settings.attackIndicator().set(new.attackIndicator.toVanilla())
        settings.showAutosaveIndicator().set(new.autosaveIndicator)
        settings.cloudStatus().set(new.clouds.toVanilla())
        settings.screenEffectScale().set(new.distortionEffects)
        settings.fovEffectScale().set(new.fovEffects)
    }

    fun setSkinSettings(new: SkinCustomization) {
        settings.setModelPart(PlayerModelPart.CAPE, new.cape)
        settings.setModelPart(PlayerModelPart.LEFT_SLEEVE, new.leftSleeve)
        settings.setModelPart(PlayerModelPart.LEFT_PANTS_LEG, new.leftPantsLeg)
        settings.setModelPart(PlayerModelPart.HAT, new.hat)
        settings.setModelPart(PlayerModelPart.JACKET, new.jacket)
        settings.setModelPart(PlayerModelPart.RIGHT_PANTS_LEG, new.rightPantsLeg)
        settings.setModelPart(PlayerModelPart.RIGHT_SLEEVE, new.rightSleeve)
        settings.mainHand().set(new.mainHand.toHumanoidArm())
    }

}

fun ChatSettings.Mode.toVanilla(): ChatVisiblity {
    return when (this) {
        ChatSettings.Mode.SHOWN -> ChatVisiblity.FULL
        ChatSettings.Mode.COMMANDS_ONLY -> ChatVisiblity.SYSTEM
        ChatSettings.Mode.HIDDEN -> ChatVisiblity.HIDDEN
    }
}

fun VideoSettings.CloudStatus.toVanilla(): CloudStatus {
    return when (this) {
        VideoSettings.CloudStatus.OFF -> CloudStatus.OFF
        VideoSettings.CloudStatus.FAST -> CloudStatus.FAST
        VideoSettings.CloudStatus.FANCY -> CloudStatus.FANCY
    }
}

fun VideoSettings.AttackIndicator.toVanilla(): AttackIndicatorStatus {
    return when (this) {
        VideoSettings.AttackIndicator.CROSSHAIR -> AttackIndicatorStatus.CROSSHAIR
        VideoSettings.AttackIndicator.HOTBAR -> AttackIndicatorStatus.HOTBAR
        VideoSettings.AttackIndicator.OFF -> AttackIndicatorStatus.OFF
    }
}

fun SkinCustomization.Hand.toHumanoidArm(): HumanoidArm {
    return when (this) {
        SkinCustomization.Hand.RIGHT -> HumanoidArm.RIGHT
        SkinCustomization.Hand.LEFT -> HumanoidArm.LEFT
    }
}