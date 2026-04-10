package com.example.nobellaureates.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NobelPrizeRemote(
    @SerialName("awardYear")
    val awardYear: String,
    @SerialName("category")
    val category: String,
    @SerialName("categoryFullName")
    val categoryFullName: String?,
    @SerialName("laureates")
    val laureates: List<LaureateRemote>?
)

@Serializable
data class LaureateRemote(
    @SerialName("id")
    val id: String,
    @SerialName("fullName")
    val fullName: String,
    @SerialName("motivation")
    val motivation: String?,
    @SerialName("portion")
    val portion: String
)

@Serializable
data class FullName(
    @SerialName("en")
    val en: String
)

@Serializable
data class Motivation(
    @SerialName("en")
    val en: String
)

@Serializable
data class BirthInfo(
    @SerialName("country")
    val country: Country?,
    @SerialName("place")
    val place: Place?
)

@Serializable
data class Country(
    @SerialName("en")
    val en: String
)

@Serializable
data class Place(
    @SerialName("city")
    val city: String?
)