package com.example.nobellaureates.presentation.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nobellaureates.domain.model.Laureate
import com.example.nobellaureates.domain.usecase.GetLaureatesUseCase
import com.example.nobellaureates.presentation.common.FilterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaureateListViewModel @Inject constructor(
    private val getLaureatesUseCase: GetLaureatesUseCase
) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val laureates: List<Laureate>) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _filterState = MutableStateFlow(FilterState())
    val filterState: StateFlow<FilterState> = _filterState.asStateFlow()

    init {
        loadLaureates()
    }

    fun loadLaureates() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val laureates = getLaureatesUseCase(
                    year = _filterState.value.selectedYear,
                    category = _filterState.value.selectedCategory
                )
                _uiState.value = UiState.Success(laureates)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun updateFilter(year: Int?, category: String?) {
        _filterState.value = FilterState(
            selectedYear = year,
            selectedCategory = category
        )
        loadLaureates()
    }
}