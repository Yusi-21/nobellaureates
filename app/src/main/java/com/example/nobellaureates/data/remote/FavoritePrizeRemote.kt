package com.example.nobellaureates.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoritePrizeRemote(
    @SerialName("awardYear")
    val awardYear: String,
    @SerialName("category")
    val category: String,
    @SerialName("categoryFullName")
    val categoryFullName: String?,
    @SerialName("motivation")
    val motivation: String? = null,
    @SerialName("laureates")
    val laureates: List<FavoriteLaureateRemote>?
)

@Serializable
data class FavoriteLaureateRemote(
    @SerialName("id")
    val id: String,
    @SerialName("fullName")
    val fullName: FullName?,
    @SerialName("motivation")
    val motivation: Motivation?,
    @SerialName("portraitUrl")
    val portraitUrl: String?,
    @SerialName("birth")
    val birth: BirthInfo?
)