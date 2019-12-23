package com.nijhoomt.ntrental.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://rjtmobile.com/aamir/property-mgmt/"

private val retrofit = Retrofit.Builder()

    /** Add converter factory for serialization and deserialization of objects. */
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object PropertyManagementAPI {
    val retrofitService: APIInterface by lazy {
        retrofit.create(APIInterface::class.java)
    }
}