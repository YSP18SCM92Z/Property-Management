package com.nijhoomt.ntrental.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


private const val BASE_URL = "http://rjtmobile.com/aamir/property-mgmt/"
private const val GEOCODING_BASE_URL = "https://maps.googleapis.com/maps/api/geocode/"

private val gson = GsonBuilder()
    .setLenient()
    .create()

private val retrofitRegister = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

private val retrofitLogin = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .build()

private val retrofitGeocoding = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(GEOCODING_BASE_URL)
    .build()

// Same
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

// Unused but consider to
//private val retrofitRxJavaRegister = Retrofit.Builder()
//
//    /** Add converter factory for serialization and deserialization of objects. */
//    .addConverterFactory(ScalarsConverterFactory.create())
//    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//    .baseUrl(BASE_URL)
//    .build()

object PropertyManagementAPI {

    val retrofitLoginService: APIInterface by lazy {
        retrofitLogin.create(APIInterface::class.java)
    }

    val retrofitRegisterService: APIInterface by lazy {
        retrofitRegister.create(APIInterface::class.java)
    }

//    val retrofitRxJavaRegisterService: APIInterface by lazy {
//        retrofitRxJavaRegister.create(APIInterface::class.java)
//    }

    // Geocoding
    val retrofitGeocodingService: APIInterface by lazy {
        retrofitGeocoding.create(APIInterface::class.java)
    }

    val retrofitService: APIInterface by lazy {
        retrofit.create(APIInterface::class.java)
    }

}