import net.cacheux.gpsc.parseContainersIndex
import java.nio.BufferUnderflowException

@Throws(CliException::class)
fun list(path: String) {
    val index = getContainersIndexFile(path)

    try {
        parseContainersIndex(index).let { containers ->
            println("Game name: ${containers.gameName}")
            println("Game ID:   ${containers.gameId}")
            containers.entries.forEach { entry ->
                println("Entry ${entry.uuid} : ${entry.entryName1} / ${entry.entryName2}")
                index.entryList(entry) { it, _ ->
                    println("- ${it.uuid} : ${it.filename}")
                }
            }
        }
    } catch (e: BufferUnderflowException) {
        throw CliException("Error parsing ${index.name}")
    }
}
