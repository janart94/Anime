package com.anime.app.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anime.app.data.api.RetrofitInstance
import com.anime.app.data.model.AnimeDetail
import com.anime.app.data.repo.AnimeRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeDetailViewModel(private val animeId: Int) : ViewModel() {
    private val repo = AnimeRepo(RetrofitInstance.api)
    private val _anime = MutableStateFlow<AnimeDetail?>(null)
    val anime: StateFlow<AnimeDetail?> = _anime

    init {
        viewModelScope.launch {
            _anime.value = repo.getAnimeDetail(animeId)
        }
    }
}