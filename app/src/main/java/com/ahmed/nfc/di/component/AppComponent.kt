package com.ahmed.nfc.di.component

import android.app.Application
import com.ahmed.nfc.MyApp
import com.ahmed.nfc.di.builder.ActivityBuilder
import com.ahmed.nfc.di.builder.FragmentBuilder
import com.ahmed.nfc.di.module.AppModule
import com.ahmed.nfc.di.module.DbModule
import com.ahmed.nfc.di.module.NetworkModule
import com.ahmed.nfc.di.module.RepoModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [(AndroidInjectionModule::class), (NetworkModule::class), (DbModule::class), (AppModule::class), (RepoModule::class),
    (ActivityBuilder::class),(FragmentBuilder::class)])
interface AppComponent {
    fun inject(myApp: MyApp)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}