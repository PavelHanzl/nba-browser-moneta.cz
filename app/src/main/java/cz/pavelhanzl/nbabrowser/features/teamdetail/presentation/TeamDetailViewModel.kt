package cz.pavelhanzl.nbabrowser.features.teamdetail.presentation

import androidx.lifecycle.ViewModel
import cz.pavelhanzl.nbabrowser.data.player.PlayerRepository
import cz.pavelhanzl.nbabrowser.data.team.TeamRepository

class TeamDetailViewModel(
    private val teamRepository: TeamRepository
) :ViewModel() {
    val nameOfScreen = "Team detail"

}