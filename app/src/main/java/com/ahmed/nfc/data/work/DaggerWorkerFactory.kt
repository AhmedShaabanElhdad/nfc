package com.ahmed.nfc.data.work

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.ahmed.nfc.data.datamanager.DataManager

class DaggerWorkerFactory(private val dataManager: DataManager) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        val workerKlass = Class.forName(workerClassName).asSubclass(ListenableWorker::class.java)
        val constructor =
            workerKlass.getDeclaredConstructor(Context::class.java, WorkerParameters::class.java)
        val instance = constructor.newInstance(appContext, workerParameters)

        when (instance) {
            is RefreshTransactionWork -> {
                instance.dataManager = dataManager
            }
            // optionally, handle other workers
        }

        return instance
    }
}