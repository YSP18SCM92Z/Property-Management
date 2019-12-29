package com.nijhoomt.ntrental.properties.tenants.add_tenant_by_landlord

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nijhoomt.ntrental.model.AddTenantCred

class AddTenantViewModelFactory(
    private val addTenantCred: AddTenantCred,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddTenantViewModel::class.java)) {
            return AddTenantViewModel(addTenantCred, application) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}