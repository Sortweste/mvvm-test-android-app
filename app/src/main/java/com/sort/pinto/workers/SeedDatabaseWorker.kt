package com.sort.pinto.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.lang.Exception

/*Prepopulate Database when App is installed*/
@HiltWorker
class SeedDatabaseWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
) : CoroutineWorker(context, workerParameters) {

    /*This will run only once, when app is installed successfully*/
    override suspend fun doWork(): Result = coroutineScope {
        withContext(Dispatchers.IO){
            try {
                Result.success()
            } catch (e: Exception) {
                Result.failure()
            }
        }
    }
}
