package com.example.nobellaureates.domain.repository

import com.example.nobellaureates.domain.model.Laureate
interface LaureateRepository {
    suspend fun getLaureates(year: Int?, category: String?): List<Laureate>
}