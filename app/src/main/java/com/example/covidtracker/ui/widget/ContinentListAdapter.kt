package com.example.covidtracker.ui.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtracker.R
import com.example.myapplication.Continent
import numberFormat

class ContinentListAdapter(private val continentList: MutableList<Continent?>) :
    RecyclerView.Adapter<ContinentListAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var continentName: TextView = itemView.findViewById(R.id.continent_name)
        var cases: TextView = itemView.findViewById(R.id.cases)
        var recovered: TextView = itemView.findViewById(R.id.recovered)
        var death: TextView = itemView.findViewById(R.id.death)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContinentListAdapter.ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.continent_card, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val continent = continentList[position]
        val noInfo = "No info"

        holder.continentName.text = continent!!.continent

        holder.cases.text = if (continent.cases != null) numberFormat(continent.cases) else noInfo
        holder.recovered.text =
            if (continent.recovered != null) numberFormat(continent.recovered) else noInfo
        holder.death.text = if (continent.deaths != null) numberFormat(continent.deaths) else noInfo

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(continentList[position]!!)
        }
    }

    override fun getItemCount(): Int {
        return continentList.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(continent: Continent)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}