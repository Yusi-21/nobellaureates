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
        val response = apiService.getNobelPrizes(
            limit = 500,
            offset = 0,
            year = year,
            category = category
        )

        return response.nobelPrizes.flatMap { prize ->
            val awardYear = prize.awardYear ?: return@flatMap emptyList()
            val categoryName = prize.category?.en ?: return@flatMap emptyList()

            prize.laureates?.mapNotNull { laureate ->
                val fullName = laureate.fullName?.en ?: return@mapNotNull null

                Laureate(
                    id = laureate.id.ifEmpty { return@mapNotNull null },
                    fullName = fullName,
                    motivation = laureate.motivation?.en,
                    portraitUrl = laureate.portraitUrl,
                    birthCountry = laureate.birth?.country?.en,
                    birthPlace = laureate.birth?.place?.city,
                    awardYear = awardYear,
                    category = categoryName,
                    categoryFullName = prize.categoryFullName?.en,
                    shortMotivation = laureate.motivation?.en?.take(100) ?: "No motivation provided"
                )
            } ?: emptyList()
        }
    }
}