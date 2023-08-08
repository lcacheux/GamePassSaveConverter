import net.cacheux.gpsc.parseContainersIndex
import java.io.File
import java.io.IOException
import java.nio.BufferUnderflowException

@Throws(CliException::class)
fun convert(source: String, destination: String) {
    val index = getContainersIndexFile(source)

    File(destination).let { destDir ->
        try {
            parseContainersIndex(index).let { containers ->
                if (!destDir.exists()) {
                    destDir.mkdirs()
                } else if (destDir.isFile) {
                    throw CliException("Destination must be a directory")
                }

                containers.entries.forEach { entry ->
                    val destPath = "${destDir.absolutePath}/${entry.entryName1}"
                    index.entryList(entry) { it, dir ->
                        dir.child(it.uuid)?.copyTo(File("${destPath}/${it.filename}"))
                    }
                }
            }
        } catch (e: BufferUnderflowException) {
            throw CliException("Error parsing file ${index.name}")
        } catch (e: IOException) {
            throw CliException("Error writing file: ${e.message}")
        }
    }
}
