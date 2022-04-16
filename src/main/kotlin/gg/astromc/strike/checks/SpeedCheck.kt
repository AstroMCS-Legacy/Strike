package gg.astromc.strike.checks

import gg.astromc.strike.filters.bypassFilter
import gg.astromc.strike.filters.ignoreCancelledFilter
import gg.astromc.strike.tags.PosTagSerializer
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.event.player.PlayerMoveEvent
import net.minestom.server.tag.Tag
import world.cepi.kstom.event.listen
import kotlin.math.abs

class SpeedCheck(
    private val eventNode: EventNode<Event>,
) : Check {

    override val name = "speed"

    override fun initialize() {
        eventNode.listen<PlayerMoveEvent> {
            filters += ignoreCancelledFilter
            filters += bypassFilter(name)

            handler {
                val lastPosition = player.getTag(lastPositionTag)
                val newPosition = newPosition

                if (lastPosition == null) {
                    player.setTag(lastPositionTag, newPosition)
                    return@handler
                }

                val distX = abs(newPosition.x - lastPosition.x)
                val distY = abs(newPosition.y - lastPosition.y)
                val distZ = abs(newPosition.z - lastPosition.z)

                val distXZ = distX + distZ

                if (distXZ > 1 || distY > 7.5) {
                    player.teleport(lastPosition)
                    return@handler
                }

                player.setTag(lastPositionTag, newPosition)
            }
        }
    }

    companion object {
        private val lastPositionTag = Tag.Structure("strike.last_position", PosTagSerializer)
    }

}
