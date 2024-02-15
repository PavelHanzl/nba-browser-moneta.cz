package cz.pavelhanzl.nbabrowser.features.teamdetail.presentation

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
fun TeamDetailScreen(
    navController: NavController,
    viewModel: TeamDetailViewModel = koinViewModel()
) {


    val team = viewModel.state.team

    Scaffold() {

        if (team != null) {
            with(team) {
                Column {
                    Text(text = viewModel.nameOfScreen)
                    Text(text = id.toString())
                    Text(text = full_name)
                    Text(text = city)
                    Button(onClick = { navController.popBackStack()}) {
                        Text(text = "zpět")

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
