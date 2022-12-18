package com.example.covidtracker.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.R
import com.example.covidtracker.data.continents
import com.example.covidtracker.databinding.FragmentHomeBinding
import com.example.covidtracker.ui.activity.ContinentActivity
import com.example.covidtracker.ui.widget.ContinentListAdapter
import com.example.covidtracker.data.model.Continent
import com.example.covidtracker.ui.activity.SearchActivity
import com.example.covidtracker.ui.widget.setSearchBar

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


            val sortButton: ImageButton = binding.sortButton

            sortButton.setOnClickListener {
                val popup = PopupMenu(binding.root.context, it)
                popup.inflate(R.menu.sort_menu)
                popup.setForceShowIcon(true)
                popup.show()

                popup.setOnMenuItemClickListener { item: MenuItem? ->
                    when (item?.itemId) {
                        R.id.sort_case_ascending -> {
                            continents.sortBy { continent: Continent? ->
                                continent?.cases
                            }

                            continentListAdapter.notifyDataSetChanged()
                        }
                        R.id.sort_case_descending -> {
                            continents.sortByDescending { continent: Continent? ->
                                continent?.cases
                            }

                            continentListAdapter.notifyDataSetChanged()
                        }
                    }
                    true
                }
            }

            val searchBar = binding.searchBar.countrySearch
            setSearchBar(searchBar) {
                val searchIntent = Intent(binding.root.context, SearchActivity::class.java)
                searchIntent.putExtra(SearchActivity.keyword, searchBar.text.toString())
                startActivity(searchIntent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}