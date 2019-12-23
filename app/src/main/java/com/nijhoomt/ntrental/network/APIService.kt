package com.nijhoomt.ntrental.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
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

object PropertyManagementAPI {
    val retrofitLoginService: APIInterface by lazy {
        retrofitLogin.create(APIInterface::class.java)
    }
    val retrofitRegisterService: APIInterface by lazy {
        retrofitRegister.create(APIInterface::class.java)
    }
}