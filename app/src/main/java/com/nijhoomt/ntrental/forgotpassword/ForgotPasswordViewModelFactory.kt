package com.nijhoomt.ntrental.forgotpassword

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nijhoomt.ntrental.model.ForgotPasswordCred

class ForgotPasswordViewModelFactory(
    private val forgotPasswordCred: ForgotPasswordCred,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForgotPasswordViewModel::class.java)) {
            return ForgotPasswordViewModel(forgotPasswordCred, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}