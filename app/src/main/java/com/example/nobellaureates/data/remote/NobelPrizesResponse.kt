package com.example.nobellaureates.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NobelPrizesResponse(
    @SerialName("nobelPrizes")
    val nobelPrizes: List<NobelPrizeDto>
)

@Serializable
data class NobelPrizeDto(
    @SerialName("awardYear")
    val awardYear: String? = null,
    @SerialName("category")
    val category: CategoryName? = null,
    @SerialName("categoryFullName")
    val categoryFullName: NobelPrizeCategoryName? = null,
    @SerialName("dateAwarded")
    val dateAwarded: String? = null,
    @SerialName("prizeAmount")
    val prizeAmount: Int? = null,
    @SerialName("prizeStatus")
    val prizeStatus: String? = null,
    @SerialName("laureates")
    val laureates: List<LaureateDto>? = null
)

@Serializable
data class NobelPrizeCategoryName(
    @SerialName("en")
    val en: String
)

@Serializable
data class CategoryName(
    @SerialName("en") val en: String,
    @SerialName("no") val no: String
)

@Serializable
data class LaureateDto(
    @SerialName("id")
    val id: String = "",
    @SerialName("fullName")
    val fullName: FullName? = null,
    @SerialName("motivation")
    val motivation: Motivation? = null,
    @SerialName("portion")
    val portion: String = "",
    @SerialName("sortOrder")
    val sortOrder: String? = null,
    @SerialName("portraitUrl")
    val portraitUrl: String? = null,
    @SerialName("birth")
    val birth: BirthInfo? = null
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
    val place: Place?,
    @SerialName("date")
    val date: String?
)

@Serializable
data class Country(
    @SerialName("en")
    val en: String
)

@Serializable
data class Place(
    @SerialName("city")
    val city: String?,
    @SerialName("country")
    val country: Country?
)