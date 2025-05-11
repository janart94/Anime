package com.anime.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anime.app.data.api.RetrofitInstance
import com.anime.app.data.model.Anime
import com.anime.app.data.repo.AnimeRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeListViewModel : ViewModel() {
    private val repo = AnimeRepo(RetrofitInstance.api)
    private val _animeList = MutableStateFlow<List<Anime>>(emptyList())
    val animeList: StateFlow<List<Anime>> get() = _animeList

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        viewModelScope.launch {
            _isLoading.value = true
            _animeList.value = repo.getTopAnime()
            _isLoading.value = false
        }
    }
}