package cz.pavelhanzl.nbabrowser.features.teamdetail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.pavelhanzl.nbabrowser.data.team.TeamRepository
import cz.pavelhanzl.nbabrowser.features.teamdetail.model.Team
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the UI state and business logic of the Team Detail screen.
 *
 * This ViewModel handles the loading of team details from a repository
 * and maintains the state for the Team Detail screen.
 *
 * @param savedStateHandle Handle for saving and retrieving data with the ViewModel's state.
 * @param teamRepository Repository for fetching team data.
 */
class TeamDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val teamRepository: TeamRepository
) : ViewModel() {

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

/**
 * Represents the UI state for the Team Detail screen.
 *
 * @property team The [Team] object containing detailed information about the team.
 */
data class TeamDetailScreenState(
    val team: Team? = null
)