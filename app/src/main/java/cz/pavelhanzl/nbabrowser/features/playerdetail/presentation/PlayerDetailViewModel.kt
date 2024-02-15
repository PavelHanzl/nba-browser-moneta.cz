package cz.pavelhanzl.nbabrowser.features.playerdetail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.pavelhanzl.nbabrowser.data.player.PlayerRepository
import cz.pavelhanzl.nbabrowser.features.playerdetail.model.Player
import cz.pavelhanzl.nbabrowser.features.teamdetail.model.Team
import kotlinx.coroutines.launch
import java.text.FieldPosition

class PlayerDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val playerRepository: PlayerRepository
) : ViewModel() {
    val nameOfScreen = "Player Detail"

    var state by mutableStateOf(PlayerDetailScreenState())

    init {

        //on init loads player details using player id in saved state
        val playerId: Int? = savedStateHandle["playerId"]
        loadPlayerDetail(playerId!!) //cannot be null, because id is from search screen where the player was loaded from

    }

    private fun loadPlayerDetail(playerId: Int) {
        viewModelScope.launch {

            state = state.copy(
                player = playerRepository.getPlayerById(playerId)
            )

        }
    }

}

data class PlayerDetailScreenState(
    val player: Player? = null
    )