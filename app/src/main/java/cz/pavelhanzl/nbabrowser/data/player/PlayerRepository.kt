package cz.pavelhanzl.nbabrowser.data.player

import cz.pavelhanzl.nbabrowser.features.playerdetail.model.Player
import retrofit2.http.GET

interface PlayerRepository {

    suspend fun searchPlayersByName(playerName: String):Result<List<Player>>


    suspend fun searchPlayerById(playerId: String) : Player?
}