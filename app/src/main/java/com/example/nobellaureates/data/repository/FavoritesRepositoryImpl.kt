package com.example.nobellaureates.data.repository

import com.example.nobellaureates.data.remote.FavoritesApiService
import com.example.nobellaureates.domain.model.FavoritePrize
import com.example.nobellaureates.domain.repository.FavoritesRepository
import javax.inject.Inject
import kotlin.collections.map

class FavoritesRepositoryImpl @Inject constructor(
    private val apiService: FavoritesApiService
) : FavoritesRepository {
    override suspend fun getFavorites(): List<FavoritePrize> {
        val response = apiService.getFavorites()
        return response.map { prize ->
            FavoritePrize(
                id = "${prize.awardYear}_${prize.category}",
                awardYear = prize.awardYear,
                category = prize.category,
                categoryFullName = prize.categoryFullName,
                motivation = prize.motivation
            )
        }
    }

    override suspend fun addFavorite(prizeId: String) {
        apiService.addFavorite(prizeId)
    }

    override suspend fun removeFavorite(prizeId: String) {
        apiService.removeFavorite(prizeId)
    }
}