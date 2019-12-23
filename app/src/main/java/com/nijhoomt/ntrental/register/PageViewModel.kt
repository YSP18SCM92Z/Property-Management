package com.nijhoomt.ntrental.register

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nijhoomt.ntrental.model.RegisterCredential
import com.nijhoomt.ntrental.network.PropertyManagementAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PageViewModel(
    private val registerCredential: RegisterCredential
) : ViewModel() {

//    private val _index = MutableLiveData<Int>()
//    val text: LiveData<String> = Transformations.map(_index) {
//        "Hello world from section: $it"
//    }

    private var _responseMessage = MutableLiveData<String>()

    val responseMessage: LiveData<String>
        get() = _responseMessage


    private val _index = MutableLiveData<Int>()
    val index: LiveData<Int>
        get() = _index

    fun setIndex(index: Int) {
        _index.value = index
    }

    private val _tabTitle = MutableLiveData<String>()
    val tabTitle: LiveData<String>
        get() = _tabTitle

    fun setTabTitle(tabTitle: String?) {
        _tabTitle.value =  tabTitle
    }

    init {

        initiateRegister()
    }

    private fun initiateRegister() {
        val call = PropertyManagementAPI.retrofitService
            .postNewUserAsync(email = registerCredential.email,
                landlord_email = registerCredential.landlored_email,
                password = registerCredential.password,
                account_for = registerCredential.account_for)

        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
               Log.e("Nijhoom", t.message)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                _responseMessage.value = response.body()
            }

        })
    }

}