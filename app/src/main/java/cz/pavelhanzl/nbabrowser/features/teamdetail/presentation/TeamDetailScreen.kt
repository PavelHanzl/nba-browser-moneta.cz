package cz.pavelhanzl.nbabrowser.features.teamdetail.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import cz.pavelhanzl.nbabrowser.features.playersearch.presentation.ScreenNav
import org.koin.androidx.compose.koinViewModel

@Composable
fun TeamDetailScreen(
    navController: NavController,
    teamId: String?,
    viewModel: TeamDetailViewModel = koinViewModel()
) {
    Column {
        Text(text = viewModel.nameOfScreen)
        ScreenNav(navController = navController)
    }

}