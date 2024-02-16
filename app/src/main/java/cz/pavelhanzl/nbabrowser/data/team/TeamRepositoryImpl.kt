package cz.pavelhanzl.nbabrowser.data.team

import cz.pavelhanzl.nbabrowser.features.teamdetail.model.Team

/**
 * Implementation of [TeamRepository] for interacting with [TeamApiService].
 *
 * This repository provides methods for fetching detailed team data from a data source.
 *
 * @property dataSource Instance of TeamApiService for making API calls.
 */
class TeamRepositoryImpl(
    private val dataSource: TeamApiService
) : TeamRepository {

    /**
     * Fetches detailed information of a specific team by its ID.
     *
     * @param teamId The unique identifier of the team.
     * @return A [Team] object containing team details if found, or null otherwise.
     */
    override suspend fun getTeamById(teamId: Int): Team? {
        val response = dataSource.getTeamById(
            teamId = teamId
        )

        return if (response.isSuccessful && response.body() != null) {
            response.body()!!.data
        } else {
            null
        }
    }

}