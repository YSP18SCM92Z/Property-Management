package com.nijhoomt.ntrental.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


private const val BASE_URL = "http://rjtmobile.com/aamir/property-mgmt/"

private val gson = GsonBuilder()
    .setLenient()
    .create()

private val retrofitLogin = Retrofit.Builder()

    /** Add converter factory for serialization and deserialization of objects. */
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .build()

private val retrofitRegister = Retrofit.Builder()

    /** Add converter factory for serialization and deserialization of objects. */
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

private val retrofitRxJavaRegister = Retrofit.Builder()

    /** Add converter factory for serialization and deserialization of objects. */
    .addConverterFactory(ScalarsConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .baseUrl(BASE_URL)
    .build()

private val retrofitPropertyList = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object PropertyManagementAPI {
    val retrofitLoginService: APIInterface by lazy {
        retrofitLogin.create(APIInterface::class.java)
    }
    val retrofitRegisterService: APIInterface by lazy {
        retrofitRegister.create(APIInterface::class.java)
    }
    val retrofitRxJavaRegisterService: APIInterface by lazy {
        retrofitRxJavaRegister.create(APIInterface::class.java)
    }

    val retrofitPropertyService: APIInterface by lazy {
        retrofitPropertyList.create(APIInterface::class.java)
    }
}