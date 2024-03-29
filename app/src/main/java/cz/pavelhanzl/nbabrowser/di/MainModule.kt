package cz.pavelhanzl.nbabrowser.di

import androidx.lifecycle.SavedStateHandle
import cz.pavelhanzl.nbabrowser.data.player.PlayerApiService
import cz.pavelhanzl.nbabrowser.data.player.PlayerRepository
import cz.pavelhanzl.nbabrowser.data.player.PlayerRepositoryImpl
import cz.pavelhanzl.nbabrowser.data.team.TeamApiService
import cz.pavelhanzl.nbabrowser.data.team.TeamRepository
import cz.pavelhanzl.nbabrowser.data.team.TeamRepositoryImpl
import cz.pavelhanzl.nbabrowser.features.playerdetail.presentation.PlayerDetailViewModel
import cz.pavelhanzl.nbabrowser.features.playersearch.presentation.PlayerSearchViewModel
import cz.pavelhanzl.nbabrowser.features.teamdetail.presentation.TeamDetailViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Provides dependency injection configurations for the NBA Browser application.
 *
 * This module includes the setup of networking components like Retrofit and OkHttpClient,
 * service interfaces for accessing the NBA API, and repositories for handling data operations.
 * It also includes the setup of ViewModels for different features in the application.
 */
val appModule = module {

    // Creating an Interceptor to add an authorization header for https://docs.balldontlie.io/#authentication
    val authorizationInterceptor = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "6916e92a-5225-4770-92af-6f931edd6b51")
            .build()
        chain.proceed(newRequest)
    }

    // Creating an OkHttp client instance with interceptor
    single {
        OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }


    //Api instance
    single {
        Retrofit.Builder()
            .baseUrl("https://api.balldontlie.io/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get()) // Getting the OkHttp client instance
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

    viewModel { (savedStateHandle: SavedStateHandle) ->
        PlayerDetailViewModel(
            savedStateHandle = savedStateHandle,
            playerRepository = get()
        )
    }

    viewModel { (savedStateHandle: SavedStateHandle) ->
        TeamDetailViewModel(
            savedStateHandle = savedStateHandle,
            teamRepository = get()
        )
    }


}