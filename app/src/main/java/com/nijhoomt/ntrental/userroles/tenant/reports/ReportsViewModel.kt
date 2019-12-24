package com.nijhoomt.ntrental.userroles.tenant.reports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReportsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Reports Fragment for Tenant"
    }
    val text: LiveData<String> = _text
}