package cz.pavelhanzl.nbabrowser.data.player

import cz.pavelhanzl.nbabrowser.features.playerdetail.model.Player

class PlayerRepositoryImpl(
    private val dataSource: PlayerApiService
) :PlayerRepository {
    override suspend fun searchPlayersByName(playerName: String): Result<List<Player>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchPlayerById(playerId: String): Player? {
        val response = dataSource.findPlayerById(playerId)

        if(response.isSuccessful && response.body() != null){
            return response.body()!!
        } else {
            return null
        }


    }

}