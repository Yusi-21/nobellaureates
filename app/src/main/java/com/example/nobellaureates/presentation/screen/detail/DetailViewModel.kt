package com.example.nobellaureates.presentation.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nobellaureates.domain.model.Laureate
import com.example.nobellaureates.domain.usecase.GetLaureatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getLaureatesUseCase: GetLaureatesUseCase
) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val laureate: Laureate) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun loadLaureate(id: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val allLaureates = getLaureatesUseCase()
                val laureate = allLaureates.find { it.id == id }
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
}