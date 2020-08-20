package com.ahmed.nfc.ui.fragment.addTransaction

import android.util.Log
import com.ahmed.nfc.data.datamanager.DataManager
import com.ahmed.nfc.data.model.ResponseTransaction
import com.ahmed.nfc.data.model.Transaction
import com.ahmed.nfc.data.network.NetworkResult
import com.ahmed.nfc.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class AddTransactionViewModel @Inject constructor(
    val dataManager: DataManager
) : BaseViewModel(dataManager) {


    suspend fun addTransactionToRemote(transaction: Transaction): Pair<Int, String> {

        return withContext(Dispatchers.IO) {
            var msg: Pair<Int, String>

            val data = dataManager.insertTransactionData(
                transaction.type,
                transaction.country,
                transaction.cryptogram,
                transaction.value
            )
            Log.e("data","$data")

            when ((data as NetworkResult<Any>)) {
                is NetworkResult.Success<Any> -> {
                    val result = ((data as NetworkResult.Success<*>).data as ResponseTransaction)
                    if (result.status == 1)
                        dataManager.insertTransactionDB(
                            Transaction(
                                result.id,
                                transaction.type,
                                transaction.country,
                                transaction.cryptogram,
                                result.date,
                                transaction.value
                            )
                        )
                    msg = Pair(0, result.msg)
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
}