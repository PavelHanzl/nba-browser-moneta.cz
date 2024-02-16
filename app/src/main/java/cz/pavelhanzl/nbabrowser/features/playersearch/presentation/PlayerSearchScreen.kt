package cz.pavelhanzl.nbabrowser.features.playersearch.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
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
import cz.pavelhanzl.nbabrowser.temp.images.PlayerPhotoThumbnails
import cz.pavelhanzl.nbabrowser.temp.images.TeamLogo
import cz.pavelhanzl.nbabrowser.utils.ktx.toFeetAndInches
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun PlayerSearchScreen(
    navController: NavController, viewModel: PlayerSearchViewModel = koinViewModel()
) {
    val state = viewModel.state
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            NbaTopAppBar(
                navController = navController,
                scrollBehavior = scrollBehavior,
                icon = { R.drawable.icon_basketball_ball },
                title = { "NBA Players" },
                backButtonEnabled = { false }
            )

        }) {
        if (state.items.isNotEmpty()) {
            Column(modifier = Modifier.padding(it)) {
                Spacer(modifier = Modifier.height(10.dp))
                PlayerList(viewModel = viewModel, navController = navController, state = state)
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
fun NbaTopAppBar(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    icon: () -> Int,
    title: () -> String,
    backButtonEnabled: () -> Boolean = { true }
) {
    TopAppBar(

        navigationIcon = {

            IconButton(
                onClick = { navController.navigateUp() },
                enabled = backButtonEnabled()
            ) {
                // Ball icon
                GlideImage(
                    modifier = Modifier.size(30.dp),
                    model = icon(),
                    contentDescription = "Ball icon",
                    contentScale = ContentScale.Fit,
                    transition = CrossFade
                )
            }
        },

        title = {
            Text(
                text = title(),
                fontWeight = FontWeight.ExtraBold
            )
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun PlayerList(
    modifier: Modifier = Modifier,
    viewModel: PlayerSearchViewModel,
    navController: NavController,
    state: PlayerSearchScreenState
) {


    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        items(state.items.size) { i ->
            PlayerItem(state.items[i], onClick = {
                navController.navigate("${NavigationStrings.PLAYERDETAIL}/${state.items[i].id}")
            })

            //loads next page of players
            if (i >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                viewModel.loadNextPlayers()
            }
        }

        //shows loading indicator if loading of next page is in progress
        item {
            if (state.isLoading) {
                Row(
                    modifier = Modifier
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
    player: Player, onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Row(
            modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically
        ) {


            Box(
                modifier = Modifier.size(100.dp)
            ) {

                // temporary solution - will be replaced by real images from real api
                val randomPlayerImage = remember { PlayerPhotoThumbnails.entries.toTypedArray().random().url }

                // Player image preview
                GlideImage(
                    modifier = Modifier
                        .size(90.dp)
                        //.border(3.dp, Color.LightGray, CircleShape)
                        .clip(CircleShape)
                        .align(Alignment.Center),
                    model = randomPlayerImage,
                    contentDescription = "Photo of player",
                    contentScale = ContentScale.FillHeight,
                    loading = placeholder(R.drawable.player_loading_placeholder),
                    failure = placeholder(R.drawable.player_failure_placeholder),
                    transition = CrossFade
                )

                // temporary solution - will be replaced by real images from real api
                val randomTeamLogo = remember { TeamLogo.entries.toTypedArray().random().url }

                // Player team logo
                GlideImage(
                    modifier = Modifier
                        .size(45.dp)
                        .align(Alignment.BottomEnd) // zarovná logo vpravo dole
                        .padding(4.dp),
                    model = randomTeamLogo,
                    contentDescription = "Team logo of player",
                    contentScale = ContentScale.Fit,
                    loading = placeholder(R.drawable.general_loader_placeholder),
                    failure = placeholder(R.drawable.icon_transparent_placeholder),
                    transition = CrossFade
                )

            }

            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {


                Row {

                    // Player id
                    Text(
                        text = "${player.id.toString()} |" ?: "",
                        color = Color.LightGray,
                        fontWeight = FontWeight.Black,
                        fontSize = 18.sp
                    )
                    // Player name
                    Text(
                        modifier = Modifier.padding(start = 6.dp),
                        text = "${player.first_name ?: ""} ${player.last_name ?: ""} " ?: "",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )


                }


                Row {

                    // Court icon
                    GlideImage(
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.CenterVertically),
                        model = R.drawable.icon_court,
                        contentDescription = "Court icon",
                        contentScale = ContentScale.Fit,
                        transition = CrossFade
                    )

                    // Position
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 8.dp),
                        text = player.position ?: "",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    // Height icon
                    GlideImage(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(20.dp)
                            .align(Alignment.CenterVertically),
                        model = R.drawable.icon_measurment,
                        contentDescription = "Court icon",
                        contentScale = ContentScale.Fit,
                        transition = CrossFade
                    )

                    // Height
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 6.dp),
                        text = player.height.toFeetAndInches() ?: "",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                }

                Row {

                    // Team icon
                    GlideImage(
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.CenterVertically),
                        model = R.drawable.icon_team,
                        contentDescription = "Team icon",
                        contentScale = ContentScale.Fit,
                        transition = CrossFade
                    )

                    // Team full name
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 8.dp),
                        text = player.team.full_name ?: "",
                        fontSize = 16.sp,
                        overflow = TextOverflow.Ellipsis,
                        fontStyle = FontStyle.Italic,
                        maxLines = 1,

                        )
                }


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