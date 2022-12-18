package com.example.myapplication

import com.example.covidtracker.data.model.Continent
import com.example.covidtracker.data.model.Country
import com.example.covidtracker.data.model.Global
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
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

    private fun getService(): Response {
        return getRetrofit().create(Response::class.java)
    }

    private interface Response {
        @GET("all")
        fun getGlobal(): Call<Global>

        @GET("continents")
        fun getContinents(): Call<List<Continent>>

        @GET("continents/{continent}")
        fun getContinent(@Path("continent") country: String): Call<Continent>

        @GET("countries")
        fun getCountries(): Call<List<Country>>

        @GET("countries/{country}")
        fun getCountry(@Path("country") country: String): Call<Country>
    }

    fun getGlobal(callback: (Global?) -> Unit) {
        getService().getGlobal().enqueue(object : Callback<Global> {
            override fun onResponse(
                call: Call<Global>,
                response: retrofit2.Response<Global>
            ) {
                val data = response.body()!!
                callback(data)
            }

            override fun onFailure(call: Call<Global>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun getCountries(callback: (List<Country>?) -> Unit) {
        getService().getCountries().enqueue(object : Callback<List<Country>> {
            override fun onResponse(
                call: Call<List<Country>>,
                response: retrofit2.Response<List<Country>>
            ) {
                val data = response.body()!!
                callback(data)
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun getContinents(callback: (List<Continent>?) -> Unit) {
        getService().getContinents().enqueue(object : Callback<List<Continent>> {
            override fun onResponse(
                call: Call<List<Continent>>,
                response: retrofit2.Response<List<Continent>>
            ) {
                val data = response.body()!!
                callback(data)
            }

            override fun onFailure(call: Call<List<Continent>>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun getCountry(country: String, callback: (Country?) -> Unit) {
        getService().getCountry(country).enqueue(object : Callback<Country> {
            override fun onResponse(
                call: Call<Country>,
                response: retrofit2.Response<Country>
            ) {
                val data = response.body()!!
                callback(data)
            }

            override fun onFailure(call: Call<Country>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun getContinent(continent: String, callback: (Continent?) -> Unit) {
        getService().getContinent(continent).enqueue(object : Callback<Continent> {
            override fun onResponse(
                call: Call<Continent>,
                response: retrofit2.Response<Continent>
            ) {
                val data = response.body()!!
                callback(data)
            }

            override fun onFailure(call: Call<Continent>, t: Throwable) {
                callback(null)
            }
        })
    }
}
