package com.jai.blueprint.data.datamanager

import android.util.Log
import com.jai.blueprint.data.model.Transaction
import com.jai.blueprint.data.source.NetworkCall
import com.jai.blueprint.data.source.dao.TransactionDao
import com.jai.blueprint.ui.base.BaseRepository
import com.jai.blueprint.utils.AppConstant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(
    val transactionDao: TransactionDao,
    val networkCall: NetworkCall
) : BaseRepository() {

    /**
     * API call
     */

    suspend fun fetchTransactionData(): Any {
        val data =
            safeApiCall({ networkCall.getTransactionData(AppConstant.API_TOKEN_VALUE) }, "No response")
        return data!!
    }

    suspend fun insertTransactionData(type:String,country:String,cryptogram:Int,value:Int): Any {
        val data =
            safeApiCall({ networkCall.addTransaction(AppConstant.API_TOKEN_VALUE,type,country,cryptogram,value) }, "No response")
        return data!!
    }


    /**
     * DATABASE Manage
     */

    fun updateTransactionDB(data: MutableList<Transaction>): Any {
        transactionDao.deleteAll()
        val result = transactionDao.insertAll(data)
        return result
    }

    fun insertTransactionDB(data: Transaction): Any {
        val result = transactionDao.insert(data)
        return result
    }

    fun fetchTransactionDB(): Any {
        return transactionDao.fetchAllData()
    }


}