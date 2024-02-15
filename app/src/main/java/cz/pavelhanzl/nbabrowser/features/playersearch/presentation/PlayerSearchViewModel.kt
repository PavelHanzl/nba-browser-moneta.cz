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
            playerRepository.getAllPlayersByPage( startIndex, state.perPage)
        },
        getNextCursor = { currentIndex, maxResults ->
            currentIndex+maxResults
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


data class PlayerSearchScreenState(
    val isLoading: Boolean = false,
    val items: List<Player> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val nextCursor: Int = 0,
    val perPage: Int = 35,
    val resultExpected: Boolean = false
)