package com.nijhoomt.ntrental.model

data class ForgotPasswordObject(
    var useremail: String,
    var userpassword: String
)

data class ForgotPasswordCred(
    val useremail: String
)