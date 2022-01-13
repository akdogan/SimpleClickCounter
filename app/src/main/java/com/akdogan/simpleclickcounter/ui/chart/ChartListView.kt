package com.akdogan.simpleclickcounter.ui.chart

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ComposeChartItem(
    val date: String,
    val clicks: String
)

@Composable
fun ChartListView(chartItems: List<ComposeChartItem>){
    LazyColumn{
        items(chartItems){ item ->
            ChartItemView(item = item)
        }
    }
}

@Composable
fun ChartItemView(item: ComposeChartItem) {
    Row(
        modifier = Modifier.padding(12.dp)
    ) {
        Text(
            text = item.date,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer( modifier = Modifier.width(32.dp))
        Text(
            text = item.clicks,
            fontSize = 18.sp
        )
    }
}
