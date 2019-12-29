package com.nijhoomt.ntrental.network


import com.nijhoomt.ntrental.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private const val FILE_NAME_LOGIN = "pro_mgt_login.php"
private const val FILE_NAME_REGISTER = "pro_mgt_reg.php"
private const val FILE_NAME_PROPERTY_LIST = "property.php"
private const val FILE_NAME_FORGOT_YOUR_PASSWORD = "pro_mgt_forgot_pass.php"
private const val FILE_NAME_ADD_PROPERTY = "pro_mgt_add_pro.php"
private const val FILE_NAME_REMOVE_PROPERTY = "remove-property.php"
private const val FILE_NAME_PROPERTY_TENANTS = "pro_mgt_tenent_details.php"
private const val FILE_NAME_ALL_PROPERTY_FOR_TENANTS = "pro_mgt_property_all.php"
private const val FILE_NAME_ADD_TENANTS_BY_PROPERTY = "pro_mgt_add_tenants.php"


private const val GEOCODING_OUTPUT_FORMAT = "json"  // xml or json

interface APIInterface {

    // GET/POST works the same way in this case (TESTED)
    @GET("$FILE_NAME_LOGIN")
    fun postUserAsync(
        @Query("email") email: String,
        @Query("password") password: String
    ) : Call<LoginObject>

    // GET/POST works the same way in this case (TESTED)
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
    ): Call<PropertyObject>

    @GET("$FILE_NAME_FORGOT_YOUR_PASSWORD")
    fun getForgottenPasswordAsync(
        @Query("email") email: String
    ) : Call<ForgotPasswordObject>

    @POST("$GEOCODING_OUTPUT_FORMAT")
    fun getLatLngObjectAsync(
        @Query("address") address: String,
        @Query("key") key: String
    ) : Call<GeocodingObject>

    @GET("$FILE_NAME_ADD_PROPERTY")
    fun addPropertyAsync(
        @Query("address") address: String,
        @Query("city") city: String,
        @Query("state") state: String,
        @Query("country") country: String,
        @Query("pro_status") pro_status: String,
        @Query("purchase_price") purchase_price: String,
        @Query("mortage_info") mortage_info: String,
        @Query("userid") userid: String,
        @Query("usertype") usertype: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String
    ): Call<Message>

    @GET("$FILE_NAME_REMOVE_PROPERTY")
    fun removePropertyAsync(
        @Query("propertyid") propertyid: String
    ): Call<Message>

    @GET("$FILE_NAME_PROPERTY_TENANTS")
    fun getListOfTenantsOfChosenLandlordAsync(
        @Query("landlordid") landlordid: String
    ): Call<TenantObject>


    @GET("$FILE_NAME_ALL_PROPERTY_FOR_TENANTS")
    fun getAllPropertiesForTenantsAsync(): Call<PropertyObject>

    @GET("$FILE_NAME_ADD_TENANTS_BY_PROPERTY")
    fun addTenantsByLandlordAsync(
        @Query("name") name: String,
        @Query("email") email: String,
        @Query("address") address: String,
        @Query("mobile") mobile: String,
        @Query("propertyid") propertyid: String,
        @Query("landlordid") landlordid: String
    ) : Call <String>

}




