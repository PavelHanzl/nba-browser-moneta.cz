package cz.pavelhanzl.nbabrowser.data.team

import cz.pavelhanzl.nbabrowser.features.teamdetail.model.Team

class TeamRepositoryImpl(
    private val dataSource: TeamApiService
) : TeamRepository {
    override suspend fun searchTeamById(teamId: String): Team {
        TODO("Not yet implemented")
    }

}