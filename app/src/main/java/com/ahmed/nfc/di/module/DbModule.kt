package com.ahmed.nfc.di.module

import android.content.Context
import androidx.room.Room
import com.ahmed.nfc.data.source.LocalDb
import com.ahmed.nfc.data.source.dao.TransactionDao
import com.ahmed.nfc.utils.AppConstant
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    //
    @Provides
    @Singleton
    internal fun provideDb(context: Context): LocalDb {
        return Room.databaseBuilder(context, LocalDb::class.java, AppConstant.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    internal fun provideTransactionDao(context: Context): TransactionDao {
        return provideDb(context).transactionDao()
    }

}