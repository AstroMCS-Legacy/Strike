package gg.astromc.strike

import gg.astromc.strike.checks.SpeedCheck
import gg.astromc.strike.config.StrikeConfig
import gg.astromc.strike.config.useConfig
import net.minestom.server.extensions.Extension
import java.io.File

class StrikeExtension : Extension() {

    override fun initialize(): LoadStatus {
        val configFile = File(dataDirectory().toFile(), "config.yml")
        val config = useConfig(configFile, StrikeConfig(), "STRIKE_")

        val speedCheck = SpeedCheck(eventNode())
        speedCheck.initialize()

        return LoadStatus.SUCCESS
    }

    override fun terminate() {
    }

}
