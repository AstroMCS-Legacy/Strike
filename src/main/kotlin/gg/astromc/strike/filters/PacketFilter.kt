package gg.astromc.strike.filters

import net.minestom.server.event.player.PlayerPacketEvent
import net.minestom.server.network.packet.client.ClientPacket

fun <P1 : ClientPacket> packetFilter(p1: Class<P1>): (PlayerPacketEvent) -> Boolean = filter@{
    val packetClazz = it.packet::class.java
    return@filter packetClazz == p1
}

fun <P1 : ClientPacket, P2 : ClientPacket> packetFilter(p1: Class<P1>, p2: Class<P2>): (PlayerPacketEvent) -> Boolean = filter@{
    val packetClazz = it.packet::class.java
    return@filter packetClazz == p1 || packetClazz == p2
}

fun <P1 : ClientPacket, P2 : ClientPacket, P3 : ClientPacket> packetFilter(
    p1: Class<P1>, p2: Class<P2>, p3: Class<P3>
): (PlayerPacketEvent) -> Boolean = filter@{
    val packetClazz = it.packet::class.java
    return@filter packetClazz == p1 || packetClazz == p2 || packetClazz == p3
}
