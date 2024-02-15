package cz.pavelhanzl.nbabrowser.data.team

import cz.pavelhanzl.nbabrowser.features.playerdetail.model.Player
import cz.pavelhanzl.nbabrowser.features.teamdetail.model.Team

interface TeamRepository {

    suspend fun getTeamById(teamId: Int) : Team?

}