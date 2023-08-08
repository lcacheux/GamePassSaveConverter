package net.cacheux.gpsc

/**
 * Data from containers.index files.
 */
data class ContainersIndex(
    val gameName: String,
    val gameId: String,
    val entries: List<Entry>,
) {
    data class Entry(
        val entryName1: String,
        val entryName2: String, // entryName2 is unused or copy of entryName1 in most cases
        val entryId: String,
        val containerIndex: Int, // Extension of the file container.*
        val uuid: String,
    )
}
