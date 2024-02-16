package cz.pavelhanzl.nbabrowser.features.teamdetail.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import cz.pavelhanzl.nbabrowser.R
import cz.pavelhanzl.nbabrowser.features.playerdetail.presentation.DetailScreenLoading
import cz.pavelhanzl.nbabrowser.features.playersearch.presentation.NbaTopAppBar
import cz.pavelhanzl.nbabrowser.features.teamdetail.model.Team
import cz.pavelhanzl.nbabrowser.temp.images.TeamLogo
import org.koin.androidx.compose.koinViewModel

/**
 * Displays the team detail screen.
 *
 * This screen shows detailed information about a specific NBA team, including its logo, name, city,
 * and division. A back button allows navigation back to the previous screen (player detail).
 *
 * @param navController NavController to manage navigation actions.
 * @param viewModel ViewModel that holds the state and business logic for the team detail.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
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
                icon = R.drawable.icon_back,
                title = R.string.team_detail_title
            )
        }
    ) {

        if (team != null) {

            Column(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TeamDetailHeader(team)
                TeamDetailTeamName(team)
                TeamDetailTeamInfo(team)

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = { navController.popBackStack() },
                    shape = RectangleShape
                )
                {
                    Text(text = stringResource(R.string.back_to_player_detail))
                }
            }

        } else {
            DetailScreenLoading()
        }

    }

}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
private fun TeamDetailHeader(
    team: Team
) {
    Box(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .background(Color(0xFFdadada))
    ) {

        // temporary solution - will be replaced by real images from real api
        val randomTeamImage = remember { TeamLogo.entries.toTypedArray().random().url }

        // Player image preview
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.Center),
            model = randomTeamImage,
            contentDescription = stringResource(R.string.logo_of_team),
            contentScale = ContentScale.Fit,
            loading = placeholder(R.drawable.player_loading_transparent_placeholder),
            failure = placeholder(R.drawable.player_failure_transparent_placeholder),
            transition = CrossFade
        )
    }
}

@Composable
private fun TeamDetailTeamName(team: Team) {

    team.full_name?.let {
        // Team name
        Text(
            modifier = Modifier
                .padding(top = 24.dp),
            text = team.full_name ?: "",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        // Team abbreviation
        Text(
            text = "(${team.abbreviation})" ?: "",
            color = Color.LightGray,
            fontWeight = FontWeight.Black,
            fontSize = 24.sp
        )
    }

}


@Composable
@OptIn(ExperimentalGlideComposeApi::class)
private fun TeamDetailTeamInfo(team: Team) {
    Row(
        modifier = Modifier.padding(top = 20.dp)
    ) {

        team.city?.let {
            // City icon
            GlideImage(
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.CenterVertically),
                model = R.drawable.icon_city,
                contentDescription = stringResource(R.string.city_icon),
                contentScale = ContentScale.Fit,
                transition = CrossFade
            )

            // City
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp),
                text = team.city ?: "",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        team.division?.let {

            // Division icon
            GlideImage(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(20.dp)
                    .align(Alignment.CenterVertically),
                model = R.drawable.icon_compas,
                contentDescription = stringResource(R.string.division_icon),
                contentScale = ContentScale.Fit,
                transition = CrossFade
            )

            // Division
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 5.dp),
                text = team.division ?: "",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}
