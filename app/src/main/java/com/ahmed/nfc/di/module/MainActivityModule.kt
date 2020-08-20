package com.ahmed.nfc.di.module

import androidx.lifecycle.ViewModelProvider
import com.ahmed.nfc.data.datamanager.DataManager
import com.ahmed.nfc.ui.activity.main.MainViewModel
import com.ahmed.nfc.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    internal fun provideMainViewModel(dataManager: DataManager): MainViewModel {
        return MainViewModel(dataManager)
    }

    @Provides
    internal fun mainActivityModelProvider(MainActivity: MainViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(MainActivity)
    }

}