package com.example.covidtracker.ui.global

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.covidtracker.data.global
import com.example.covidtracker.databinding.FragmentGlobalBinding
import numberFormat
import org.eazegraph.lib.models.BarModel
import org.eazegraph.lib.models.PieModel
import org.eazegraph.lib.models.StackedBarModel


class GlobalFragment : Fragment() {

    private var _binding: FragmentGlobalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val globalViewModel =
            ViewModelProvider(this)[GlobalViewModel::class.java]

        _binding = FragmentGlobalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        globalViewModel.fetchData {
            updateUi(_binding)
        }

        return root
    }

    private fun updateUi(binding: FragmentGlobalBinding?) {
        binding!!.progressCircular.visibility = View.GONE
        binding.backdrop.visibility = View.GONE

        val pieChart = binding.pieChart

        val case = global!!.cases
        val recovered = global!!.recovered
        val death = global!!.deaths

        val caseModel = PieModel("Total Case", case!!.toFloat(), Color.parseColor("#6D9886"))
        val recoveredModel = PieModel("Recovered", recovered!!.toFloat(), Color.parseColor("#F2E7D5"))
        val deathModel = PieModel("Deaths", death!!.toFloat(), Color.parseColor("#393E46"))

        binding.cases.text = numberFormat(case)
        binding.recovered.text = numberFormat(recovered)
        binding.death.text = numberFormat(death)

        pieChart.addPieSlice(caseModel)
        pieChart.addPieSlice(recoveredModel)
        pieChart.addPieSlice(deathModel)

        pieChart.showContextMenu()
        pieChart.startAnimation()

        val stackedChart = binding.stackedChart
        val stackedBarModel = StackedBarModel("Today Cases")

        val todayCase = global!!.todayCases
        val todayRecovered = global!!.todayRecovered
        val todayDeath = global!!.todayDeaths

        binding.todayCases.text = numberFormat(todayCase ?: 0)
        binding.todayRecovered.text = numberFormat(todayRecovered ?: 0)
        binding.todayDeath.text = numberFormat(todayDeath ?: 0)

        stackedBarModel.addBar(BarModel("New Case", todayCase!!.toFloat(), Color.parseColor("#6D9886")))
        stackedBarModel.addBar(BarModel("Today Recovered", todayRecovered!!.toFloat(), Color.parseColor("#F2E7D5")))
        stackedBarModel.addBar(BarModel("Today Deaths", todayDeath!!.toFloat(), Color.parseColor("#393E46")))

        stackedChart.addBar(stackedBarModel)

        stackedChart.startAnimation()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}