package com.example.nobellaureates.domain.model

data class FavoritePrize(
    val id: String,
    val awardYear: String,
    val category: String,
    val categoryFullName: String?,
    val motivation: String?
)