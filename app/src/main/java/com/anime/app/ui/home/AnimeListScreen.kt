package com.anime.app.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.anime.app.R
import com.anime.app.data.model.Anime
import com.anime.app.data.model.ImageUrl
import com.anime.app.data.model.Images
import com.anime.app.ui.Label

@Composable
fun AnimeListScreen(navController: NavController, viewModel: AnimeListViewModel = viewModel()) {
    val list by viewModel.animeList.collectAsState(initial = arrayListOf<Anime>())
    val isLoading by viewModel.isLoading.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn {
                items(
                    list, key = { anime -> anime.mal_id }) { anime ->
                    AnimeItem(
                        anime = anime,
                        onClick = { navController.navigate("detail/${anime.mal_id}") })
                }
            }
        }
    }
}

@Composable
fun AnimeItem(anime: Anime, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(anime.images.jpg.image_url),
                contentDescription = anime.title,
                modifier = Modifier
                    .width(100.dp)
                    .aspectRatio(0.7f)
                    .background(Color.LightGray)

            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = anime.title, style = MaterialTheme.typography.titleLarge)

                Spacer(modifier = Modifier.height(6.dp))

                Label(stringResource(R.string.rating), anime.score.toString())

                Spacer(modifier = Modifier.height(6.dp))

                Label(stringResource(R.string.episodes), anime.episodes.toString())
            }
        }
    }
}

@Preview
@Composable
fun PreviewAnimeItem() {
    AnimeItem(
        anime = Anime(
            mal_id = 1, title = "Naruto", score = 8.5, episodes = 220, images = Images(
                jpg = ImageUrl("https://cdn.myanimelist.net/images/anime/1015/138006.jpg")
            )
        ), onClick = {})
}
