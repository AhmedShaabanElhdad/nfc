package com.ahmed.nfc.di.builder

import com.ahmed.nfc.ui.activity.main.MainActivity
import com.ahmed.nfc.di.module.MainActivityModule
import com.ahmed.nfc.di.module.activity.SplashActivityModule
import com.ahmed.nfc.ui.activity.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by JAI on 11,November,2019
 * JAI KHAMBHAYTA
 */
@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(SplashActivityModule::class)])
    internal abstract fun bindSplashActivity(): SplashActivity

}


