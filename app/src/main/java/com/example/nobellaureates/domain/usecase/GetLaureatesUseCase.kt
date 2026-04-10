package com.example.nobellaureates.domain.usecase

import com.example.nobellaureates.domain.model.Laureate
import com.example.nobellaureates.domain.repository.LaureateRepository
import javax.inject.Inject

class GetLaureatesUseCase @Inject constructor(
    private val repository: LaureateRepository
) {
    suspend operator fun invoke(
        year: Int? = null,
        category: String? = null
    ): List<Laureate> {
        val entities = repository.getLaureates(year, category)
        return entities.map { entity ->
            Laureate(
                id = entity.id,
                prizeId = "${entity.awardYear}_${entity.category}",
                fullName = entity.fullName,
                motivation = entity.motivation,
                portraitUrl = entity.portraitUrl,
                birthCountry = entity.birthCountry,
                birthPlace = entity.birthPlace,
                awardYear = entity.awardYear,
                category = entity.category,
                categoryFullName = entity.categoryFullName,
                shortMotivation = entity.motivation?.take(100) ?: "No motivation provided"
            )
        }
    }
}