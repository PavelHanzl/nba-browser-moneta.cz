package cz.pavelhanzl.nbabrowser.features.teamdetail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.pavelhanzl.nbabrowser.data.player.PlayerRepository
import cz.pavelhanzl.nbabrowser.data.team.TeamRepository
import cz.pavelhanzl.nbabrowser.features.playerdetail.presentation.PlayerDetailScreenState
import cz.pavelhanzl.nbabrowser.features.teamdetail.model.Team
import kotlinx.coroutines.launch

class TeamDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val teamRepository: TeamRepository
) :ViewModel() {

    var state by mutableStateOf(TeamDetailScreenState())

    init {

        //on init loads team details using team id in saved state
        val teamId: Int? = savedStateHandle["teamId"]
        loadTeamDetail(teamId!!) //cannot be null, because id is from search screen where the player was loaded from

    }

    private fun loadTeamDetail(teamId: Int) {
        viewModelScope.launch {

            state = state.copy(
                team = teamRepository.getTeamById(teamId)
            )

        }
    }

}

data class TeamDetailScreenState(
    val team: Team? = null
)