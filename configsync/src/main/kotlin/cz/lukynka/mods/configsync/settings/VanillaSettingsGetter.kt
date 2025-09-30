package cz.lukynka.mods.configsync.settings

import cz.lukynka.mods.configsync.keybinds.Keybinds
import net.minecraft.client.AttackIndicatorStatus
import net.minecraft.client.CloudStatus
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ServerData
import net.minecraft.client.multiplayer.ServerList
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.HumanoidArm
import net.minecraft.world.entity.player.ChatVisiblity
import net.minecraft.world.entity.player.PlayerModelPart

object VanillaSettingsGetter {

    private val settings = Minecraft.getInstance().options


    fun get(): Settings {
        return Settings(
            fov = getFov(),
            skinCustomization = getSkinCustomization(),
            videoSettings = getVideoSettings(),
            language = settings.languageCode,
            musicAndSounds = getMusicAndSound(),
            chatSettings = getChatSettings(),
            accessibilitySettings = getAccessibilitySettings(),
            server = getServers(),
            keybinds = Keybinds.get()
        )
    }

    private fun getServers(): List<Server> {
        val list = mutableListOf<Server>()
        val serverList = ServerList(Minecraft.getInstance())
        for (i in 0 until serverList.size()) {
            val server = serverList.get(i)
            val name = server.name
            val ip = server.ip
            val mode = server.resourcePackStatus.toMode()
            val cloudServer = Server(name, ip, mode)
            list.add(cloudServer)
        }

        return list
    }

    private fun getChatSettings(): ChatSettings {
        return ChatSettings(
            mode = settings.chatVisibility().get().toVanilla(),
            webLinks = settings.chatLinks().get(),
            chatTextOpacity = settings.chatOpacity().get(),
            chatTextSize = settings.chatScale().get(),
            chatDelay = settings.chatDelay().get(),
            focusedHeight = settings.chatHeightFocused().get(),
            hideMatchedNames = settings.hideMatchedNames().get(),
            onlyShowSecureChat = settings.onlyShowSecureChat().get(),
            colors = settings.chatColors().get(),
            promptOnLinks = settings.chatLinksPrompt().get(),
            textBackgroundOpacity = settings.textBackgroundOpacity().get(),
            lineSpacing = settings.chatLineSpacing().get(),
            width = settings.chatWidth().get(),
            unfocusedHeight = settings.chatHeightUnfocused().get(),
            commandSuggestions = settings.autoSuggestions().get(),
            reducedDebugInfo = settings.reducedDebugInfo().get(),
        )
    }

    private fun getMusicAndSound(): MusicAndSounds {
        return MusicAndSounds(
            master = settings.getSoundSourceVolume(SoundSource.MASTER),
            music = settings.getSoundSourceVolume(SoundSource.MUSIC),
            weather = settings.getSoundSourceVolume(SoundSource.WEATHER),
            hostileCreatures = settings.getSoundSourceVolume(SoundSource.HOSTILE),
            players = settings.getSoundSourceVolume(SoundSource.PLAYERS),
            voice = settings.getSoundSourceVolume(SoundSource.VOICE),
            jukebox = settings.getSoundSourceVolume(SoundSource.RECORDS),
            blocks = settings.getSoundSourceVolume(SoundSource.BLOCKS),
            friendlyCreatures = settings.getSoundSourceVolume(SoundSource.NEUTRAL),
            ambient = settings.getSoundSourceVolume(SoundSource.AMBIENT),
            showSubtitles = settings.showSubtitles().get(),
            directionalAudio = settings.directionalAudio().get(),
        )
    }

    private fun getSkinCustomization(): SkinCustomization {
        return SkinCustomization(
            cape = settings.isModelPartEnabled(PlayerModelPart.CAPE),
            leftSleeve = settings.isModelPartEnabled(PlayerModelPart.LEFT_SLEEVE),
            leftPantsLeg = settings.isModelPartEnabled(PlayerModelPart.LEFT_PANTS_LEG),
            hat = settings.isModelPartEnabled(PlayerModelPart.HAT),
            jacket = settings.isModelPartEnabled(PlayerModelPart.JACKET),
            rightPantsLeg = settings.isModelPartEnabled(PlayerModelPart.RIGHT_PANTS_LEG),
            rightSleeve = settings.isModelPartEnabled(PlayerModelPart.RIGHT_SLEEVE),
            mainHand = settings.mainHand().get().toHand(),
        )
    }

