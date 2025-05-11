package com.anime.app.data.api

import com.anime.app.data.model.AnimeDetailResponse
import com.anime.app.data.model.AnimeListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeApiService {
    @GET("top/anime")
    suspend fun getTopAnime(): AnimeListResponse

    @GET("anime/{id}")
    suspend fun getAnimeDetail(@Path("id") id: Int): AnimeDetailResponse
}