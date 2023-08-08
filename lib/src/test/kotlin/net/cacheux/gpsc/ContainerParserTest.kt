package net.cacheux.gpsc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ContainerParserTest {
    @Test
    fun testParseContainer() {
        val container =
            parseContainer("$TEST_DATA_PATH/singledirsave/531561FB71B74F089C4679054C2B505B/container.3")

        assertEquals(10, container.entries.size)

        val entry = container.entries.find { it.filename == "EcrSaveEc" }
        assertNotNull(entry)
        assertEquals("96B16C0BB63F477695F7B6594B9B5A37", entry?.uuid)

        println(container)
    }
}
