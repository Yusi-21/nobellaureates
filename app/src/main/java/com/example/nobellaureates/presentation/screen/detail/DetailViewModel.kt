package com.example.nobellaureates.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nobellaureates.domain.model.Laureate
import com.example.nobellaureates.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getLaureatesUseCase: GetLaureatesUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val laureate: Laureate) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    fun loadLaureate(prizeId: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val allLaureates = getLaureatesUseCase()
                val laureate = allLaureates.find { it.prizeId == prizeId }
                if (laureate != null) {
                    _uiState.value = UiState.Success(laureate)
                } else {
                    _uiState.value = UiState.Error("Лауреат не найден")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun checkIsFavorite(prizeId: String) {
        viewModelScope.launch {
            try {
                val favorites = getFavoritesUseCase()
                _isFavorite.value = favorites.any { it.id == prizeId }
            } catch (e: Exception) {
                _isFavorite.value = false
            }
        }
    }

    fun addToFavorites(prizeId: String) {
        viewModelScope.launch {
            addFavoriteUseCase(prizeId)
            _isFavorite.value = true
        }
    }

    fun removeFromFavorites(prizeId: String) {
        viewModelScope.launch {
            removeFavoriteUseCase(prizeId)
            _isFavorite.value = false
        }
    }
}