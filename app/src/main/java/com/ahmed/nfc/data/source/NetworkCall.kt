package com.ahmed.nfc.data.source

import com.ahmed.nfc.data.model.ResponseTransaction
import com.ahmed.nfc.utils.AppConstant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface NetworkCall {

    @GET(AppConstant.API_TRANSACTIOS)
    suspend fun getTransactionData(@Query(AppConstant.API_TOKEN_KEY) value: String): Response<ResponseTransaction>


    @GET(AppConstant.API_ADDTRANSACTION)
    suspend fun addTransaction(
        @Query(AppConstant.API_TOKEN_KEY) token: String,
        @Query(AppConstant.TYPE) type: String,
        @Query(AppConstant.COUNTRY) country: String,
        @Query(AppConstant.CRYPTOGRAM) cryptogram: Int,
        @Query(AppConstant.VALUE) value: Int
    ): Response<ResponseTransaction>


}