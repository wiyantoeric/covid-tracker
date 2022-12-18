package com.example.covidtracker.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.covidtracker.R
import com.example.covidtracker.data.continents
import com.example.covidtracker.data.countries
import com.example.covidtracker.ui.widget.CountryGridAdapter
import com.example.myapplication.Continent
import com.example.myapplication.Country

class ContinentActivity : AppCompatActivity() {
    companion object {
        const val continent_name = "continent_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val continent: Continent = continents.find { continent: Continent? ->
            continent!!.continent.equals(intent.getStringExtra(continent_name))
        }!!

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_continent)

        val countryList = countries.filter { country: Country? ->
            country!!.country in continent.countries!!
        }.toMutableList()

        val countryGrid = findViewById<RecyclerView>(R.id.country_grid)
        countryGrid.layoutManager = GridLayoutManager(this, 2)

        val countryGridAdapter = CountryGridAdapter(countryList)
        countryGrid.adapter = countryGridAdapter

        val sortButton = findViewById<ImageButton>(R.id.sort_button)
        sortButton.setOnClickListener {
            val popup = PopupMenu(this, it)
            popup.inflate(R.menu.sort_menu)
            popup.setForceShowIcon(true)
            popup.show()

            popup.setOnMenuItemClickListener { item: MenuItem? ->
                when (item?.itemId) {
                    R.id.sort_case_ascending -> {
                        countryList.sortBy { country: Country? ->
                            country!!.active
                        }

                        countryGridAdapter.notifyDataSetChanged()
                    }
                    R.id.sort_case_descending -> {
                        countryList.sortByDescending { country: Country? ->
                            country!!.active
                        }

                        countryGridAdapter.notifyDataSetChanged()
                    }
                }
                true
            }
        }

        val backButton = findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
    }
}