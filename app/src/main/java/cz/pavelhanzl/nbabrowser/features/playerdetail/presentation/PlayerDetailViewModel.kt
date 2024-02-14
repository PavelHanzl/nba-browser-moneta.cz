package cz.pavelhanzl.nbabrowser.features.playerdetail.presentation

import androidx.lifecycle.ViewModel
import cz.pavelhanzl.nbabrowser.data.player.PlayerRepository

class PlayerDetailViewModel(
    private val playerRepository: PlayerRepository,
) : ViewModel() {
    val nameOfScreen = "Player Detail"


}