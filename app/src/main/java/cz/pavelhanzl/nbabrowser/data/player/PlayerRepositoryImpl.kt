package cz.pavelhanzl.nbabrowser.data.player

import cz.pavelhanzl.nbabrowser.features.playerdetail.model.Player

/**
 * Implementation of [PlayerRepository] for interacting with [PlayerApiService].
 *
 * This repository is responsible for making network requests related to player data
 * and handling the responses.
 *
 * @property dataSource An instance of PlayerApiService for making API calls.
 */
class PlayerRepositoryImpl(
    private val dataSource: PlayerApiService
) : PlayerRepository {

    /**
     * Fetches a list of players from the API based on pagination parameters.
     *
     * @param nextCursor The cursor(index) for the next set of results.
     * @param perPage The number of items to be returned per page.
     * @return A [Result] containing a list of [Player] objects or an empty list.
     */
    override suspend fun getAllPlayersByPage(nextCursor: Int, perPage: Int): Result<List<Player>> {

        val response = dataSource.getAllPlayers(
            nextCursor = nextCursor,
            perPage = perPage
        )

        return if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()?.data ?: emptyList())
        } else {
            Result.success(emptyList())
        }
    }

    /**
     * Fetches details of a specific player by their ID.
     *
     * @param playerId The unique identifier of the player.
     * @return The details of the player as a [Player] object or null if not found.
     */
    override suspend fun getPlayerById(playerId: Int): Player? {

        val response = dataSource.getPlayerById(
            playerId = playerId
        )

        return if (response.isSuccessful && response.body() != null) {
            response.body()!!.data
        } else {
            null
        }
    }

}