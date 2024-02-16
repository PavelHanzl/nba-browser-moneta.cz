package cz.pavelhanzl.nbabrowser.data.player

import cz.pavelhanzl.nbabrowser.features.playerdetail.model.Player

/**
 * Repository interface for handling player data operations.
 *
 * This interface defines the contract for fetching player-related data from a data source.
 */
interface PlayerRepository {

    /**
     * Retrieves a list of players based on pagination parameters.
     *
     * @param nextCursor The cursor(index) indicating the current position in the dataset.
     * @param perPage The maximum number of players to be retrieved per request.
     * @return A [Result] wrapping a list of [Player] objects.
     */
    suspend fun getAllPlayersByPage(nextCursor: Int, perPage: Int): Result<List<Player>>

    /**
     * Retrieves a specific player by their unique ID.
     *
     * @param playerId The unique identifier of the player.
     * @return A [Player] object if found, or null otherwise.
     */
    suspend fun getPlayerById(playerId: Int): Player?
}