package cz.lukynka.mods.configsync

import com.google.gson.JsonParser
import cz.lukynka.mods.common.LukynkaMod
import cz.lukynka.mods.configsync.settings.Settings
import cz.lukynka.mods.configsync.settings.VanillaSettingsGetter
import cz.lukynka.mods.configsync.settings.VanillaSettingsSetter
import io.github.dockyardmc.tide.transcoder.JsonTranscoder
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents
import net.minecraft.client.Minecraft
import net.minecraft.client.Options
import java.io.File

class ConfigSyncMod : LukynkaMod("config_sync") {

    companion object {
        private val USER_HOME = System.getProperty("user.home")
        private const val DATA_FOLDER = ".config_sync"
        private const val DATA_FILE = "$DATA_FOLDER/config.json"
        private val CONFIG_FILE = File(USER_HOME, DATA_FILE)
        private val CONFIG_FOLDER = File(USER_HOME, DATA_FOLDER)
        var WRITE_LOCKED: Boolean = true

        fun writeSettings() {
            val currentSettings = VanillaSettingsGetter.get()
            CONFIG_FILE.writeText(Settings.CODEC.encode(JsonTranscoder, currentSettings).toString())
        }

        @JvmStatic
        fun readSettings(options: Options) {
            val text = CONFIG_FILE.readText()
            val settings = Settings.CODEC.decode(JsonTranscoder, JsonParser.parseString(text))
            VanillaSettingsSetter.set(settings, options)
        }
    }

    private var initialized: Boolean = false

    override fun onInitializeClient() {

        ClientLifecycleEvents.CLIENT_STARTED.register { _ ->
            WRITE_LOCKED = false
            if (!CONFIG_FILE.exists()) {
                CONFIG_FOLDER.mkdirs()
                CONFIG_FILE.createNewFile()
                writeSettings()
            } else {
                readSettings(Minecraft.getInstance().options)
            }
            initialized = true
        }
    }
}