package com.akdogan.simpleclickcounter.datalayer.roomdb

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.akdogan.simpleclickcounter.datalayer.Repository
import com.akdogan.simpleclickcounter.datalayer.StatisticModelDomain

class StatisticsWorker(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params) {

    private val repository = Repository(context)

    override suspend fun doWork(): Result {
        val item = inputData.getItem()
        repository.saveEntry(item)
        return Result.success()
    }
}

fun StatisticModelDomain.createWorkerPackage(): Data {
    val parameters: MutableList<Pair<String, Any>> = mutableListOf()
    parameters.add(Pair(DATE_KEY, this.date))
    parameters.add(Pair(CLICKS_KEY, this.clicks))

    return workDataOf(*parameters.toTypedArray())
}

private fun Data.getItem(): StatisticModelDomain {
    return StatisticModelDomain(
        date = getLong(DATE_KEY, -1),
        clicks = getLong(CLICKS_KEY, -1)
    )
}

private const val UID_KEY = "UID_KEY"
private const val DATE_KEY = "DATE_KEY"
private const val CLICKS_KEY = "CLICKS_KEY"