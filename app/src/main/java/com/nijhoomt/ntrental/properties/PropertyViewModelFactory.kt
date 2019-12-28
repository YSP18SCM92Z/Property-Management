package com.nijhoomt.ntrental.properties

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nijhoomt.ntrental.model.UserId

class PropertyViewModelFactory(
    private val userId: UserId,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PropertyViewModel::class.java)){
            return PropertyViewModel(userId, application) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}