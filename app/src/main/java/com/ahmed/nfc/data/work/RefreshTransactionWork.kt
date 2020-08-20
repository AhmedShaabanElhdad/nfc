package com.ahmed.nfc.data.work

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.ahmed.nfc.data.datamanager.DataManager
import com.ahmed.nfc.utils.AppConstant
import kotlinx.coroutines.runBlocking

class RefreshTransactionWork(context: Context, params: WorkerParameters) : Worker(context, params) {

    lateinit var dataManager: DataManager


    override fun doWork(): Result {
        return try {
            runBlocking {
//                dataManager.insertData(dataManager.networkCall.geLocation().body()!!.data)
                Log.d(AppConstant.DEBUG_TAG, "Every thing is going on ok")
                Result.success()
            }
        } catch (e: Throwable) {
            Result.failure()
        }
    }


}