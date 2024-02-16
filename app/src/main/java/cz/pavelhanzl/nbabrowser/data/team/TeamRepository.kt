package cz.pavelhanzl.nbabrowser.data.team

import cz.pavelhanzl.nbabrowser.features.teamdetail.model.Team

/**
 * Repository interface for handling team data operations.
 *
 * This interface defines methods for fetching team-related data from a data source.
 */
interface TeamRepository {

    /**
     * Retrieves detailed information of a specific team by its ID.
     *
     * @param teamId The unique identifier of the team.
     * @return A [Team] object containing team details if found, or null otherwise.
     */
    suspend fun getTeamById(teamId: Int): Team?

}