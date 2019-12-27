package com.nijhoomt.ntrental.properties.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by N & T on 12/26/2019.
 * Under instructions of Varun, Manisha, Ansari, & Rahul
 */
class AddPropertyViewModelFactory(
    private val formattedAddress: String,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddPropertyViewModel::class.java)) {
            return AddPropertyViewModel(formattedAddress, application) as T
        }

        throw IllegalArgumentException("Unknown viewmodel class") as Throwable
    }
}