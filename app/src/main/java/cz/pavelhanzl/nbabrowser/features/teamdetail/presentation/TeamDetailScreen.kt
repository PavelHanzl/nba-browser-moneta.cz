package cz.pavelhanzl.nbabrowser.features.teamdetail.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cz.pavelhanzl.nbabrowser.R
import cz.pavelhanzl.nbabrowser.features.playersearch.presentation.NbaTopAppBar
import cz.pavelhanzl.nbabrowser.navigation.NavigationStrings
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TeamDetailScreen(
    navController: NavController,
    viewModel: TeamDetailViewModel = koinViewModel()
) {


    val team = viewModel.state.team

    Scaffold(
        topBar = {
            NbaTopAppBar(
                navController = navController,
                icon = { R.drawable.icon_back },
                title = { "Team Detail" },
                backButtonEnabled = { true }
            )
        }
    ) {

        if (team != null) {
            with(team) {
                Column(
                    Modifier
                        .padding(it)
                ) {
                    Text(text = viewModel.nameOfScreen)
                    Text(text = id.toString())
                    Text(text = full_name)
                    Text(text = city)
                    Button(onClick = { navController.popBackStack()}) {
                        Text(text = "zpět")

                    }
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
