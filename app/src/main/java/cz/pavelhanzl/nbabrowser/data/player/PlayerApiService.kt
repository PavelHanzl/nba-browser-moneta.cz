package cz.pavelhanzl.nbabrowser.data.player

import cz.pavelhanzl.nbabrowser.features.playerdetail.model.Player
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayerApiService {
    @GET("players/{id}")
    suspend fun findPlayerById(
        @Path("id") playerId: String
    ): Response<Player>


}