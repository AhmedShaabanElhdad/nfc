package com.ahmed.nfc.di.builder

import com.ahmed.nfc.di.module.fragment.HomeFragmentModule
import com.ahmed.nfc.di.module.fragment.AddTransactionFragmentModule
import com.ahmed.nfc.ui.fragment.home.HomeFragment
import com.ahmed.nfc.ui.fragment.addTransaction.AddTransactionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by JAI on 13,November,2019
 * JAI KHAMBHAYTA
 */
@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector(modules = [(HomeFragmentModule::class)])
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [(AddTransactionFragmentModule::class)])
    abstract fun bindProfileFragment(): AddTransactionFragment
}