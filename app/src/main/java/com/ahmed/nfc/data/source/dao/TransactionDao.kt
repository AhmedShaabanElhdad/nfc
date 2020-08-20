package com.ahmed.nfc.data.source.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmed.nfc.data.model.Transaction

@Dao
interface TransactionDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(transaction: List<Transaction>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transaction: Transaction)

    @Query("DELETE FROM `transaction`")
    fun deleteAll()

    @Query("SELECT * FROM `transaction`")
    fun fetchAllData(): LiveData<List<Transaction>>

    @Query("SELECT count(*) from `transaction`")
    fun getSize():Int


}