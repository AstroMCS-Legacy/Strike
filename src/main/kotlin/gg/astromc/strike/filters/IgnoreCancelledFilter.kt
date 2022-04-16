package gg.astromc.strike.filters

import net.minestom.server.event.trait.CancellableEvent

val ignoreCancelledFilter: (CancellableEvent) -> Boolean = { !it.isCancelled }