    private fun getFov(): Int {
        return settings.fov().get() ?: 90
    }

    private fun getVideoSettings(): VideoSettings {
        return VideoSettings(
            renderDistance = settings.renderDistance().get(),
            simulationDistance = settings.simulationDistance().get(),
            brightness = settings.gamma().get(),
            guiScale = settings.guiScale().get(),
            fullscreen = settings.fullscreen().get(),
            vsync = settings.enableVsync().get(),
            maxFramerate = settings.framerateLimit().get(),
            viewBobbing = settings.bobView().get(),
            attackIndicator = settings.attackIndicator().get().toVanilla(),
            autosaveIndicator = settings.showAutosaveIndicator().get(),
            clouds = settings.cloudStatus().get().toCloudVideoStatus(),
            distortionEffects = settings.screenEffectScale().get(),
            fovEffects = settings.fovEffectScale().get(),
        )
    }

    private fun getAccessibilitySettings(): AccessibilitySettings {
        return AccessibilitySettings(
            highContrast = settings.highContrast().get(),
            menuBackgroundBlur = settings.menuBackgroundBlurriness,
            backgroundForChatOnly = settings.backgroundForChatOnly().get(),
            lineSpacing = settings.chatLineSpacing().get(),
            notificationTime = settings.notificationDisplayTime().get(),
            distortionEffects = settings.screenEffectScale().get(),
            darknessEffects = settings.darknessEffectScale().get(),
            glintSpeed = settings.glintSpeed().get(),
            hideLightingFlashes = settings.hideLightningFlash().get(),
            narratorHotKey = settings.narratorHotkey().get(),
            showSubtitles = settings.showSubtitles().get(),
            autoJump = settings.autoJump().get(),
            monochromeLogo = settings.darkMojangStudiosBackground().get(),
            hideSplashTexts = settings.hideSplashTexts().get(),
        )
    }
}


fun ServerData.ServerPackStatus.toMode(): Server.ResourcePackMode {
    return when (this) {
        ServerData.ServerPackStatus.ENABLED -> Server.ResourcePackMode.ENABLED
        ServerData.ServerPackStatus.DISABLED -> Server.ResourcePackMode.DISABLED
        ServerData.ServerPackStatus.PROMPT -> Server.ResourcePackMode.PROMPT
    }
}


fun ChatVisiblity.toVanilla(): ChatSettings.Mode {
    return when (this) {
        ChatVisiblity.FULL -> ChatSettings.Mode.SHOWN
        ChatVisiblity.SYSTEM -> ChatSettings.Mode.COMMANDS_ONLY
        ChatVisiblity.HIDDEN -> ChatSettings.Mode.HIDDEN
    }
}

fun AttackIndicatorStatus.toVanilla(): VideoSettings.AttackIndicator {
    return when (this) {
        AttackIndicatorStatus.OFF -> VideoSettings.AttackIndicator.OFF
        AttackIndicatorStatus.CROSSHAIR -> VideoSettings.AttackIndicator.CROSSHAIR
        AttackIndicatorStatus.HOTBAR -> VideoSettings.AttackIndicator.HOTBAR
    }
}

fun CloudStatus.toCloudVideoStatus(): VideoSettings.CloudStatus {
    return when (this) {
        CloudStatus.OFF -> VideoSettings.CloudStatus.OFF
        CloudStatus.FAST -> VideoSettings.CloudStatus.FAST
        CloudStatus.FANCY -> VideoSettings.CloudStatus.FANCY
    }
}

fun HumanoidArm.toHand(): SkinCustomization.Hand {
    return when (this) {
        HumanoidArm.LEFT -> SkinCustomization.Hand.LEFT
        HumanoidArm.RIGHT -> SkinCustomization.Hand.RIGHT
    }
}