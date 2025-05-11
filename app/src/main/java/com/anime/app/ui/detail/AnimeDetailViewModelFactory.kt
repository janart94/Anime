package com.anime.app.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AnimeDetailViewModelFactory(private val animeId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimeDetailViewModel::class.java)) {
            return AnimeDetailViewModel(animeId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}