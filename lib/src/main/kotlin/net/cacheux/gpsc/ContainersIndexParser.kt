package net.cacheux.gpsc

import java.io.File
import java.io.FileInputStream

fun parseContainersIndex(path: String) = parseContainersIndex(File(path))

fun parseContainersIndex(file: File): ContainersIndex {
    file.inputStream().use { input ->
        input.readInt32() // Useless int value
        val entryCount = input.readInt32()
        input.readInt32() // Useless int value
        val gameName = input.readString()
        input.skipNBytes(12)
        val gameId = input.readString()
        input.skipNBytes(8)

        val entries = (0 until entryCount).map {
            input.parseContainersIndexEntry()
        }

        return ContainersIndex(gameName, gameId, entries)
    }
}

fun FileInputStream.parseContainersIndexEntry(): ContainersIndex.Entry {
    val name1 = readString()
    val name2 = readString()
    val id = readString()
    val index = readInt8()
    readInt32()
    val uuid = readUuid()
    skipNBytes(24) // Unknown data at end of each entry

    return ContainersIndex.Entry(name1, name2, id, index, uuid)
}
