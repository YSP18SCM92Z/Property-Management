package com.nijhoomt.ntrental.network


import com.nijhoomt.ntrental.model.PropertyObject
import com.nijhoomt.ntrental.forgotpassword.ForgotPasswordObject
import com.nijhoomt.ntrental.model.GeocodingObject
import com.nijhoomt.ntrental.model.LoginObject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private const val FILE_NAME_LOGIN = "pro_mgt_login.php"
private const val FILE_NAME_REGISTER = "pro_mgt_reg.php"
private const val FILE_NAME_PROPERTY_LIST = "property.php"
private const val FILE_NAME_FORGOT_YOUR_PASSWORD = "pro_mgt_forgot_pass.php"

private const val GEOCODING_OUTPUT_FORMAT = "json"  // xml or json
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

    @GET("$FILE_NAME_FORGOT_YOUR_PASSWORD")
    fun getForgottenPasswordAsync(
        @Query("email") email: String
    ) : Call<ForgotPasswordObject>

    @POST("$GEOCODING_OUTPUT_FORMAT")
    fun getLatLngObjectAsync(

        @Query("address") address: String,
        @Query("key") key: String

    ) : Call<GeocodingObject>

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
