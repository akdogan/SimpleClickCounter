package com.akdogan.simpleclickcounter.datalayer

import android.content.Context
import android.util.Log
import androidx.lifecycle.Transformations
import com.akdogan.simpleclickcounter.datalayer.roomdb.StatisticsDao
import com.akdogan.simpleclickcounter.datalayer.roomdb.StatisticsDatabase
import com.akdogan.simpleclickcounter.datalayer.roomdb.toDatabase
import com.akdogan.simpleclickcounter.datalayer.roomdb.toDomain
import com.akdogan.simpleclickcounter.extension.isToday

class Repository(
    context: Context
) {
    private val database: StatisticsDao = StatisticsDatabase.getInstance(context).statisticsDatabase

    suspend fun getTodaysEntryOrNull(): StatisticModelDomain?{
        val result = database.getLatestEntry()
        Log.d("Respository", "result from databasequery: $result ")
        return if (result?.date?.isToday() == true){
            result.toDomain()
        } else null
    }

    suspend fun saveEntry(item: StatisticModelDomain){
        if (checkIfItemExists(item)){
            database.update(item.toDatabase())
        } else {
            database.insert(item.toDatabase())
        }
    }

    suspend fun checkIfItemExists(item: StatisticModelDomain): Boolean{
        return database.checkIfItemExists(item.date) != null
    }

    fun getDataAsLiveData() = Transformations.map(database.getDataAsLiveData()){ databaseList ->
        databaseList.map{
            it.toDomain()
        }
    }
}