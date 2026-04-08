package com.example.nobellaureates.presentation.common

data class FilterState(
    val selectedYear: Int? = null,
    val selectedCategory: String? = null
)

val categories = listOf(
    "All" to null,
    "Physics" to "physics",
    "Chemistry" to "chemistry",
    "Medicine" to "medicine",
    "Literature" to "literature",
    "Peace" to "peace",
    "Economic Sciences" to "economics"
)

val years = (1901..2024).reversed()