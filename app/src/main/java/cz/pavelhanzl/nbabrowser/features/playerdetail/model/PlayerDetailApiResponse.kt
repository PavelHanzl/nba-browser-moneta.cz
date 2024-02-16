package cz.pavelhanzl.nbabrowser.features.playerdetail.model

/**
 * Data class representing the response from the API for a player detail request.
 *
 * This class encapsulates the player detail data returned from the API.
 *
 * @property data The [Player] object containing detailed information about a specific player.
 */
data class PlayerDetailApiResponse(
    val data: Player
)