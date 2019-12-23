package com.nijhoomt.ntrental.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginObject(
    @SerializedName("msg") val message: String,
    @SerializedName("userid") val userId: String,
    @SerializedName("usertype") val userType: String,
    @SerializedName("useremail") val userEmail: String,
    @SerializedName("appapikey") val appApiKey: String
) : Serializable
