package com.nijhoomt.ntrental.register

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nijhoomt.ntrental.model.RegisterCredential

class PageViewModelFactory(
    private val registerCredential: RegisterCredential,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PageViewModel::class.java)) {
            return PageViewModel(registerCredential, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}