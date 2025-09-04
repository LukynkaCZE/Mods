package cz.lukynka.mods.configsync

import dev.isxander.yacl3.api.ConfigCategory
import dev.isxander.yacl3.api.Option
import dev.isxander.yacl3.api.YetAnotherConfigLib
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component

object ModConfig {

    var syncConfig: Boolean = true

    fun getScreen(): Screen {
        val config = YetAnotherConfigLib.createBuilder()
            .title(Component.literal("Config Sync"))
            .category(
                ConfigCategory.createBuilder()
                    .name(Component.literal("Sync Options"))
                    .option(
                        Option.createBuilder<Boolean>()
                            .name(Component.literal("Sync Config"))
                            .binding(true, { syncConfig }, { syncConfig = it })
                            .controller(TickBoxControllerBuilder::create)
                            .build()
                    )
                    .build()
            )
            .build()

        return config.generateScreen(Minecraft.getInstance().screen)
    }
}