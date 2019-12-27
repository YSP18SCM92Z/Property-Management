package com.nijhoomt.ntrental.properties.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nijhoomt.ntrental.model.GeocodingObject
import com.nijhoomt.ntrental.model.LatLngObject
import com.nijhoomt.ntrental.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by N & T on 12/26/2019.
 * Under instructions of Varun, Manisha, Ansari, & Rahul
 */
class AddPropertyViewModel (
    formattedAddress: String,
    application: Application
) : AndroidViewModel(application) {

    private val repository = Repository(application)

    private var _latLngObject = MutableLiveData<LatLngObject>()
    val latLngObject: LiveData<LatLngObject>
        get() = _latLngObject

    private var _wellFormattedAddress = MutableLiveData<String>()
    val wellFormattedAddress: LiveData<String>
        get() = _wellFormattedAddress

    init {
//        getPropertyList(userId)
        getLatLngObject(formattedAddress)
    }

    private fun getLatLngObject(formattedAddress: String) {
        val call = repository.getLatLngObject(formattedAddress)

        call.enqueue(object : Callback<GeocodingObject> {
            override fun onFailure(call: Call<GeocodingObject>, t: Throwable) {
                Log.e("Nijhoom", t.message)
            }

            override fun onResponse(
                call: Call<GeocodingObject>,
                response: Response<GeocodingObject>
            ) {
                //
                _latLngObject.value = LatLngObject(
                    response.body()?.results!![0].geometry.location.lat,
                    response.body()?.results!![0].geometry.location.lng)

                _wellFormattedAddress.value = response.body()?.results!![0].formatted_address
            }

        })
    }

}