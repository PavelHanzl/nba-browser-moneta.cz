package cz.pavelhanzl.nbabrowser.features.playerdetail.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import cz.pavelhanzl.nbabrowser.features.playersearch.presentation.ScreenNav
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayerDetailScreen(
    navController: NavController,
    playerId: String?,
    viewModel: PlayerDetailViewModel = koinViewModel()
) {
    Column {
        Text(text = viewModel.nameOfScreen)
        Text(text = viewModel.playerLastName)
        ScreenNav(navController = navController)
    }


}
