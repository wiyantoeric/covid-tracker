package com.example.covidtracker.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.data.continents
import com.example.covidtracker.databinding.FragmentHomeBinding
import com.example.covidtracker.ui.activity.ContinentActivity
import com.example.covidtracker.ui.activity.CountryActivity
import com.example.covidtracker.ui.widget.ContinentListAdapter
import com.example.covidtracker.ui.widget.CountryGridAdapter
import com.example.myapplication.Continent

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val search: EditText = binding.searchBar.countrySearch

        homeViewModel.fetchData {
            updateUi(_binding)
        }

//    homeViewModel.text.observe(viewLifecycleOwner) {
//      search.text = it
//    }

        return root
    }

    private fun updateUi(binding: FragmentHomeBinding?) {
        Log.d("response1", continents.toString())

        binding!!.progressCircular.visibility = View.GONE

        if (continents.isNotEmpty()) {
            val continentList = binding.continentList
            continentList.setHasFixedSize(true)
            continentList.layoutManager = LinearLayoutManager(binding.root.context)

            val continentListAdapter = ContinentListAdapter(continents)
            continentList.adapter = continentListAdapter
            continentListAdapter.setOnItemClickCallback(object :
                ContinentListAdapter.OnItemClickCallback {

                override fun onItemClicked(continent: Continent) {
                    val continentActivity =
                        Intent(binding.root.context, ContinentActivity::class.java)
                    continentActivity.putExtra(
                        ContinentActivity.continent_name,
                        continent.continent
                    )
                    startActivity(continentActivity)
                }
            })

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}