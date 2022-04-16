package gg.astromc.strike.config

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.StringFormat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import me.codyq.envschema.EnvSchema
import java.io.File

inline fun <reified T> useConfig(
    file: File,
    default: T,
    envPrefix: String = "",
    format: StringFormat = Yaml.default,
): T {
    val config = if (file.exists()) {
        format.decodeFromString(file.readText())
    } else {
        file.parentFile.mkdirs()
        file.writeText(format.encodeToString(default))
        default
    }
    return EnvSchema.load(config, prefix = envPrefix)
}
