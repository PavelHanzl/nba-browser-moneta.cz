package cz.pavelhanzl.nbabrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import cz.pavelhanzl.nbabrowser.navigation.AppNavHost
import cz.pavelhanzl.nbabrowser.ui.theme.NBABrowserTheme

/**
 * Main activity for the NBA Browser app.
 *
 * This activity sets the content view for the application using Jetpack Compose. It initializes the theme
 * and sets up the navigation host, which is the entry point for the navigation graph of the app.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NBABrowserTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(navController = rememberNavController())
                }
            }
        }
    }
}