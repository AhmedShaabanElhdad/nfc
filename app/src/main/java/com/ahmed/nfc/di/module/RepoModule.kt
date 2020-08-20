package com.jai.blueprint.di.module

import androidx.work.WorkerFactory
import com.jai.blueprint.data.datamanager.DataManager
import com.jai.blueprint.data.source.NetworkCall
import com.jai.blueprint.data.source.dao.TransactionDao
import com.jai.blueprint.data.work.DaggerWorkerFactory
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