package com.nijhoomt.ntrental.properties.tenants

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PropertyTenantsViewModelFactory(
    private val landlordId: String,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PropertyTenantsViewModel::class.java)) {
            return PropertyTenantsViewModel(landlordId, application) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}