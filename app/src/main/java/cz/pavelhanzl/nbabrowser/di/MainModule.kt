package cz.pavelhanzl.nbabrowser.di

import cz.pavelhanzl.nbabrowser.data.player.PlayerApiService
import cz.pavelhanzl.nbabrowser.data.team.TeamApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    //Api instance
    single {
        Retrofit.Builder()
            .baseUrl("https://www.balldontlie.io/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Data
    factory { get<Retrofit>().create(PlayerApiService::class.java) }
    factory { get<Retrofit>().create(TeamApiService::class.java) }

}