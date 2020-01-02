package com.nijhoomt.ntrental.forgotpassword

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nijhoomt.ntrental.model.ForgotPasswordCred
import com.nijhoomt.ntrental.model.ForgotPasswordObject
import com.nijhoomt.ntrental.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordViewModel(
    forgotPasswordCred: ForgotPasswordCred,
    private val application: Application
) : ViewModel() {

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
                Log.e("ForgotPasswordVM", "Failed to get forgotten password: ${t.message}")
//                Toast.makeText(
//                    application,
//                    "ForgotPasswordVM: Failed to login: ${t.message}",
//                    Toast.LENGTH_LONG
//                ).show()
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