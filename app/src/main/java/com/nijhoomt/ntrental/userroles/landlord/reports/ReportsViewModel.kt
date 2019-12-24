package com.nijhoomt.ntrental.userroles.landlord.reports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReportsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Reports Fragment for Landlord"
    }
    val text: LiveData<String> = _text
}