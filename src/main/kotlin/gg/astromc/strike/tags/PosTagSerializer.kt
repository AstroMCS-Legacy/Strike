package gg.astromc.strike.tags

import net.minestom.server.coordinate.Pos
import net.minestom.server.tag.Tag
import net.minestom.server.tag.TagReadable
import net.minestom.server.tag.TagSerializer
import net.minestom.server.tag.TagWritable

object PosTagSerializer : TagSerializer<Pos> {
    private val xTag = Tag.Double("x")
    private val yTag = Tag.Double("y")
    private val zTag = Tag.Double("z")
    private val yawTag = Tag.Float("yaw")
    private val pitchTag = Tag.Float("pitch")

    override fun read(reader: TagReadable): Pos? {
        val x = reader.getTag(xTag) ?: return null
        val y = reader.getTag(yTag) ?: return null
        val z = reader.getTag(zTag) ?: return null
        val yaw = reader.getTag(yawTag) ?: return null
        val pitch = reader.getTag(pitchTag) ?: return null
        return Pos(x, y, z, yaw, pitch)
    }

    override fun write(writer: TagWritable, value: Pos?) {
        if (value == null) {
            writer.removeTag(xTag)
            writer.removeTag(yTag)
            writer.removeTag(zTag)
            writer.removeTag(yawTag)
            writer.removeTag(pitchTag)
            return
        }

        writer.setTag(xTag, value.x)
        writer.setTag(yTag, value.y)
        writer.setTag(zTag, value.z)
        writer.setTag(yawTag, value.yaw)
        writer.setTag(pitchTag, value.pitch)
    }
}
