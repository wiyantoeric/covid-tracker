package com.example.covidtracker.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtracker.R
import com.example.covidtracker.data.countries
import com.example.covidtracker.data.model.Country
import com.example.covidtracker.ui.widget.CountryGridAdapter
import com.example.covidtracker.ui.widget.setSearchBar

class SearchActivity : AppCompatActivity() {
    companion object {
        const val keyword = "search_keyword"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val keyword = intent.getStringExtra(keyword)
        Log.d("keyword", keyword.toString())

        val searchBar = findViewById<EditText>(R.id.country_search)
        searchBar.setText(keyword)
        setSearchBar(searchBar) {
            finish()

            val searchIntent = Intent(this, SearchActivity::class.java)
            searchIntent.putExtra(SearchActivity.keyword, searchBar.text.toString())
            startActivity(searchIntent)
        }

        val countryName = findViewById<TextView>(R.id.country_name)
        countryName.text = keyword ?: "-"

        val backButton = findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        val countryList = countries.filter { country: Country? ->
            country?.country!!.toLowerCase().contains(keyword!!.toLowerCase())
        }.toMutableList()

        if (countryList.isEmpty()) {
            val countryGrid = findViewById<RecyclerView>(R.id.country_grid)
            countryGrid.visibility = View.GONE
        } else {
            val notFound = findViewById<TextView>(R.id.not_found)
            notFound.visibility = View.GONE

            val countryGrid = findViewById<RecyclerView>(R.id.country_grid)
            countryGrid.layoutManager = GridLayoutManager(this, 2)

            val countryGridAdapter = CountryGridAdapter(countryList)
            countryGrid.adapter = countryGridAdapter
        }
    }
}