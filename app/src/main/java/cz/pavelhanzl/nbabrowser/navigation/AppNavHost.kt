package cz.pavelhanzl.nbabrowser.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import cz.pavelhanzl.nbabrowser.features.playerdetail.presentation.PlayerDetailScreen
import cz.pavelhanzl.nbabrowser.features.playersearch.presentation.PlayerSearchScreen
import cz.pavelhanzl.nbabrowser.features.teamdetail.presentation.TeamDetailScreen


/**
 * Sets up the navigation graph for the application using Compose's NavHost.
 *
 * This function defines the navigation routes for different screens within the app,
 * including player search(list), player detail, and team detail screens.
 *
 * @param modifier A [Modifier] used to adjust the layout or to add behavior to this NavHost.
 * @param navController The [NavHostController] that keeps track of the navigation stack.
 * @param startDestination The start destination route in the navigation graph.
 */
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationStrings.PLAYERSEARCH.toString()
) {
    NavHost(navController = navController, startDestination = startDestination) {

        //Player Search screen
        composable(
            NavigationStrings.PLAYERSEARCH.toString()
        ) {
            PlayerSearchScreen(navController)
        }

        //Player Detail screen with id as a string parameter
        composable(
            "${NavigationStrings.PLAYERDETAIL}/{playerId}",
            arguments = listOf(navArgument("playerId") {
                type = NavType.IntType
            })
        ) {

            PlayerDetailScreen(
                navController
            )
        }

        //Team Detail screen with id as a string parameter
        composable(
            "${NavigationStrings.TEAMDETAIL}/{teamId}",
            arguments = listOf(navArgument("teamId") {
                type = NavType.IntType
            })
        ) {

            TeamDetailScreen(
                navController
            )
        }

    }
}