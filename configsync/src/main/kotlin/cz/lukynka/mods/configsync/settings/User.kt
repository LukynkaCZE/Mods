package cz.lukynka.mods.configsync.settings

data class User(
    var uuid: String,
    val username: String,
    val passwordHash: String,
    var settings: Settings
)