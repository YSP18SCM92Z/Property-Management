package com.nijhoomt.ntrental.di.viewmodels

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    // Providing an instance of ViewModelProvidersFactory
    @Binds
    abstract fun bindViewModelFactory(viewModelProvidersFactory: ViewModelProvidersFactory): ViewModelProvider.Factory
}