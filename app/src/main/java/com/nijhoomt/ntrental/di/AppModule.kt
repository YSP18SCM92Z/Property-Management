package com.nijhoomt.ntrental.di

import dagger.Module
import dagger.Provides

/**
 * Anything that can exist but will not change over the course of application's life time
 * Retrofit Instance
 * Glide Instance
 * ...
 */
@Module
abstract class AppModule {

    @Module
    companion object AppModule {

        @JvmStatic
        @Provides
        fun provideSomeString() = "This is a test String"
    }
}