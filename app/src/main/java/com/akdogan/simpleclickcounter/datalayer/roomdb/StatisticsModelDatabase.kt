package com.akdogan.simpleclickcounter.datalayer.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.akdogan.simpleclickcounter.datalayer.StatisticModelDomain

@Entity(tableName = "statistics_table")
data class StatisticsModelDatabase(
    @PrimaryKey val date: Long,
    @ColumnInfo(name = "clicks") val clicks: Long
)

fun StatisticsModelDatabase.toDomain() : StatisticModelDomain = StatisticModelDomain(
    //databaseId = uid,
    date = date,
    clicks = clicks
)

fun StatisticModelDomain.toDatabase() : StatisticsModelDatabase {
    /*return if (databaseId == null) {
        StatisticsModelDatabase(
            date = date,
            clicks = clicks
        )
    } else {
        StatisticsModelDatabase(
            //uid = databaseId,
            date = date,
            clicks = clicks
        )
    }*/
    return StatisticsModelDatabase(
        date = date,
        clicks = clicks
    )
}
