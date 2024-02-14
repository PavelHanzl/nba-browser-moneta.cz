package cz.pavelhanzl.nbabrowser.features.playersearch.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import cz.pavelhanzl.nbabrowser.navigation.NavigationStrings
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayerSearchScreen(
    navController: NavController,
    viewModel: PlayerSearchViewModel = koinViewModel()
) {
    Column {
        Text(text = viewModel.nameOfScreen)
        ScreenNav(navController = navController)
    }


}

@Composable
fun ScreenNav( navController: NavController){
    Column {
        Button(onClick = { navController.navigate(NavigationStrings.PLAYERSEARCH.toString())}) {
            Text(text = "Naviguj na player search")
        }

        Button(onClick = { navController.navigate(NavigationStrings.PLAYERDETAIL.toString()+"/123")}) {
            Text(text = "Naviguj na player detail")
        }

        Button(onClick = { navController.navigate(NavigationStrings.TEAMDETAIL.toString()+"/123")}) {
            Text(text = "Naviguj na team detail")
        }
    }
}