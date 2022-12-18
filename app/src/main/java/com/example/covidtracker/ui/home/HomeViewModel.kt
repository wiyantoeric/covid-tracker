package com.example.covidtracker.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidtracker.data.countries
import com.example.covidtracker.data.continents
import com.example.myapplication.Continent
import com.example.myapplication.Country
import com.example.myapplication.DiseaseApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    fun fetchData(callback: () -> Unit) {
        if (countries.isEmpty()) DiseaseApi().getCountries { data ->
            countries = data!!.toMutableList()
        }

        if (continents.isEmpty()) DiseaseApi().getContinents { data ->
            continents = data!!.toMutableList()
            callback()
        } else {
            callback()
        }
    }

    val text: LiveData<String> = _text
}