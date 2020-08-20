package com.jai.blueprint.di.module.fragment

import androidx.lifecycle.ViewModelProvider
import com.jai.blueprint.data.datamanager.DataManager
import com.jai.blueprint.data.model.Transaction
import com.jai.blueprint.ui.fragment.addTransaction.AddTransactionViewModel
import com.jai.blueprint.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class AddTransactionFragmentModule {

    @Provides
    internal fun provideTransaction(): Transaction {
        return Transaction()
    }

    @Provides
    internal fun provideAddTransactionViewModel(dataManager: DataManager, transaction: Transaction): AddTransactionViewModel {
        return AddTransactionViewModel(dataManager,transaction)
    }


    @Provides
    fun provideViewModelFactory(addTransactionViewModel: AddTransactionViewModel): ViewModelProvider.Factory =
        ViewModelProviderFactory(addTransactionViewModel)

}