package com.nijhoomt.ntrental.userroles.landlord.documents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DocumentsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Documents Fragment"
    }
    val text: LiveData<String> = _text
}