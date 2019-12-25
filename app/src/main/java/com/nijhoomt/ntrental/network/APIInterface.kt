package com.nijhoomt.ntrental.network

import com.nijhoomt.ntrental.properties.Property
import com.nijhoomt.ntrental.properties.PropertyObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val FILE_NAME_LOGIN = "pro_mgt_login.php"
private const val FILE_NAME_REGISTER = "pro_mgt_reg.php"
private const val FILE_NAME_PROPERTY_LIST = "property.php"

interface APIInterface {

    @GET("$FILE_NAME_LOGIN")
    fun postUserAsync(
        @Query("email") email: String,
        @Query("password") password: String
    ) : Call<LoginObject>

    @GET("$FILE_NAME_REGISTER")
    fun postNewUserAsync(
        @Query("email") email: String,
        @Query("landlord_email") landlord_email: String,
        @Query("password") password: String,
        @Query("account_for") account_for: String
    ) : Call<String>

//    @POST("$FILE_NAME_REGISTER")
//    fun postNewUserRxJavaAsync(
//        @Query("email") email: String,
//        @Query("landlord_email") landlord_email: String,
//        @Query("password") password: String,
//        @Query("account_for") account_for: String
//    ) : Single<String>

    @GET("$FILE_NAME_PROPERTY_LIST")
    fun getPropertyListAsync(

        @Query("userid") userid: String,
        @Query("usertype") usertype: String

        ) : Call<PropertyObject>
}


/*
@Query("id") id: String,
@Query("propertyaddress") propertyaddress: String,
@Query("propertycity") propertycity: String,
@Query("propertystate") propertystate: String,
@Query("propertycountry") propertycountry: String,
@Query("propertystatus") propertystatus: String,
@Query("propertypurchaseprice") propertypurchaseprice: String,
@Query("propertymortageinfo") propertymortageinfo: String,
@Query("propertylatitude") propertylatitude: String,
@Query("propertylongitude") propertylongitude: String*/
