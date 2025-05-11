package com.anime.app.data.model

data class AnimeListResponse(
    val data: List<Anime>
)

data class Anime(
    val mal_id: Int,
    val title: String,
    val episodes: Int,
    val score: Double,
    val images: Images
)

data class Images(val jpg: ImageUrl)
data class ImageUrl(val image_url: String)