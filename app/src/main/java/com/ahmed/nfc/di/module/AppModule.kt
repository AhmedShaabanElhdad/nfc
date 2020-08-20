package com.ahmed.nfc.di.module

import android.app.Application
import android.content.Context
import com.ahmed.nfc.data.source.preference.AppPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }


    @Provides
    @Singleton
    internal fun provideAppPreferenece(context: Context): AppPreferences {
        return AppPreferences(context)
    }


//    @Provides
//    @Singleton
//    internal fun provideCalligraphyDefaultConfig(): CalligraphyConfig {
//        return CalligraphyConfig.Builder()
//            .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
//            .setFontAttrId(R.attr.fontPath)
//            .build()
    }


