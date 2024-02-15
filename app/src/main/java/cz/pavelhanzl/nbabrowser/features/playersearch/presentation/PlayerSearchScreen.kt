package cz.pavelhanzl.nbabrowser.features.playersearch.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import cz.pavelhanzl.nbabrowser.R
import cz.pavelhanzl.nbabrowser.features.playerdetail.model.Player
import cz.pavelhanzl.nbabrowser.navigation.NavigationStrings
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlayerSearchScreen(
    navController: NavController,
    viewModel: PlayerSearchViewModel = koinViewModel()
) {

    Scaffold(
        topBar = {

        }
    ) {
        Column {
            Text(text = viewModel.nameOfScreen)
            ScreenNav(navController = navController)
            Button(onClick = { viewModel.loadNextPlayers() }) {
                Text(text = "načti hráče")

            }
            PlayerList(
                viewModel = viewModel,
                navController =navController
            )
        }
    }
}

@Composable
fun PlayerList(
    modifier: Modifier = Modifier,
    viewModel: PlayerSearchViewModel,
    navController: NavController
) {
    val state = viewModel.state

    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        items(state.items.size) { i ->
            PlayerItem(state.items[i],
                onClick = {
                    navController.navigate("${NavigationStrings.PLAYERDETAIL}/${state.items[i].id}")
                })

            //loads next page of books
            if (i >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                viewModel.loadNextPlayers()
            }
        }

        //shows loading indicator if loading of next page is in progress
        item {
            if (state.isLoading) {
                Row(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlayerItem(
    player: Player,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
//        // Player image preview
//        GlideImage(
//            modifier = Modifier
//                .size(100.dp),
//            model = book.volumeInfo.imageLinks?.smallThumbnailToHttps(),
//            contentDescription = stringResource(R.string.book_image_thumbnail),
//            loading = placeholder(R.drawable.loading_placeholder),
//            failure = placeholder(R.drawable.notfound_placeholder),
//            transition = CrossFade
//        )
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
        ) {

            // Player id
            Text(
                text = player.id.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )


            // Player name
            Text(
                text = "${player.first_name} ${player.last_name} ",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            // Position
            Text(
                text = player.position,
                fontSize = 14.sp,
                color = Color.Gray
            )

            // Preview of book description
            Text(
                text = player.team.full_name,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                fontStyle = FontStyle.Italic

            )

        }
    }
}


@Composable
fun ScreenNav(navController: NavController) {
    Column {
        Button(onClick = { navController.navigate(NavigationStrings.PLAYERSEARCH.toString()) }) {
            Text(text = "Naviguj na player search")
        }

        Button(onClick = { navController.navigate(NavigationStrings.PLAYERDETAIL.toString() + "/123") }) {
            Text(text = "Naviguj na player detail")
        }

        Button(onClick = { navController.navigate(NavigationStrings.TEAMDETAIL.toString() + "/123") }) {
            Text(text = "Naviguj na team detail")
        }
    }
}