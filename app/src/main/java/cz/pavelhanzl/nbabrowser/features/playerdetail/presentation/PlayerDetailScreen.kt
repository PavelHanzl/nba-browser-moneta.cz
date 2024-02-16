package cz.pavelhanzl.nbabrowser.features.playerdetail.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
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
import cz.pavelhanzl.nbabrowser.features.playersearch.presentation.NbaTopAppBar
import cz.pavelhanzl.nbabrowser.navigation.NavigationStrings
import cz.pavelhanzl.nbabrowser.temp.images.PlayerPhoto
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlayerDetailScreen(
    navController: NavController,
    viewModel: PlayerDetailViewModel = koinViewModel()
) {

    val player = viewModel.state.player

    Scaffold(
        topBar = {
            NbaTopAppBar(
                navController = navController,
                icon = { R.drawable.icon_back },
                title = { "Player Detail" },
                backButtonEnabled = { true }
            )
        }
    ) {

        if (player != null) {

            with(player) {
                Column {

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


                    Text(text = viewModel.nameOfScreen)
                    Text(text = id.toString())
                    Text(text = first_name)
                    Text(text = last_name)
                    Text(text = height)
                    Text(text = country)
                    Text(text = team.full_name)
                    Button(onClick = { navController.navigate("${NavigationStrings.TEAMDETAIL.toString()}/${player.team.id}") }) {
                        Text(text = "Detail ${team.name}")

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
