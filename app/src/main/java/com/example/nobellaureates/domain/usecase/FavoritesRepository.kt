package com.example.nobellaureates.domain.repository

import com.example.nobellaureates.domain.model.FavoritePrize

interface FavoritesRepository {
    suspend fun getFavorites(): List<FavoritePrize>
    suspend fun addFavorite(prizeId: String)
    suspend fun removeFavorite(prizeId: String)
}