package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class Continent(

    @field:SerializedName("continent")
    val continent: String? = null,

    @field:SerializedName("recoveredPerOneMillion")
    val recoveredPerOneMillion: Any? = null,

    @field:SerializedName("cases")
    val cases: Double? = null,

    @field:SerializedName("continentInfo")
    val continentInfo: ContinentInfo? = null,

    @field:SerializedName("critical")
    val critical: Double? = null,

    @field:SerializedName("active")
    val active: Double? = null,

    @field:SerializedName("countries")
    val countries: List<String?>? = null,

    @field:SerializedName("testsPerOneMillion")
    val testsPerOneMillion: Any? = null,

    @field:SerializedName("population")
    val population: Double? = null,

    @field:SerializedName("recovered")
    val recovered: Double? = null,

    @field:SerializedName("tests")
    val tests: Double? = null,

    @field:SerializedName("criticalPerOneMillion")
    val criticalPerOneMillion: Any? = null,

    @field:SerializedName("deathsPerOneMillion")
    val deathsPerOneMillion: Any? = null,

    @field:SerializedName("todayRecovered")
    val todayRecovered: Double? = null,

    @field:SerializedName("casesPerOneMillion")
    val casesPerOneMillion: Any? = null,

    @field:SerializedName("updated")
    val updated: Long? = null,

    @field:SerializedName("deaths")
    val deaths: Double? = null,

    @field:SerializedName("activePerOneMillion")
    val activePerOneMillion: Any? = null,

    @field:SerializedName("todayCases")
    val todayCases: Double? = null,

    @field:SerializedName("todayDeaths")
    val todayDeaths: Double? = null
)

data class ContinentInfo(

    @field:SerializedName("lat")
    val lat: Any? = null,

    @field:SerializedName("long")
    val jsonMemberLong: Any? = null
)
