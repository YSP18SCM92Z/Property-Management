package com.nijhoomt.ntrental.properties.tenants

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nijhoomt.ntrental.model.Tenant
import com.nijhoomt.ntrental.model.TenantObject
import com.nijhoomt.ntrental.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PropertyTenantsViewModel(
    landlordId: String,
    private val application: Application
) : ViewModel() {

    private val repository = Repository(application)

    private var _listOfTenantsOfChosenLandlord = MutableLiveData<List<Tenant>>()

    val listOfTenantsOfChosenLandlord: LiveData<List<Tenant>>
        get() = _listOfTenantsOfChosenLandlord

    init {
        getListOfTenantsOfChosenLandlord(landlordId)
//        getListOfTenantsOfChosenLandlord("32")
    }

    private fun getListOfTenantsOfChosenLandlord(landlordId: String) {
        val call = repository.getListOfTenantsOfChosenLandlord(landlordId)

        call.enqueue(object : Callback<TenantObject> {
            override fun onFailure(call: Call<TenantObject>, t: Throwable) {
                Log.e(
                    "PropertyTenantsVM",
                    "Failed to get a list of tenants for this chosen landlord: ${t.message}"
                )
                Toast.makeText(
                    application,
                    "PropertyTenantsVM: Failed to get a list of tenants for this chosen landlord: ${t.message}",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onResponse(
                call: Call<TenantObject>,
                response: Response<TenantObject>
            ) {
                _listOfTenantsOfChosenLandlord.value = response.body()?.listOfTenantsOfChosenLandlord
            }
        })
    }

}