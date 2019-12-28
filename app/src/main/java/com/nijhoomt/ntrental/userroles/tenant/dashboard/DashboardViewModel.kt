package com.nijhoomt.ntrental.userroles.tenant.dashboard

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nijhoomt.ntrental.model.Property
import com.nijhoomt.ntrental.model.PropertyObject
import com.nijhoomt.ntrental.model.UserId
import com.nijhoomt.ntrental.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel(
    private val application: Application
) : ViewModel() {

    private val repository = Repository(application)

    private var _listOfAllPropertiesForTenants = MutableLiveData<List<Property>>()
    val listOfAllPropertiesForTenants: LiveData<List<Property>>
        get() = _listOfAllPropertiesForTenants

    init {
        getListOfAllPropertiesForTenants()
    }

    private fun getListOfAllPropertiesForTenants() {
        val call = repository.getAllPropertiesForTenants()

        call.enqueue(object : Callback<PropertyObject> {
            override fun onFailure(call: Call<PropertyObject>, t: Throwable) {
                Log.e("DashboardVM", "Failed to get property list: ${t.message}")
            }

            override fun onResponse(
                call: Call<PropertyObject>,
                response: Response<PropertyObject>
            ) {
                _listOfAllPropertiesForTenants.value = response.body()?.Property
            }
        })
    }

}