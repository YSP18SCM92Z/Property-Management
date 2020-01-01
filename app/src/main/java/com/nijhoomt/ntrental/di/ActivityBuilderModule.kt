package com.nijhoomt.ntrental.di

import com.nijhoomt.ntrental.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Annotates a class that contributes to the object graph.
 */
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity
}