package com.example.nobellaureates.domain.usecase

import com.example.nobellaureates.domain.model.FavoritePrize
import com.example.nobellaureates.domain.repository.FavoritesRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(): List<FavoritePrize> {
        return repository.getFavorites()
    }
}