package com.anime.app.ui.detail

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YoutubePlayerView(videoId: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    AndroidView(
        factory = {
            val youTubePlayerView = YouTubePlayerView(context).apply {
                enableAutomaticInitialization = false
            }

            youTubePlayerView.initialize(
                object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.cueVideo(videoId, 0f)
                    }
                }
            )

            youTubePlayerView
        },
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16 / 9f)
    )
}