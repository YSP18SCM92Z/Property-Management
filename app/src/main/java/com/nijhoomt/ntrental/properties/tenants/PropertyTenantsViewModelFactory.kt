package com.nijhoomt.ntrental.properties.tenants

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nijhoomt.ntrental.model.UserId
import com.nijhoomt.ntrental.properties.detail.PropertyDetailViewModel

class PropertyTenantsViewModelFactory(
    private val landlordId: String,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PropertyDetailViewModel::class.java)){
            return PropertyDetailViewModel(landlordId, application) as T
        }

        throw IllegalArgumentException("Unknown viewmodel class") as Throwable
    }
}