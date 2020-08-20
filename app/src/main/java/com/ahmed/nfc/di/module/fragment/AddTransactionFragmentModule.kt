package com.ahmed.nfc.di.module.fragment

import androidx.lifecycle.ViewModelProvider
import com.ahmed.nfc.data.datamanager.DataManager
import com.ahmed.nfc.data.model.Transaction
import com.ahmed.nfc.ui.fragment.addTransaction.AddTransactionViewModel
import com.ahmed.nfc.utils.ViewModelProviderFactory
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