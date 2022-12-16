package com.example.myapplication

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class DiseaseApi {
    private fun getRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://disease.sh/v3/covid-19/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService(): Response {
        return getRetrofit().create(Response::class.java)
    }

    interface Response {
        @GET("continents")
        fun getContinents(): Call<List<Continent>>

        @GET("continents/{continent}")
        fun getContinent(@Path("continent") country: String): Call<Continent>

        @GET("countries")
        fun getCountries(): Call<List<Country>>

        @GET("countries/{country}")
        fun getCountry(@Path("country") country: String): Call<Country>
    }
}
