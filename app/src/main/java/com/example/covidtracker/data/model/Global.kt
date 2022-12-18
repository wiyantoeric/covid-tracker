package com.example.covidtracker.data.model

import com.google.gson.annotations.SerializedName

data class Global(

	@field:SerializedName("recoveredPerOneMillion")
	val recoveredPerOneMillion: Any? = null,

	@field:SerializedName("cases")
	val cases: Int? = null,

	@field:SerializedName("critical")
	val critical: Int? = null,

	@field:SerializedName("oneCasePerPeople")
	val oneCasePerPeople: Int? = null,

	@field:SerializedName("active")
	val active: Int? = null,

	@field:SerializedName("testsPerOneMillion")
	val testsPerOneMillion: Any? = null,

	@field:SerializedName("population")
	val population: Long? = null,

	@field:SerializedName("affectedCountries")
	val affectedCountries: Int? = null,

	@field:SerializedName("oneDeathPerPeople")
	val oneDeathPerPeople: Int? = null,

	@field:SerializedName("recovered")
	val recovered: Int? = null,

	@field:SerializedName("oneTestPerPeople")
	val oneTestPerPeople: Int? = null,

	@field:SerializedName("tests")
	val tests: Long? = null,

	@field:SerializedName("criticalPerOneMillion")
	val criticalPerOneMillion: Any? = null,

	@field:SerializedName("deathsPerOneMillion")
	val deathsPerOneMillion: Any? = null,

	@field:SerializedName("todayRecovered")
	val todayRecovered: Int? = null,

	@field:SerializedName("casesPerOneMillion")
	val casesPerOneMillion: Int? = null,

	@field:SerializedName("updated")
	val updated: Long? = null,

	@field:SerializedName("deaths")
	val deaths: Int? = null,

	@field:SerializedName("activePerOneMillion")
	val activePerOneMillion: Any? = null,

	@field:SerializedName("todayCases")
	val todayCases: Int? = null,

	@field:SerializedName("todayDeaths")
	val todayDeaths: Int? = null
)
