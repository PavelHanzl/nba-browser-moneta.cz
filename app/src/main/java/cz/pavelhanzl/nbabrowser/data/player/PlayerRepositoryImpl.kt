package cz.pavelhanzl.nbabrowser.data.player

import android.util.Log
import cz.pavelhanzl.nbabrowser.features.playerdetail.model.Player

class PlayerRepositoryImpl(
    private val dataSource: PlayerApiService
) :PlayerRepository {
    override suspend fun getAllPlayersByPage(nextCursor: Int, perPage: Int): Result<List<Player>> {

        val response = dataSource.getAllPlayers(
            nextCursor=nextCursor,
            perPage = perPage)

        return if(response.isSuccessful && response.body() != null){
            Result.success(response.body()?.data ?: emptyList())
        } else {
            Result.success(emptyList())
        }
    }

    override suspend fun getPlayerById(playerId: String): Player? {

        val response = dataSource.getPlayerById(playerId)

        return if(response.isSuccessful && response.body() != null){
            response.body()!!
        } else {
            null
        }
    }

}