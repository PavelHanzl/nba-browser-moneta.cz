package cz.pavelhanzl.nbabrowser.features.playersearch.presentation

import androidx.lifecycle.ViewModel
import cz.pavelhanzl.nbabrowser.data.player.PlayerRepository

class PlayerSearchViewModel(
    private val playerRepository: PlayerRepository,
) : ViewModel() {

    val nameOfScreen = "Player Search"


}