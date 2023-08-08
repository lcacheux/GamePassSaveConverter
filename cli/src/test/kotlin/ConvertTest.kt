import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

class ConvertTest {
    companion object {
        const val OUTPUT_DIR = "./build/tests/output/"
    }

    private val outputDir = File(OUTPUT_DIR)

    @AfterEach
    fun cleanup() {
        outputDir.deleteRecursively()
    }

    @Test
    fun testConvertWithSingleDirSave() {
        val result = executeMain(arrayOf("convert", "-p", "../testdata/singledirsave/", "-d", OUTPUT_DIR))
        assertEquals(0, result)
        assertEquals(1, outputDir.listFiles()?.size)
        assertEquals(10, outputDir.child("EiyudenSaveContainer")?.listFiles()?.size)
        assertFileExists("EiyudenSaveContainer", "EcrSaveSys")
        assertFileExists("EiyudenSaveContainer", "EcrSaveGame2")
    }

    @Test
    fun testConvertWithMultiDirSave() {
        val result = executeMain(arrayOf("convert", "-p", "../testdata/multidirsave/", "-d", OUTPUT_DIR))
        assertEquals(0, result)
        assertEquals(3, outputDir.listFiles()?.size)
        assertFileExists("inputprofile", "data")
        assertFileExists("slot00", "data")
    }

    @Test
    fun testConvertWithErrorSave() {
        val result = executeMain(arrayOf("convert", "-p", "../testdata/errorsave/", "-d", OUTPUT_DIR))
        assertEquals(1, result)
        assertFalse(outputDir.exists())
    }

    private fun assertFileExists(path: String, name: String) {
        assertEquals(true, outputDir.child(path)?.child(name)?.isFile)
    }
}
