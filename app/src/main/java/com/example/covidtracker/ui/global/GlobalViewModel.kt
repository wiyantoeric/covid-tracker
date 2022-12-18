package com.example.covidtracker.ui.global

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidtracker.data.continents
import com.example.covidtracker.data.global
import com.example.myapplication.DiseaseApi

class GlobalViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }

    fun fetchData(callback: () -> Unit) {
        if (global == null) DiseaseApi().getGlobal { data ->
            global = data!!
            callback()
        } else {
            callback()
        }
    }

    val text: LiveData<String> = _text
}