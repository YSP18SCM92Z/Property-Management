package com.nijhoomt.ntrental.properties.tenants.add_tenant_by_landlord

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nijhoomt.ntrental.model.AddTenantCred
import com.nijhoomt.ntrental.repository.Repository
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class AddTenantViewModel(
    addTenantCred: AddTenantCred,
    application: Application
) : ViewModel(){

    private var repository = Repository(application)

    private var _responseMessage = MutableLiveData<String>()
    val responseMessage: LiveData<String>
        get() = _responseMessage

    init{
        addTenantsByLandlord(addTenantCred)
    }

    private fun addTenantsByLandlord(addTenantCred: AddTenantCred) {
        val call = repository.addTenantsByLandlord(addTenantCred)

       call.enqueue(object : retrofit2.Callback<String>{
           override fun onFailure(call: Call<String>, t: Throwable) {
              Log.e("AddTenant", t.message)
           }

           override fun onResponse(call: Call<String>, response: Response<String>) {
               _responseMessage.value = response.body()
           }


       })
    }


}