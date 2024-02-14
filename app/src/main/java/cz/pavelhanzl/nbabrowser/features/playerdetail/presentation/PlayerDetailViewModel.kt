package cz.pavelhanzl.nbabrowser.features.playerdetail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.pavelhanzl.nbabrowser.data.player.PlayerRepository
import kotlinx.coroutines.launch

class PlayerDetailViewModel(
    private val playerRepository: PlayerRepository,
) : ViewModel() {
    val nameOfScreen = "Player Detail"
    var playerLastName by mutableStateOf("")
    init {
        viewModelScope.launch {  playerLastName =
            playerRepository.searchPlayerById("257")?.last_name.toString()
        }
    }




}