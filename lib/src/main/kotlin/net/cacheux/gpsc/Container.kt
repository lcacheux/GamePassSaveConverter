package net.cacheux.gpsc

/**
 * Data from container.* files.
 */
data class Container(
    val entries: List<Entry>,
) {
    data class Entry(
        val filename: String,
        val uuid: String,
    )
}
