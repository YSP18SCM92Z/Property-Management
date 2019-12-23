package com.nijhoomt.ntrental.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nijhoomt.ntrental.model.LoginCredential

class LoginViewModelFactory(
    private val loginCredential: LoginCredential,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(loginCredential, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}