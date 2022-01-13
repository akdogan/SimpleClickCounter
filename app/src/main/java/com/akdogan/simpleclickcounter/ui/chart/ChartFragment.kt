package com.akdogan.simpleclickcounter.ui.chart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.ViewModelProvider
import com.akdogan.simpleclickcounter.ui.main.ClickViewModelFactory
import com.akdogan.simpleclickcounter.ui.theme.SimpleClickCounterTheme


class ChartFragment : Fragment() {


    private lateinit var viewModel: ChartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModelFactory = ClickViewModelFactory(requireContext())

        viewModel = ViewModelProvider(this, viewModelFactory).get(
            ChartViewModel::class.java
        )

        val test = listOf(
            ComposeChartItem("01.10.2021", "12"),
            ComposeChartItem("02.10.2021", "2"),
            ComposeChartItem("03.10.2021", "22"),
            ComposeChartItem("04.10.2021", "15"),
            ComposeChartItem("05.10.2021", "7")
        )

        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                SimpleClickCounterTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(color = MaterialTheme.colors.background) {
                        ChartScreen(viewModel)
                    }
                }
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.entries.observe(viewLifecycleOwner){ list ->
            list.forEach {
                Log.d("FragmentObserver", it.toString())
            }
        }
    }


}