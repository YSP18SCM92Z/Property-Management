package com.nijhoomt.ntrental.properties

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

class PropertyViewModel(
    userId: UserId,
    application: Application
) : ViewModel() {

    private val repository = Repository(application)

    private var _propertyList = MutableLiveData<List<Property>>()

    val property: LiveData<List<Property>>
        get() = _propertyList

    init {
        getPropertyList(userId)
//        getPropertyList(UserId("3", "landlord"))
    }

    private fun getPropertyList(userId: UserId) {
        val call = repository.getPropertyList(userId)

        call.enqueue(object : Callback<PropertyObject> {
            override fun onFailure(call: Call<PropertyObject>, t: Throwable) {
                Log.e("Nijhoom", t.message)
            }

            override fun onResponse(
                call: Call<PropertyObject>,
                response: Response<PropertyObject>
            ) {
                //
                _propertyList.value = response.body()?.Property
            }

        })
    }

}