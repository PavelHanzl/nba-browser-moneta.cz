package cz.pavelhanzl.nbabrowser.data.team

import cz.pavelhanzl.nbabrowser.features.teamdetail.model.TeamDetailApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Service interface for making API calls related to NBA teams.
 *
 * This interface defines methods for fetching team data using Retrofit.
 */
interface TeamApiService {

    /**
     * Fetches detailed information of a specific team by its ID.
     *
     * @param teamId The unique identifier of the team.
     * @return A [Response] containing [TeamDetailApiResponse] with team details.
     */
    @GET("teams/{id}")
    suspend fun getTeamById(
        @Path("id") teamId: Int
    ): Response<TeamDetailApiResponse>

}