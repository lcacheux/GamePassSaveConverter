package net.cacheux.gpsc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.nio.BufferUnderflowException
import kotlin.test.assertFailsWith

class ContainersIndexParserTest {
    @Test
    fun testSingleDirSave() {
        val containersIndex =
            parseContainersIndex("$TEST_DATA_PATH/singledirsave/containers.index")
        println(containersIndex)

        assertEquals("505GAMESS.P.A.EiyudenChronicleRising_tefn33qh9azfc!Game", containersIndex.gameName)
        assertEquals("ec86cf94-f545-42aa-a9f3-07d79c4b3f0d", containersIndex.gameId)
        assertEquals(1, containersIndex.entries.size)

        assertEquals("EiyudenSaveContainer", containersIndex.entries[0].entryName1)
        assertEquals("EiyudenSaveContainer", containersIndex.entries[0].entryName2)
        assertEquals("\"0x8DB01DF747C29DE\"", containersIndex.entries[0].entryId)
        assertEquals(3, containersIndex.entries[0].containerIndex)
        assertEquals("531561FB71B74F089C4679054C2B505B", containersIndex.entries[0].uuid)
    }

    @Test
    fun testMultiDirSave() {
        val containersIndex =
            parseContainersIndex("$TEST_DATA_PATH/multidirsave/containers.index")
        println(containersIndex)

        assertEquals("FocusHomeInteractiveSA.APlagueTaleRequiem-Windows_4hny5m903y3g0!Game", containersIndex.gameName)
        assertEquals("ee9e1a49-6c28-4635-ac9d-c36d1da39729", containersIndex.gameId)
        assertEquals(3, containersIndex.entries.size)

        val entry = containersIndex.entries.find { it.entryName1 == "inputprofile" }
        assertNotNull(entry)
        assertEquals("inputprofile", entry?.entryName2)
        assertEquals(3, entry?.containerIndex)
        assertEquals("2D6DA2A3288B4A4A89D4078FDD6A33E0", entry?.uuid)
    }

    @Test
    fun testErrorSave() {
        assertFailsWith<BufferUnderflowException> {
            parseContainersIndex("$TEST_DATA_PATH/errorsave/containers.index")
        }
    }
}
