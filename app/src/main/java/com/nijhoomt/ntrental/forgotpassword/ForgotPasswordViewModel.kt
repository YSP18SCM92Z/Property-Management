package com.nijhoomt.ntrental.forgotpassword

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nijhoomt.ntrental.network.LoginObject
import com.nijhoomt.ntrental.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordViewModel(forgotPasswordCred: ForgotPasswordCred, application: Application): ViewModel() {

    private val repository = Repository(application)

    private val _forgotPasswordObject = MutableLiveData<ForgotPasswordObject>()

    val forgotPasswordObject: LiveData<ForgotPasswordObject>
        get() = _forgotPasswordObject

    init {
        getForgottenPassword(forgotPasswordCred)
    }

    private fun getForgottenPassword(forgotPasswordCred: ForgotPasswordCred) {

        val call = repository.getForgottenPassword(forgotPasswordCred)

        call.enqueue(object: Callback<ForgotPasswordObject>{
            override fun onFailure(call: Call<ForgotPasswordObject>, t: Throwable) {
                Log.d("Failed", "Failed")
            }

            override fun onResponse(
                call: Call<ForgotPasswordObject>,
                response: Response<ForgotPasswordObject>
            ) {
                _forgotPasswordObject.value = response.body()
            }

        })

    }

}