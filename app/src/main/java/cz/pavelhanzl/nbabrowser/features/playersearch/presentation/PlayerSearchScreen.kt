package cz.pavelhanzl.nbabrowser.features.playersearch.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import cz.pavelhanzl.nbabrowser.temp.images.PlayerPhoto
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlayerSearchScreen(
    navController: NavController,
    viewModel: PlayerSearchViewModel = koinViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = viewModel.nameOfScreen) })
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.height(10.dp))
            PlayerList(viewModel = viewModel, navController = navController)
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
    Card(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(

        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // temporary solution - will be replaced by real images from real api
            val randomPlayerImage = PlayerPhoto.entries.toTypedArray().random().url

            // Player image preview
            GlideImage(
                modifier = Modifier
                    .size(100.dp)
                    .border(3.dp, Color.LightGray, CircleShape)
                    .clip(CircleShape),
                model = randomPlayerImage,
                contentDescription = "Photo of player",
                contentScale = ContentScale.FillHeight,
                loading = placeholder(R.drawable.ic_launcher_background),
                failure = placeholder(R.drawable.ic_launcher_foreground),
                transition = CrossFade
            )
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

                // Position
                Text(
                    text = player.height,
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                // Team full name
                Text(
                    text = player.team.full_name,
                    fontSize = 14.sp,
                    overflow = TextOverflow.Ellipsis,
                    fontStyle = FontStyle.Italic

                )

            }
        }
        HorizontalDivider()
    }
}


//@Composable
//fun ScreenNav(navController: NavController) {
//    Column {
//        Button(onClick = { navController.navigate(NavigationStrings.PLAYERSEARCH.toString()) }) {
//            Text(text = "Naviguj na player search")
//        }
//
//        Button(onClick = { navController.navigate(NavigationStrings.PLAYERDETAIL.toString() + "/123") }) {
//            Text(text = "Naviguj na player detail")
//        }
//
//        Button(onClick = { navController.navigate(NavigationStrings.TEAMDETAIL.toString() + "/123") }) {
//            Text(text = "Naviguj na team detail")
//        }
//    }
//}