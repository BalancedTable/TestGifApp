package com.example.testtask.ui.themeAndScreens.Screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.testtask.data.TestGifRepository
import com.example.testtask.network.GIF
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.example.testtask.GifApplication

sealed interface GifUiState {
    data class Success(val gifs: List<GIF>): GifUiState
    object Error: GifUiState
    object Loading: GifUiState
}

class FirstScreenViewModel (private val gifRepository: TestGifRepository): ViewModel(){
    var gifUiState: GifUiState by mutableStateOf(GifUiState.Loading)
        private set

    init {
        getGif()
    }

    fun getGif(){
        viewModelScope.launch {
            gifUiState = try {
                GifUiState.Success(gifRepository.getTestGif())
            } catch (e: Exception) {
                GifUiState.Error
            }
        }
    }
    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GifApplication)
                val gifRepository = application.container.GifRepository
                FirstScreenViewModel(gifRepository = gifRepository)
            }
        }
    }
}