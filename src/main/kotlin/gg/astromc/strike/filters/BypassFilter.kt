package gg.astromc.strike.filters

import net.minestom.server.event.trait.PlayerEvent

fun bypassFilter(checkName: String): (PlayerEvent) -> Boolean = {
    !it.player.hasPermission("strike.bypass.*") &&
    !it.player.hasPermission("strike.bypass.$checkName")
}
