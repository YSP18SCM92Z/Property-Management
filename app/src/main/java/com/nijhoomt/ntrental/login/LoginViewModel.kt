package com.nijhoomt.ntrental.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nijhoomt.ntrental.model.LoginCredential
import com.nijhoomt.ntrental.network.LoginObject
import com.nijhoomt.ntrental.network.PropertyManagementAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by N & T on 12/22/2019.
 * Under instructions of Varun, Manisha, Ansari, & Rahul
 */
class LoginViewModel(
    private val loginCredential: LoginCredential,
    private val application: Application
) : ViewModel() {

    private var _loginObject = MutableLiveData<LoginObject>()

    val loginObject: LiveData<LoginObject>
        get() = _loginObject

    init {
        initiateLogin()
    }

    private fun initiateLogin() {
        val call = PropertyManagementAPI
            .retrofitLoginService
            .postUserAsync(
                password = loginCredential.password,
                email = loginCredential.email)

        call.enqueue(object : Callback<LoginObject>{
            override fun onFailure(call: Call<LoginObject>, t: Throwable) {
                Log.e("Nijhoom", t.message)
            }

            override fun onResponse(call: Call<LoginObject>, response: Response<LoginObject>) {
                _loginObject.value = response.body()
            }

        })
    }
}