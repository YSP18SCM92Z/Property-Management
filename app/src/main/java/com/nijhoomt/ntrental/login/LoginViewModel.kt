package com.nijhoomt.ntrental.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nijhoomt.ntrental.model.LoginCredential
import com.nijhoomt.ntrental.model.LoginObject
import com.nijhoomt.ntrental.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by N & T on 12/22/2019.
 * Under instructions of Varun, Manisha, Ansari, & Rahul
 */
class LoginViewModel(
    loginCredential: LoginCredential,
    application: Application
) : ViewModel() {

    private val repository = Repository(application)

    private var _loginObject = MutableLiveData<LoginObject>()

    val loginObject: LiveData<LoginObject>
        get() = _loginObject

    init {
        initiateLogin(loginCredential)
    }

    private fun initiateLogin(loginCredential: LoginCredential) {
//        val call = PropertyManagementAPI
//            .retrofitLoginService
//            .postUserAsync(
//                password = loginCredential.password,
//                email = loginCredential.email)
//
        val call = repository.signUserIn(loginCredential)

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