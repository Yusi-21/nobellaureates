package com.example.nobellaureates.data.model

data class LaureateEntity(
    val id: String,
    val fullName: String,
    val motivation: String?,
    val portraitUrl: String?,
    val birthCountry: String?,
    val birthPlace: String?,
    val awardYear: String,
    val category: String,
    val categoryFullName: String?,
    val portion: String
)