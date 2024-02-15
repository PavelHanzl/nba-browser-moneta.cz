package cz.pavelhanzl.nbabrowser.data.player

import cz.pavelhanzl.nbabrowser.features.playerdetail.model.PlayerDetailApiResponse
import cz.pavelhanzl.nbabrowser.features.playersearch.model.PlayerSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlayerApiService {

    @GET("players")
    suspend fun getAllPlayers(
        @Query("cursor") nextCursor: Int,
        @Query("per_page") perPage: Int
    ): Response<PlayerSearchResponse>


    @GET("players/{id}")
    suspend fun getPlayerById(
        @Path("id") playerId: Int
    ): Response<PlayerDetailApiResponse>


}