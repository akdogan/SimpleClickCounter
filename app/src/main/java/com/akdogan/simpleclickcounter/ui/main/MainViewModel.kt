package com.akdogan.simpleclickcounter.ui.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.akdogan.simpleclickcounter.datalayer.Repository
import com.akdogan.simpleclickcounter.datalayer.StatisticModelDomain
import com.akdogan.simpleclickcounter.datalayer.roomdb.StatisticsWorker
import com.akdogan.simpleclickcounter.datalayer.roomdb.createWorkerPackage
import com.akdogan.simpleclickcounter.extension.getTodayAsLong
import com.akdogan.simpleclickcounter.ui.chart.ChartViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainViewModel(
    context: Context
): ViewModel() {

    private val repository = Repository(context)

    private val workManager = WorkManager.getInstance(context)

    private var counter: Long = 0

    private val _counter: MutableLiveData<Long> = MutableLiveData(null)
    val currentCounterValueAsString: LiveData<String>
        get() = Transformations.map(_counter){
            it.toString()
        }

    // private val todaysStatisticsObject from repository
    private var todaysStatistic: StatisticModelDomain? = null

    fun update() {
        getTodayItemIfExists()
    }

    fun onCountUp(){
        counter++
        updateLiveCounter()
        Log.d("ViewModel","Countup called, counter is $counter")
    }

    fun onCountDown(){
        if (counter > 0) counter--
        updateLiveCounter()
        Log.d("ViewModel","CountDown called, counter is $counter")

    }

    private fun updateLiveCounter() {
        _counter.postValue(counter)
    }

    private fun getTodayItemIfExists(){
        // get latest from database
        // if its for today, display the number
        // if not, display 0
        viewModelScope.launch {
            val today = repository.getTodaysEntryOrNull()
            Log.d("ViewModel", "today was fetched: $today")
            if (today != null){
                counter = today.clicks
                todaysStatistic = today
            }
            updateLiveCounter()
        }
    }

    fun doSaving(){
        val tempItem = todaysStatistic
        val item = if (tempItem == null){
            StatisticModelDomain(
                date = getTodayAsLong(),
                clicks = counter
            )
        } else {
            tempItem.copy(
                clicks = counter
            )
        }


        val oneTimeRequest = OneTimeWorkRequestBuilder<StatisticsWorker>()
            .setInputData(item.createWorkerPackage())
            .setInitialDelay(0, TimeUnit.MILLISECONDS)
            .build()
        workManager.enqueue(oneTimeRequest)
    }

}

class ClickViewModelFactory(
    private val context: Context

) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(context) as T
            modelClass.isAssignableFrom(ChartViewModel::class.java) -> ChartViewModel(context) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}