package com.jai.blueprint.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class ResponseTransaction(
    @SerializedName("transactions")
    val data: MutableList<Transaction>,

    @SerializedName("status")
    val status: Int,

    @SerializedName("msg")
    val msg: String,


    @SerializedName("date")
    val date: String,

    @SerializedName("id")
    val id: Int
)


@Entity(tableName = "transaction")
data class Transaction(
    
    @PrimaryKey
    var id: Int = 0,
    var type: String = "Refugee",
    var country:String = "France",
    var cryptogram: Int=100,
    var date: String="31/10/2020",
    var value: Int = -1


)