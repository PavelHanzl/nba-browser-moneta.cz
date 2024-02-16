package cz.pavelhanzl.nbabrowser.data.paginator

/**
 * Defines a basic pagination interface handling the loading and management of data.
 *
 * @param Key The type of the key used for pagination.
 * @param Item The type of items in the pagination.
 */
interface Paginator<Key, Item> {

    /**
     * Loads the next set of items asynchronously.
     *
     * Implementations of this function should handle the logic for loading the next chunk of data
     * based on the current state of the paginator, including handling any necessary API calls
     * or data transformations.
     */
    suspend fun loadNextItems()

    /**
     * Resets the paginator to its initial state.
     *
     * This function should be used to clear any existing data and reset any internal cursors or
     * pointers, making the paginator ready for a fresh sequence of data loading.
     */
    fun reset()
}