package com.example.nobellaureates.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

class ApiService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getNobelPrizes(
        limit: Int = 500,
        offset: Int = 0,
        year: Int? = null,
        category: String? = null
    ): NobelPrizesResponse {
        return client.get {
            url("https://api.nobelprize.org/2.1/nobelPrizes")
            parameter("limit", limit)
            parameter("offset", offset)
            year?.let { parameter("nobelPrizeYear", it) }
            category?.let { parameter("nobelPrizeCategory", it) }
        }.body()
    }
}