package com.nijhoomt.ntrental.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nijhoomt.ntrental.model.RegisterCredential

class PageViewModelFactory(
    private val registerCredential: RegisterCredential
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PageViewModel::class.java)) {
            return PageViewModel(registerCredential) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}