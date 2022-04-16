package gg.astromc.strike.config

import kotlinx.serialization.Serializable
import me.codyq.envschema.annotations.EnvObject

@EnvObject
@Serializable
data class StrikeConfig(
    val enabledChecks: EnabledChecks = EnabledChecks(),
)

@EnvObject
@Serializable
data class EnabledChecks(
    val speed: Boolean = true,
    val fly: Boolean = true,
)
