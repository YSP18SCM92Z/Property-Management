package com.nijhoomt.ntrental.login

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nijhoomt.ntrental.model.LoginObject
import com.nijhoomt.ntrental.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by N & T on 12/22/2019.
 * Under instructions of Varun, Manisha, Ansari, & Rahul
 */
class LoginViewModel @Inject constructor(
    private val application: Application
) : ViewModel() {

    private val repository = Repository(application)

    private var _loginObject = MutableLiveData<LoginObject>()

    val loginObject: LiveData<LoginObject>
        get() = _loginObject

    internal fun initiateLogin(email: String, password: String) {

        val call = repository.signUserIn(email, password)

        call.enqueue(object : Callback<LoginObject>{
            override fun onFailure(call: Call<LoginObject>, t: Throwable) {
                Log.e("LoginVM", "Failed to login: ${t.message}")
//                Toast.makeText(
//                    application,
//                    "LoginVM: Failed to login: ${t.message}",
//                    Toast.LENGTH_LONG
//                ).show()
            }

            override fun onResponse(call: Call<LoginObject>, response: Response<LoginObject>) {
                _loginObject.value = response.body()
                Toast.makeText(
                    application,
                    "Login Success!",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}