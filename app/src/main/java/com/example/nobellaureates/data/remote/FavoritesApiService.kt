package com.example.nobellaureates.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import javax.inject.Inject

class FavoritesApiService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getFavorites(): List<FavoritePrizeRemote> {
        return client.get("/users/me/prizes").body()
    }

    suspend fun addFavorite(prizeId: String) {
        client.post("/users/me/prizes/$prizeId")
    }

    suspend fun removeFavorite(prizeId: String) {
        client.delete("/users/me/prizes/$prizeId")
    }
}