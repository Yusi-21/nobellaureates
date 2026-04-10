package com.example.nobellaureates.domain.model

data class Laureate(
    val id: String,
    val prizeId: String,
    val fullName: String,
    val motivation: String?,
    val portraitUrl: String?,
    val birthCountry: String?,
    val birthPlace: String?,
    val awardYear: String,
    val category: String,
    val categoryFullName: String?,
    val shortMotivation: String
)