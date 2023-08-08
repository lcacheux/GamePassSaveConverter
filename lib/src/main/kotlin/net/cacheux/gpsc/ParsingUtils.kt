package net.cacheux.gpsc

import java.io.FileInputStream
import java.nio.ByteBuffer

fun FileInputStream.readInt8(): Int {
    this.readNBytes(1).let {
        // Should be converted to UByte first to avoid negative values
        return it[0].toUByte().toInt()
    }
}

fun FileInputStream.readInt32(): Int {
    this.readNBytes(4).let {
        return ByteBuffer.wrap(it.reversedArray()).getInt()
    }
}

fun FileInputStream.readUtf16Char(): Char {
    this.readNBytes(2).let {
        return ByteBuffer.wrap(it.reversedArray()).getChar()
    }
}

/**
 * String with an Int32 for the size, then UTF-16 chars.
 */
fun FileInputStream.readString(): String {
    val stringBuffer = StringBuffer()
    repeat(this.readInt32()) {
        stringBuffer.append(this.readUtf16Char())
    }
    return stringBuffer.toString()
}

/**
 * Fixed string has a size of 128 bytes (64 UTF-16 characters), string ends when reaching 0x0000 chars.
 */
fun FileInputStream.readFixedString(): String {
    val stringBuffer = StringBuffer()
    var count = 0
    var currentChar = readUtf16Char()
    do {
        stringBuffer.append(currentChar)
        currentChar = readUtf16Char()
        count++
    } while (currentChar != Char.MIN_VALUE && count < 64)
    readNBytes((63 - count) * 2) // Read remaining bytes
    return stringBuffer.toString()
}

/**
 * UUID is a 16 bytes value used to retrieve directories/files names.
 */
val uuidOrderMap = listOf(3, 2, 1, 0, 5, 4, 7, 6, 8, 9, 10, 11, 12, 13, 14, 15)
fun FileInputStream.readUuid(): String {
    this.readNBytes(16).let { readBytes ->
        return readBytes.mapIndexed { index, _ -> readBytes[uuidOrderMap[index]] }.toByteArray().joinToString("") {
            it.toHexString()
        }
    }
}
