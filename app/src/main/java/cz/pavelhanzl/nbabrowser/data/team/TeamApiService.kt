package cz.pavelhanzl.nbabrowser.data.team

import cz.pavelhanzl.nbabrowser.features.playerdetail.model.PlayerDetailApiResponse
import cz.pavelhanzl.nbabrowser.features.teamdetail.model.TeamDetailApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamApiService {

    @GET("teams/{id}")
    suspend fun getTeamById(
        @Path("id") teamId: Int
    ): Response<TeamDetailApiResponse>

}