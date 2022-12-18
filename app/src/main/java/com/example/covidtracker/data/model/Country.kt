package com.example.covidtracker.data.model

import com.google.gson.annotations.SerializedName

data class Country(

	@field:SerializedName("continent")
	val continent: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("recoveredPerOneMillion")
	val recoveredPerOneMillion: Any? = null,

	@field:SerializedName("cases")
	val cases: Double? = null,

	@field:SerializedName("critical")
	val critical: Double? = null,

	@field:SerializedName("oneCasePerPeople")
	val oneCasePerPeople: Double? = null,

	@field:SerializedName("active")
	val active: Double? = null,

	@field:SerializedName("testsPerOneMillion")
	val testsPerOneMillion: Double? = null,

	@field:SerializedName("population")
	val population: Double? = null,

	@field:SerializedName("oneDeathPerPeople")
	val oneDeathPerPeople: Double? = null,

	@field:SerializedName("recovered")
	val recovered: Double? = null,

	@field:SerializedName("oneTestPerPeople")
	val oneTestPerPeople: Double? = null,

	@field:SerializedName("tests")
	val tests: Double? = null,

	@field:SerializedName("criticalPerOneMillion")
	val criticalPerOneMillion: Any? = null,

	@field:SerializedName("deathsPerOneMillion")
	val deathsPerOneMillion: Double? = null,

	@field:SerializedName("todayRecovered")
	val todayRecovered: Double? = null,

	@field:SerializedName("casesPerOneMillion")
	val casesPerOneMillion: Double? = null,

	@field:SerializedName("countryInfo")
	val countryInfo: CountryInfo? = null,

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

data class CountryInfo(

	@field:SerializedName("flag")
	val flag: String? = null,

	@field:SerializedName("_id")
	val id: Double? = null,

	@field:SerializedName("iso2")
	val iso2: String? = null,

	@field:SerializedName("lat")
	val lat: Double? = null,

	@field:SerializedName("long")
	val jsonMemberLong: Double? = null,

	@field:SerializedName("iso3")
	val iso3: String? = null
)
