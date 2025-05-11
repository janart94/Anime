package com.anime.app.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.anime.app.R

@Composable
fun AnimeDetailScreen(animeId: Int, onTitleAvailable: (String) -> Unit) {
    val viewModel: AnimeDetailViewModel = viewModel(factory = AnimeDetailViewModelFactory(animeId))
    val animeData by viewModel.anime.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(animeData?.title) {
        animeData?.title?.let { onTitleAvailable(it) }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        animeData?.let { anime ->
            val trailerId = anime.trailer?.youtube_id
            val imageUrl = anime.images.jpg.image_url

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                if (trailerId.isNullOrEmpty()) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUrl),
                        contentDescription = "Poster",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                } else {
                    YoutubePlayerView(trailerId)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(anime.synopsis ?: "No synopsis available")

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    buildAnnotatedString {
                        append(stringResource(R.string.genres))
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(anime.genres.joinToString { g -> g.name })
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    buildAnnotatedString {
                        append(stringResource(R.string.episodes))
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(anime.episodes.toString())
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    buildAnnotatedString {
                        append(stringResource(R.string.rating))
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(anime.score.toString())
                        }
                    }
                )
            }
        } ?: run {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}