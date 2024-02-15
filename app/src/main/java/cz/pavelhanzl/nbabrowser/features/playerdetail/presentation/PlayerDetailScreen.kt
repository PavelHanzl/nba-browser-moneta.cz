package cz.pavelhanzl.nbabrowser.features.playerdetail.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cz.pavelhanzl.nbabrowser.features.playersearch.presentation.ScreenNav
import cz.pavelhanzl.nbabrowser.navigation.NavigationStrings
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlayerDetailScreen(
    navController: NavController,
    playerId: String?,
    viewModel: PlayerDetailViewModel = koinViewModel()
) {

    val state = viewModel.state
    val player = viewModel.state.player

    Scaffold() {

        if (player != null) {
            with(player) {
                Column {
                    Text(text = viewModel.nameOfScreen)
                    Text(text = id.toString())
                    Text(text = first_name)
                    Text(text = last_name)
                    Text(text = height)
                    Text(text = country)
                    Text(text = team.full_name)
                    Button(onClick = { navController.navigate("${NavigationStrings.TEAMDETAIL.toString()}/${player.team.id}") }) {
                        Text(text = "Detail ${team.name}")

                    }
                    ScreenNav(navController = navController)
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

    }

}
