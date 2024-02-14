package cz.pavelhanzl.nbabrowser.di

import cz.pavelhanzl.nbabrowser.data.player.PlayerApiService
import cz.pavelhanzl.nbabrowser.data.player.PlayerRepository
import cz.pavelhanzl.nbabrowser.data.player.PlayerRepositoryImpl
import cz.pavelhanzl.nbabrowser.data.team.TeamApiService
import cz.pavelhanzl.nbabrowser.data.team.TeamRepository
import cz.pavelhanzl.nbabrowser.data.team.TeamRepositoryImpl
import cz.pavelhanzl.nbabrowser.features.playerdetail.presentation.PlayerDetailViewModel
import cz.pavelhanzl.nbabrowser.features.playersearch.presentation.PlayerSearchViewModel
import cz.pavelhanzl.nbabrowser.features.teamdetail.presentation.TeamDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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



    //Domain
    single<PlayerRepository> {
        PlayerRepositoryImpl(
            dataSource = get()
        )
    }

    single<TeamRepository> {
        TeamRepositoryImpl(
            dataSource = get()
        )
    }


    //presentation
    viewModel {
        PlayerSearchViewModel(
            playerRepository = get()
        )
    }

    viewModel {
        PlayerDetailViewModel(
            playerRepository = get()
        )
    }

    viewModel {
        TeamDetailViewModel(
            teamRepository = get()
        )
    }



}