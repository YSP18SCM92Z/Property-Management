package com.nijhoomt.ntrental.properties.tenants

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nijhoomt.ntrental.model.*
import com.nijhoomt.ntrental.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PropertyTenantsViewModel(
    landlordId: String,
    application: Application
) : ViewModel() {

    private val repository = Repository(application)

    private var _listOfTenantsOfChosenLandlord = MutableLiveData<List<Tenant>>()

    val listOfTenantsOfChosenLandlord: LiveData<List<Tenant>>
        get() = _listOfTenantsOfChosenLandlord

    init {
        getListOfTenantsOfChosenLandlord(landlordId)
    }

    private fun getListOfTenantsOfChosenLandlord(landlordId: String) {
        val call = repository.getListOfTenantsOfChosenLandlord(landlordId)

        call.enqueue(object : Callback<TenantObject> {
            override fun onFailure(call: Call<TenantObject>, t: Throwable) {
                Log.e("PropertyTenantsVM", t.message)
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