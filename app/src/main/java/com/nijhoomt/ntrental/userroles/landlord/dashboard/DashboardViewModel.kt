package com.nijhoomt.ntrental.userroles.landlord.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Dashboard Fragment for Landlord"
    }
    val text: LiveData<String> = _text
}