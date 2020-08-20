package com.jai.blueprint.ui.fragment.home

import android.util.Log
import androidx.lifecycle.LiveData
import com.jai.blueprint.data.datamanager.DataManager
import com.jai.blueprint.data.model.ResponseTransaction
import com.jai.blueprint.data.model.Transaction
import com.jai.blueprint.data.network.NetworkResult
import com.jai.blueprint.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject


class HomeViewModel @Inject constructor(val dataManager: DataManager) : BaseViewModel(dataManager) {

    suspend fun fetchTransactionFromRemote(): Pair<Int, String> {

        return withContext(Dispatchers.IO) {
            var msg: Pair<Int, String>
            val data = dataManager.fetchTransactionData()
            Log.e("data", "$data")
            when ((data as NetworkResult<Any>)) {
                is NetworkResult.Success<Any> -> {
                    dataManager.updateTransactionDB(((data as NetworkResult.Success<*>).data as ResponseTransaction).data)
                    msg = Pair(0, "")
                }

                // error in api calling
                is NetworkResult.Error -> {
                    msg = Pair(
                        1,
                        ((data as NetworkResult.Error).error as IOException).message.toString()
                    )
                }
            }
            msg

            msg = Pair(0, "")
            msg
        }


    }

    suspend fun fetchDataFromDatabase(): LiveData<List<Transaction>> {
        return withContext(Dispatchers.IO) {
            dataManager.transactionDao.fetchAllData()
        }
    }

}
