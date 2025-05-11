package com.anime.app.data.model

data class AnimeDetailResponse(val data: AnimeDetail)

data class AnimeDetail(
    val title: String,
    val synopsis: String?,
    val episodes: Int?,
    val score: Double?,
    val trailer: Trailer?,
    val images: Images,
    val genres: List<Genre>
)

data class Trailer(val youtube_id: String?)

data class Genre(val name: String)