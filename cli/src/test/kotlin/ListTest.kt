import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ListTest {
    private val capture = OutputCapture()

    @BeforeEach
    fun before() {
        capture.startCapture()
    }

    @AfterEach
    fun after() {
        capture.stopCapture()
        capture.clearCapture()
    }

    @Test
    fun testListWithSingleDirSave() {
        val result = executeMain(arrayOf("list", "-p", "../testdata/singledirsave/"))
        assertEquals(0, result)
        assertEquals(14, capture.getOut().lines().size)
        assertTrue(capture.getOut().startsWith("Game name: 505GAMESS.P.A.EiyudenChronicleRising_tefn33qh9azfc!Game"))
        assertTrue(capture.getOut().contains("EcrSaveGame2"))
        assertTrue(capture.getOut().contains("EcrSaveSys"))
        assertTrue(capture.getErr().isEmpty())
    }

    @Test
    fun testListWithMultiDirSave() {
        val result = executeMain(arrayOf("list", "-p", "../testdata/multidirsave/"))
        assertEquals(0, result)
        assertEquals(9, capture.getOut().lines().size)
        assertTrue(capture.getOut().startsWith("Game name: FocusHomeInteractiveSA.APlagueTaleRequiem-Windows_4hny5m903y3g0!Game"))
        assertTrue(capture.getOut().contains("inputprofile"))
        assertTrue(capture.getOut().contains("slot00"))
        assertTrue(capture.getErr().isEmpty())
    }

    @Test
    fun testListWithErrorSave() {
        val result = executeMain(arrayOf("list", "-p", "../testdata/errorsave/"))
        assertEquals(1, result)
        assertTrue(capture.getOut().isEmpty())
        assertTrue(capture.getErr().startsWith("Error: Error parsing containers.index"))
    }

    @Test
    fun testListWithTuringTest() {
        val result = executeMain(arrayOf("list", "-p", "../testdata/turingtest/"))
        assertEquals(0, result)
        assertTrue(capture.getOut().startsWith("Game name: BulkheadInteractive.TheTuringTest_es3fzbrr4bc5r!VenusPrototype"))
        assertTrue(capture.getOut().contains("data"))
    }
}
