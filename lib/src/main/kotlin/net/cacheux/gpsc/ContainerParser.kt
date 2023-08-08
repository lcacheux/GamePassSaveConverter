package net.cacheux.gpsc

import java.io.File
import java.io.FileInputStream

fun parseContainer(path: String) = parseContainer(File(path))

fun parseContainer(file: File): Container {
    file.inputStream().use { input ->
        input.readInt32() // First unused value
        val count = input.readInt32()
        val entries = (0 until count).map {
            input.parseContainerEntry()
        }
        return Container(entries)
    }
}

fun FileInputStream.parseContainerEntry(): Container.Entry {
    val name = readFixedString()
    val uuid = readUuid()
    readUuid() // Repeated UUID
    return Container.Entry(name, uuid)
}
