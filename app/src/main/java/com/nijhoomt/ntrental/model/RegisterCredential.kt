package com.nijhoomt.ntrental.model

data class RegisterCredential(
    var email: String,
    var landlored_email: String,
    var password: String,
    var account_for: String
)