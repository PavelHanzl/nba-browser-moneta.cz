package cz.pavelhanzl.nbabrowser.features.playerdetail.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import cz.pavelhanzl.nbabrowser.features.playersearch.presentation.NbaTopAppBar
import cz.pavelhanzl.nbabrowser.navigation.NavigationStrings
import cz.pavelhanzl.nbabrowser.temp.images.PlayerPhoto
import cz.pavelhanzl.nbabrowser.temp.images.TeamLogo
import cz.pavelhanzl.nbabrowser.utils.ktx.toFeetAndInches
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlayerDetailScreen(
    navController: NavController, viewModel: PlayerDetailViewModel = koinViewModel()
) {

    val player = viewModel.state.player

    Scaffold(topBar = {
        NbaTopAppBar(navController = navController,
            icon = { R.drawable.icon_back },
            title = { "Player Detail" },
            backButtonEnabled = { true })
    }) {

        if (player != null) {

            Column(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                PlayerDetailHeader(navController, player)
                PlayerDetailNameAndId(player)
                PlayerDetailStats(player)
                PlayerDetailTeamInfo(navController, player)

            }

        } else {
            DetailScreenLoading()
        }

    }

}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
private fun PlayerDetailHeader(
    navController: NavController, player: Player
) {
    Box(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .background(Color(0xFFdadada))
    ) {

        // temporary solution - will be replaced by real images from real api
        val randomPlayerImage = remember { PlayerPhoto.entries.toTypedArray().random().url }

        // Player image preview
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .align(Alignment.BottomCenter),
            model = randomPlayerImage,
            contentDescription = "Photo of player",
            contentScale = ContentScale.Fit,
            loading = placeholder(R.drawable.player_loading_transparent_placeholder),
            failure = placeholder(R.drawable.player_failure_transparent_placeholder),
            transition = CrossFade
        )

        // temporary solution - will be replaced by real images from real api
        val randomTeamLogo = remember { TeamLogo.entries.toTypedArray().random().url }


        // Player team logo
        GlideImage(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomEnd) // zarovná logo vpravo dole
                .padding(20.dp)
                .clickable(onClick = { navController.navigate("${NavigationStrings.TEAMDETAIL.toString()}/${player.team.id}") }),
            model = randomTeamLogo,
            contentDescription = "Team logo of player",
            contentScale = ContentScale.Fit,
            loading = placeholder(R.drawable.general_loader_placeholder),
            failure = placeholder(R.drawable.icon_transparent_placeholder),
            transition = CrossFade
        )

    }
}

@Composable
private fun PlayerDetailNameAndId(player: Player) {
    Row(
        modifier = Modifier
            .padding(top = 24.dp)
    ) {

        // Player id
        Text(
            text = "${player.id.toString()} |" ?: "",
            color = Color.LightGray,
            fontWeight = FontWeight.Black,
            fontSize = 30.sp
        )
        // Player name
        Text(
            modifier = Modifier.padding(start = 6.dp),
            text = "${player.first_name ?: ""} ${player.last_name ?: ""} " ?: "",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )


    }
}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
private fun PlayerDetailStats(player: Player) {
    Row(
        modifier = Modifier.padding(top = 24.dp)
    ) {

        if (player.position != "" && player.position != null) {
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
        }


        player.height?.let {
            // Height icon
            GlideImage(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(20.dp)
                    .align(Alignment.CenterVertically),
                model = R.drawable.icon_measurment,
                contentDescription = "Height icon",
                contentScale = ContentScale.Fit,
                transition = CrossFade
            )

            // Height
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 5.dp),
                text = player.height.toFeetAndInches() ?: "",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        player.weight?.let {
            // Weight icon
            GlideImage(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(20.dp)
                    .align(Alignment.CenterVertically),
                model = R.drawable.icon_weight,
                contentDescription = "Weight icon",
                contentScale = ContentScale.Fit,
                transition = CrossFade
            )

            // Weight
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 5.dp),
                text = "${player.weight} Lbs" ?: "",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        player.country?.let {
            // Nationalit icon
            GlideImage(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(20.dp)
                    .align(Alignment.CenterVertically),
                model = R.drawable.icon_earth,
                contentDescription = "Nationality icon",
                contentScale = ContentScale.Fit,
                transition = CrossFade
            )

            // Weight
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 5.dp),
                text = player.country ?: "",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }


    }
}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
private fun PlayerDetailTeamInfo(navController: NavController, player: Player) {

    player.team.full_name?.let {
        Row(
            modifier = Modifier.padding(top = 12.dp)
        ) {

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

        Row {

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = { navController.navigate("${NavigationStrings.TEAMDETAIL.toString()}/${player.team.id}") },
                shape = RectangleShape
            ) {
                Text(text = "Detail of ${player.team.name} team")

            }
        }
    }
}

@Composable
fun DetailScreenLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
