package com.example.covidtracker.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtracker.R
import com.example.covidtracker.data.continents
import com.example.covidtracker.data.countries
import com.example.covidtracker.ui.widget.CountryGridAdapter
import com.example.covidtracker.data.model.Continent
import com.example.covidtracker.data.model.Country
import com.example.covidtracker.ui.widget.setSearchBar
import numberFormat

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

        val searchBar = findViewById<EditText>(R.id.country_search)
        setSearchBar(searchBar) {
            val searchIntent = Intent(this, SearchActivity::class.java)
            searchIntent.putExtra(SearchActivity.keyword, searchBar.text.toString())
            startActivity(searchIntent)
        }

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

        findViewById<TextView>(R.id.continent_name).text = continent.continent!!
        findViewById<TextView>(R.id.cases).text = numberFormat(continent.cases!!)
        findViewById<TextView>(R.id.death).text = numberFormat(continent.deaths!!)
        findViewById<TextView>(R.id.recovered).text = numberFormat(continent.recovered!!)
        findViewById<TextView>(R.id.active).text = numberFormat(continent.active!!)
        findViewById<TextView>(R.id.new_cases).text = numberFormat(continent.todayCases!!)
    }
}