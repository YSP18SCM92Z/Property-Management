package com.nijhoomt.ntrental.di.viewmodels.auth

import android.app.Application
import androidx.lifecycle.ViewModel
import com.nijhoomt.ntrental.di.viewmodels.ViewModelKey
import com.nijhoomt.ntrental.login.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class LoginViewModelsModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey(LoginViewModel::class)
//    internal abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel


    @Provides
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal fun provideLoginViewModel(application: Application): ViewModel {
        return LoginViewModel(application)
    }
}