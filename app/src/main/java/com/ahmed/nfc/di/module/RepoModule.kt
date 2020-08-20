package com.ahmed.nfc.di.module

import androidx.work.WorkerFactory
import com.ahmed.nfc.data.datamanager.DataManager
import com.ahmed.nfc.data.source.NetworkCall
import com.ahmed.nfc.data.source.dao.TransactionDao
import com.ahmed.nfc.data.work.DaggerWorkerFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    internal fun provideMovieRepository(transactionDao: TransactionDao, networkCall: NetworkCall): DataManager {
        return DataManager(transactionDao, networkCall)
    }

    @Provides
    @Singleton
    fun workerFactory(dataManager: DataManager): WorkerFactory {
        return DaggerWorkerFactory(dataManager)
    }


}