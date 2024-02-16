package cz.pavelhanzl.nbabrowser.features.playersearch.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.pavelhanzl.nbabrowser.data.player.PaginatorImpl
import cz.pavelhanzl.nbabrowser.data.player.PlayerRepository
import cz.pavelhanzl.nbabrowser.features.playerdetail.model.Player
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the UI state and business logic of the Player Search screen.
 *
 * This ViewModel uses a paginator to handle the loading and displaying of player search results,
 * and maintains the state related to player list.
 *
 * @property playerRepository Repository for fetching player data.
 */
class PlayerSearchViewModel(
    private val playerRepository: PlayerRepository,
) : ViewModel() {

    var state by mutableStateOf(PlayerSearchScreenState())

    val nameOfScreen = "Player Search"

    private val paginator = PaginatorImpl(
        initialCursor = state.nextCursor,
        maxResultsPerPage = state.perPage,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { startIndex ->
            playerRepository.getAllPlayersByPage(startIndex, state.perPage)
        },
        getNextCursor = { currentIndex, maxResults ->
            currentIndex + maxResults
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newNextCursor ->
            val mergedList = state.items + items

            state = state.copy(
                items = mergedList,
                nextCursor = newNextCursor,
                endReached = items.isEmpty()
            )
        }
    )

    init {
        loadNextPlayers()
    }

    fun loadNextPlayers() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }


}

/**
 * Represents the UI state for the Player Search screen.
 *
 * @property isLoading Indicates if the data is currently being loaded.
 * @property items The current list of loaded [Player] items.
 * @property error The error message if an error occurs during data loading.
 * @property endReached Indicates if the end of the data set has been reached.
 * @property nextCursor The cursor for the next set of results.
 * @property perPage The number of items per page in the search results.
 * @property resultExpected Indicates if search results are expected.
 */
data class PlayerSearchScreenState(
    val isLoading: Boolean = false,
    val items: List<Player> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val nextCursor: Int = 0,
    val perPage: Int = 35,
    val resultExpected: Boolean = false
)