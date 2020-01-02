package com.nijhoomt.ntrental.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create


private const val BASE_URL = "http://rjtmobile.com/aamir/property-mgmt/"
private const val GEOCODING_BASE_URL = "https://maps.googleapis.com/maps/api/geocode/"
private const val DIRECTION_BASE_URL = "https://maps.googleapis.com/maps/api/directions/"

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

private val retrofitDirection = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(DIRECTION_BASE_URL)
    .build()

// Same (Can be reused for different calls as long as it returns "JSON" object)
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

private val retrofitAddTenant = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

// ===========================================================================
// Unused but consider to
//private val retrofitRxJavaRegister = Retrofit.Builder()
//
//    /** Add converter factory for serialization and deserialization of objects. */
//    .addConverterFactory(ScalarsConverterFactory.create())
//    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//    .baseUrl(BASE_URL)
//    .build()
// ===========================================================================

object PropertyManagementAPI {

    val retrofitLoginService: APIInterface by lazy {
        retrofitLogin.create(APIInterface::class.java)
    }

    val retrofitRegisterService: APIInterface by lazy {
        retrofitRegister.create(APIInterface::class.java)
    }

// ===========================================================================
// Unused but consider to
//    val retrofitRxJavaRegisterService: APIInterface by lazy {
//        retrofitRxJavaRegister.create(APIInterface::class.java)
//    }
// ===========================================================================

    // Geocoding
    val retrofitGeocodingService: APIInterface by lazy {
        retrofitGeocoding.create(APIInterface::class.java)
    }

    // Direction
    val retrofitDirectionService: APIInterface by lazy {
        retrofitDirection.create(APIInterface::class.java)
    }

    // Same (Can be reused for different calls as long as it returns "JSON" object)
    val retrofitService: APIInterface by lazy {
        retrofit.create(APIInterface::class.java)
    }

    val retrofitAddTenantService: APIInterface by lazy {
        retrofitAddTenant.create(APIInterface::class.java)
    }
}