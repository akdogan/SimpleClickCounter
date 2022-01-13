package com.akdogan.simpleclickcounter.ui.chart

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.akdogan.simpleclickcounter.datalayer.Repository
import com.akdogan.simpleclickcounter.datalayer.StatisticModelDomain
import com.akdogan.simpleclickcounter.extension.toDateString

class ChartViewModel(
    context: Context
): ViewModel() {

    private val repository = Repository(context)

    val entries: LiveData<List<ComposeChartItem>> =
        Transformations.map(repository.getDataAsLiveData()){
            it.map { item ->
                item.toComposeModel()
            }
        }

}

private fun StatisticModelDomain.toComposeModel(): ComposeChartItem {
    return ComposeChartItem(
        date = date.toDateString("dd.MM.yyy"),
        clicks = clicks.toString()
    )
}
