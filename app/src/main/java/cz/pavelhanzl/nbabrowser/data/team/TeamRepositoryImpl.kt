package cz.pavelhanzl.nbabrowser.data.team

import cz.pavelhanzl.nbabrowser.features.teamdetail.model.Team

class TeamRepositoryImpl(
    private val dataSource: TeamApiService
) : TeamRepository {
    override suspend fun getTeamById(teamId: Int): Team? {
        val response = dataSource.getTeamById(
            teamId=teamId
        )

        return if(response.isSuccessful && response.body() != null){
            response.body()!!.data
        } else {
            null
        }
    }

}