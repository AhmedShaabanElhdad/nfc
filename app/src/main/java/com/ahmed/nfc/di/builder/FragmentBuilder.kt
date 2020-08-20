package com.jai.blueprint.di.builder

import com.jai.blueprint.di.module.fragment.HomeFragmentModule
import com.jai.blueprint.di.module.fragment.AddTransactionFragmentModule
import com.jai.blueprint.ui.fragment.home.HomeFragment
import com.jai.blueprint.ui.fragment.addTransaction.AddTransactionFragment
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