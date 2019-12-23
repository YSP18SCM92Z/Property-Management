package com.nijhoomt.ntrental.network

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

private const val FILE_NAME_LOGIN = "pro_mgt_login.php"
private const val FILE_NAME_REGISTER = "pro_mgt_reg.php"

interface APIInterface {

    @POST("$FILE_NAME_LOGIN")
    fun postUserAsync(
        @Query("email") email: String,
        @Query("password") password: String
    )
            : Call<LoginObject>

    @POST("$FILE_NAME_REGISTER")
    fun postNewUserAsync(
        @Query("email") email: String,
        @Query("landlord_email") landlord_email: String,
        @Query("password") password: String,
        @Query("account_for") account_for: String

    ) : Call<String>

}