package com.anime.app.data.repo

import com.anime.app.data.api.AnimeApiService

class AnimeRepo(private val api: AnimeApiService) {
    suspend fun getTopAnime() = api.getTopAnime().data
    suspend fun getAnimeDetail(id: Int) = api.getAnimeDetail(id).data
}