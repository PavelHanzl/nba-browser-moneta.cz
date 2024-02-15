package cz.pavelhanzl.nbabrowser.data.player

import android.database.AbstractCursor
import cz.pavelhanzl.nbabrowser.features.playerdetail.model.Player
import retrofit2.http.GET

interface PlayerRepository {

    suspend fun getAllPlayersByPage(nextCursor: Int, perPage:Int):Result<List<Player>>


    suspend fun getPlayerById(playerId: Int) : Player?
}