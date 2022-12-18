package com.example.covidtracker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidtracker.data.global
import com.example.covidtracker.data.continents
import com.example.covidtracker.data.countries
import com.example.myapplication.DiseaseApi

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