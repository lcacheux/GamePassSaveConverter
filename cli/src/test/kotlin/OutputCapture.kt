import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.charset.Charset

/**
 * Used to capture stdout/stderr and inspect them during tests.
 */
class OutputCapture {
    private val outBuffer = ByteArrayOutputStream()
    private val errBuffer = ByteArrayOutputStream()
    private val stdOut = System.out
    private val stdErr = System.err

    fun startCapture() {
        System.setOut(PrintStream(outBuffer))
        System.setErr(PrintStream(errBuffer))
    }

    fun stopCapture() {
        System.setOut(stdOut)
        System.setErr(stdErr)
    }

    fun clearCapture() {
        outBuffer.reset()
        errBuffer.reset()
    }

    fun getOut(): String = outBuffer.toString(Charset.defaultCharset())
    fun getErr(): String = errBuffer.toString(Charset.defaultCharset())
}
