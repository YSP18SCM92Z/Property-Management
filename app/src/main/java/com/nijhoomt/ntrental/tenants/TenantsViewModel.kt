package com.nijhoomt.ntrental.tenants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TenantsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Tenants Fragment"
    }
    val text: LiveData<String> = _text
}