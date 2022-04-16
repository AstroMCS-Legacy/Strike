package gg.astromc.strike.checks

sealed interface Check {
    val name: String
    fun initialize()
}
