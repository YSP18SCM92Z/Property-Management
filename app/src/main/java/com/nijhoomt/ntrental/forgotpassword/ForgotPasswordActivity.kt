package com.nijhoomt.ntrental.forgotpassword

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nijhoomt.ntrental.R
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        btn_request_password.setOnClickListener {

            val email = edit_text_email_forgotpassword

            if (email.text.toString() == "") {

                email.error = "Email field is required"
            }

            else{
                requestPassword(email.text.toString())
            }
        }
    }

    private fun requestPassword(email: String) {


        val forgotPasswordCred = ForgotPasswordCred(email)

        val forgotPasswordViewModelFactory = ForgotPasswordViewModelFactory(forgotPasswordCred, application)

        val forgotPasswordViewModel = ViewModelProviders
            .of(this, forgotPasswordViewModelFactory)
            .get(ForgotPasswordViewModel::class.java)

        forgotPasswordViewModel.forgotPasswordObject.observe(this, Observer{
            text_view_retrieved_email.text = it.useremail
            text_view_retrieved_password.text = it.userpassword
        })
    }
}

