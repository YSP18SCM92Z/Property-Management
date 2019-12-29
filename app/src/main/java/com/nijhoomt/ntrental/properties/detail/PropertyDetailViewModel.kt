package com.nijhoomt.ntrental.properties.detail

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nijhoomt.ntrental.model.DirectionObject
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
    @NonNull internal val application: Application
) : AndroidViewModel(application) {

    private val repository = Repository(application)

    private var _hasDeleteProperty = MutableLiveData<Boolean>()
    val hasDeleteProperty: LiveData<Boolean>
        get() = _hasDeleteProperty

    private var _directionObject = MutableLiveData<DirectionObject>()
    val directionObject: LiveData<DirectionObject>
        get() = _directionObject

    internal fun removeProperty(propertyId: String) {
        val call = repository.removeProperty(propertyId)

        call.enqueue(object : Callback<Message> {
            override fun onFailure(call: Call<Message>, t: Throwable) {
                Log.e("PropertyDetailVM", t.message)
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

    internal fun getDirection(
        yourLatLngLocation: String,
        destinationLatLngLocation: String,
        mode: String
    ) {
        val call = repository.getDirection(
            yourLatLngLocation = yourLatLngLocation,
            destinationLatLngLocation = destinationLatLngLocation,
            mode = mode
        )

        call.enqueue(object : Callback<DirectionObject> {
            override fun onFailure(call: Call<DirectionObject>, t: Throwable) {
                Log.e("PropertyDetailVM", t.message)
                Toast.makeText(application, "${t.message}", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<DirectionObject>,
                response: Response<DirectionObject>
            ) {
                _directionObject.value = response.body()
//                Toast.makeText(
//                    application,
//                    "${response.body().toString()}",
//                    Toast.LENGTH_LONG
//                ).show()
            }
        })
    }
}