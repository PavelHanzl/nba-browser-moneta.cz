package cz.pavelhanzl.nbabrowser.features.playersearch.model

import cz.pavelhanzl.nbabrowser.features.playerdetail.model.Player

/**
 * Data class representing the response from the API for a player search request.
 *
 * @property data A list of [Player] objects representing the search results, or null if no data is available.
 * @property meta Metadata about the search results, including pagination information.
 */
data class PlayerSearchResponse(
    val data: List<Player>?,
    val meta: Meta
)


/**
 * Represents metadata associated with a player search response.
 *
 * This class holds information about pagination of the results.
 *
 * @property next_cursor The cursor indicating the position for the next set of results.
 * @property per_page The number of items per page in the search results.
 */
data class Meta(
    val next_cursor: Int,
    val per_page: Int
)