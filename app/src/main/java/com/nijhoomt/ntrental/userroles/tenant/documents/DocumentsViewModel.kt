package com.nijhoomt.ntrental.userroles.tenant.documents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DocumentsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Documents Fragment for Tenant"
    }
    val text: LiveData<String> = _text
}