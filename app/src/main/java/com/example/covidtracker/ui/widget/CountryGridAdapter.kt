package com.example.covidtracker.ui.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtracker.R
import com.example.myapplication.Continent
import com.example.myapplication.Country
import com.squareup.picasso.Picasso

class CountryGridAdapter(private val countryList: MutableList<Country?>) :
    RecyclerView.Adapter<CountryGridAdapter.GridViewHolder>() {
//    private lateinit var onItemClickCallback: OnItemClickCallback

//    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
//        this.onItemClickCallback = onItemClickCallback
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.country_card, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val country = countryList[position]
        val noInfo = "No info"

        Picasso.get().load(country!!.countryInfo!!.flag).into(holder.countryFlag)
        holder.countryName.text = (if (country.country != null) country.active.toString() else noInfo)

        holder.active.text = (if (country.active != null) country.active.toString() else noInfo)
        holder.newCases.text =
            (if (country.todayCases != null) country.todayCases.toString() else noInfo)
        holder.death.text = (if (country.deaths != null) country.deaths.toString() else noInfo)
        holder.total.text = (if (country.cases != null) country.cases.toString() else noInfo)
        holder.recovered.text =
            (if (country.recovered != null) country.recovered.toString() else noInfo)

//        holder.itemView.setOnClickListener {
//            onItemClickCallback.onItemClicked(countryList[position])
//        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var countryFlag: ImageView = itemView.findViewById(R.id.country_flag)
        var countryName: TextView = itemView.findViewById(R.id.country_name)

        var active: TextView = itemView.findViewById(R.id.active)
        var newCases: TextView = itemView.findViewById(R.id.new_cases)
        var total: TextView = itemView.findViewById(R.id.total)
        var recovered: TextView = itemView.findViewById(R.id.recovered)
        var death: TextView = itemView.findViewById(R.id.death)
    }

//    interface OnItemClickCallback {
//        fun onItemClicked(data: countryList)
//    }
}