import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlin.system.exitProcess

enum class Commands {
    LIST,
    CONVERT
}

fun main(args: Array<String>) {
    exitProcess(executeMain(args))
}

fun executeMain(args: Array<String>): Int {
    val parser = ArgParser("gpsc")
    val command by parser.argument(ArgType.Choice<Commands>(), description = "Command")
    val path by parser.option(ArgType.String, shortName = "p", description = "Path").default(".")
    val destination by parser.option(ArgType.String, shortName = "d", description = "Destination").default("./output/")

    parser.parse(args)

    try {
        when (command) {
            Commands.LIST -> list(path)
            Commands.CONVERT -> convert(path, destination)
        }
    } catch (e: CliException) {
        System.err.println("Error: ${e.message}")
        return 1
    }

    return 0
}
