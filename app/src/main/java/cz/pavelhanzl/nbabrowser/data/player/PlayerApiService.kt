package cz.pavelhanzl.nbabrowser.data.player

import cz.pavelhanzl.nbabrowser.features.playerdetail.model.PlayerDetailApiResponse
import cz.pavelhanzl.nbabrowser.features.playersearch.model.PlayerSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Service interface for the NBA player-related API calls.
 *
 * This interface defines methods for fetching player data using Retrofit.
 */
interface PlayerApiService {

    /**
     * Retrieves a paginated list of players.
     *
     * @param nextCursor The cursor(index) number for the next set of results.
     * @param perPage The number of items per page.
     * @return A [Response] wrapping [PlayerSearchResponse] containing player data.
     */
    @GET("players")
    suspend fun getAllPlayers(
        @Query("cursor") nextCursor: Int,
        @Query("per_page") perPage: Int
    ): Response<PlayerSearchResponse>


    /**
     * Retrieves detailed information of a specific player by ID.
     *
     * @param playerId The unique identifier of the player.
     * @return A [Response] wrapping [PlayerDetailApiResponse] containing detailed player information.
     */
    @GET("players/{id}")
    suspend fun getPlayerById(
        @Path("id") playerId: Int
    ): Response<PlayerDetailApiResponse>


}