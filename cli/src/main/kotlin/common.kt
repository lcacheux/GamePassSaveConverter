import net.cacheux.gpsc.*
import java.io.File
import java.nio.BufferUnderflowException

@Throws(CliException::class)
fun getContainersIndexFile(path: String): File {
    return with(File(path)) {
        if (isDirectory) {
            child(CONTAINERS_INDEX_FILENAME)
                ?: throw CliException("$CONTAINERS_INDEX_FILENAME not found in path $path")
        } else this
    }
}

@Throws(CliException::class)
fun File.entryList(entry: ContainersIndex.Entry, action: (Container.Entry, File) -> Unit) {
    parentFile.child(entry.uuid)?.let { subdir ->
        subdir.child(CONTAINER_PREFIX + entry.containerIndex)?.let { subcontainer ->
            try {
                val container = parseContainer(subcontainer)
                container.entries.forEach {
                    action(it, subdir)
                }
            } catch (e: BufferUnderflowException) {
                throw CliException("Error parsing file ${subcontainer.name}")
            }
        }
    } ?: throw CliException("File not found for UUID ${entry.uuid}")
}

fun File.child(name: String): File? {
    return listFiles()?.firstOrNull { file -> file.name == name }
}
