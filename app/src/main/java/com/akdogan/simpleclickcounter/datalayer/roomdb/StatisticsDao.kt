package com.akdogan.simpleclickcounter.datalayer.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StatisticsDao {

    @Insert
    suspend fun insert(item: StatisticsModelDatabase)

    @Update
    suspend fun update(item: StatisticsModelDatabase)

    @Query("SELECT * from statistics_table ORDER BY date DESC LIMIT 1")
    suspend fun getLatestEntry(): StatisticsModelDatabase?

    @Query("SELECT * from statistics_table WHERE date = :date")
    suspend fun checkIfItemExists(date: Long): StatisticsModelDatabase?

    @Query("SELECT * from statistics_table")
    fun getDataAsLiveData(): LiveData<List<StatisticsModelDatabase>>

}