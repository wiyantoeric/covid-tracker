package com.example.covidtracker.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covidtracker.R
import com.example.covidtracker.data.continents
import com.example.myapplication.Continent

class ContinentActivity : AppCompatActivity() {
    companion object {
        const val continent_name = "continent_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val continent : Continent = continents.find { continent: Continent? ->
            continent!!.continent.equals(intent.getStringExtra(continent_name))
        }!!

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_continent)
    }
}