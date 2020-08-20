package com.ahmed.nfc.di.module.activity

import androidx.lifecycle.ViewModelProvider
import com.ahmed.nfc.data.datamanager.DataManager
import com.ahmed.nfc.ui.activity.splash.SplashViewModel
import com.ahmed.nfc.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

/**
 * Created by JAI on 18,November,2019
 * JAI KHAMBHAYTA
 */
@Module
class SplashActivityModule {

    @Provides
    internal fun provideSplasViewModel(dataManager: DataManager): SplashViewModel {
        return SplashViewModel(dataManager)
    }

    @Provides
    internal fun mainActivityModelProvider(splashViewModel: SplashViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(splashViewModel)
    }


}