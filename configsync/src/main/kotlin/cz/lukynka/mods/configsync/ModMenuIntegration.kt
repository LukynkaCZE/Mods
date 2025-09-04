package cz.lukynka.mods.configsync

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi

class ModMenuIntegration : ModMenuApi {

    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
        return ConfigScreenFactory { _ -> ModConfig.getScreen() }
    }

}