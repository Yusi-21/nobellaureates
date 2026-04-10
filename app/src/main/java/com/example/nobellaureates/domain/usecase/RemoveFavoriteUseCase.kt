package com.example.nobellaureates.domain.usecase

import com.example.nobellaureates.domain.repository.FavoritesRepository
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(prizeId: String) {
        repository.removeFavorite(prizeId)
    }
}