package com.akdogan.simpleclickcounter.ui.chart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun ChartScreen(
    viewModel: ChartViewModel
) {
    val list: List<ComposeChartItem> by viewModel.entries.observeAsState(emptyList())
    ChartListView(chartItems = list)
}