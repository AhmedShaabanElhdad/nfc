package com.ahmed.nfc.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmed.nfc.data.model.Transaction
import com.ahmed.nfc.data.source.dao.TransactionDao
import javax.inject.Singleton


@Singleton
@Database(entities = [Transaction::class], version = 1, exportSchema = false)
abstract class LocalDb : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}
