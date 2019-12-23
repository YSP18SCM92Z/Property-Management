package com.nijhoomt.ntrental.network

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

private const val FILE_NAME_LOGIN = "pro_mgt_login.php"

interface APIInterface {

    @POST("$FILE_NAME_LOGIN")
    fun postUserAsync(
        @Query("email") email: String,
        @Query("password") password: String
    )
            : Call<LoginObject>

}