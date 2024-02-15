package cz.pavelhanzl.nbabrowser.data.paginator

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}