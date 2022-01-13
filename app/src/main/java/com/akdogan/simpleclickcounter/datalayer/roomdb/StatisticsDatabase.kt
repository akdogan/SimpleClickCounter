package com.akdogan.simpleclickcounter.datalayer.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StatisticsModelDatabase::class], version = 1, exportSchema = false)
abstract class StatisticsDatabase : RoomDatabase() {

    abstract val statisticsDatabase: StatisticsDao

    companion object {

        @Volatile
        private var INSTANCE: StatisticsDatabase? = null

        fun getInstance(context: Context):StatisticsDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context,
                        StatisticsDatabase::class.java,
                        "statistics_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}