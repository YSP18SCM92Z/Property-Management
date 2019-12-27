package com.nijhoomt.ntrental.properties.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nijhoomt.ntrental.model.Message
import com.nijhoomt.ntrental.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by N & T on 12/26/2019.
 * Under instructions of Varun, Manisha, Ansari, & Rahul
 */
class PropertyDetailViewModel(
    propertyId: String,
    application: Application
) : AndroidViewModel(application) {

    private val repository = Repository(application)

    private var _hasDeleteProperty = MutableLiveData<Boolean>()
    val hasDeleteProperty: LiveData<Boolean>
        get() = _hasDeleteProperty

//    init {
//        removeProperty(propertyId)
//    }

    internal fun removeProperty(propertyId: String) {
        val call = repository.removeProperty(propertyId)

        call.enqueue(object : Callback<Message> {
            override fun onFailure(call: Call<Message>, t: Throwable) {
                Log.e("Nijhoom", t.message)
                _hasDeleteProperty.value = false
            }

            override fun onResponse(
                call: Call<Message>,
                response: Response<Message>
            ) {
                _hasDeleteProperty.value = true
            }

        })
    }
}