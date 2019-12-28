package com.nijhoomt.ntrental.register

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nijhoomt.ntrental.model.RegisterCredential
import com.nijhoomt.ntrental.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PageViewModel(
    registerCredential: RegisterCredential,
    application: Application
) : ViewModel() {

//    private val _index = MutableLiveData<Int>()
//    val text: LiveData<String> = Transformations.map(_index) {
//        "Hello world from section: $it"
//    }

    private var repository = Repository(application)

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
        initiateRegister(registerCredential)
    }

    private fun initiateRegister(registerCredential: RegisterCredential) {

        val call = repository.createNewUser(registerCredential)

        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("PageVM", "Failed to register user: ${t.message}")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                _responseMessage.value = response.body()
            }
        })

//        val call = PropertyManagementAPI.retrofitRxJavaRegisterService
//            .postNewUserRxJavaAsync(
//                email = registerCredential.email,
//                landlord_email = registerCredential.landlored_email,
//                password = registerCredential.password,
//                account_for = registerCredential.account_for)
//
//        call.observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe(object : SingleObserver<String> {
//                override fun onSuccess(successString: String) {
//                    _responseMessage.value = successString
//                }
//
//                override fun onSubscribe(d: Disposable) {
//                }
//
//                override fun onError(e: Throwable) {
//                    Log.e("PageVM", "Failed to register user: ${e.message}")
//                }
//            })
//
//         val call needs to be clear() or dispose if you plan to use it
    }
}