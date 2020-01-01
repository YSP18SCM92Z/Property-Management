package com.nijhoomt.ntrental.properties

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.nijhoomt.ntrental.model.Property
import com.nijhoomt.ntrental.model.UserId
import com.nijhoomt.ntrental.repository.Repository

class PropertyViewModel(
    userId: UserId,
    application: Application
) : ViewModel() {

    private val repository = Repository(application)

    private val _userId = MutableLiveData<UserId>()

    val propertyList: LiveData<List<Property>> = Transformations.switchMap(_userId){
        repository.getPropertyList(it)
    }

    fun setUserId(userId: UserId){
        val updated_user = userId

        if(_userId.value == updated_user){

            return
        }

        _userId.value = updated_user

    }

    fun cancelJobs(){

        repository.cancelJob()
    }






    /*init {
        getPropertyList(userId)
    }*/


    /*private fun getPropertyList(userId: UserId) {
        val call = repository.getPropertyList(userId)

        call.enqueue(object : Callback<PropertyObject> {
            override fun onFailure(call: Call<PropertyObject>, t: Throwable) {
                Log.e("PropertyVM", "Failed to get property list: ${t.message}")
            }

            override fun onResponse(
                call: Call<PropertyObject>,
                response: Response<PropertyObject>
            ) {
                _propertyList.value = response.body()?.Property
            }
        })
    }*/

}