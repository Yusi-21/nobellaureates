package com.example.nobellaureates.data.repository

import com.example.nobellaureates.data.remote.ApiService
import com.example.nobellaureates.domain.model.Laureate
import com.example.nobellaureates.domain.repository.LaureateRepository
import javax.inject.Inject

class LaureateRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : LaureateRepository {

    override suspend fun getLaureates(
        year: Int?,
        category: String?
    ): List<Laureate> {
        val prizes = apiService.getNobelPrizes(
            limit = 10,
            offset = 0,
            year = year,
            category = category
        )

        return prizes.flatMap { prize ->
            prize.laureates?.map { laureate ->
                Laureate(
                    id = laureate.id,
                    prizeId = "${prize.awardYear}_${prize.category}",
                    fullName = laureate.fullName,
                    motivation = laureate.motivation,
                    portraitUrl = null,
                    birthCountry = null,
                    birthPlace = null,
                    awardYear = prize.awardYear,
                    category = prize.category,
                    categoryFullName = prize.categoryFullName,
                    shortMotivation = laureate.motivation?.take(100) ?: "No motivation provided"
                )
            } ?: emptyList()
        }
    }
}