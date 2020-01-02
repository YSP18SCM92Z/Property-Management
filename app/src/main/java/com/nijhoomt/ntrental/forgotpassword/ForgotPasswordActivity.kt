package com.nijhoomt.ntrental.forgotpassword

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.login.LoginActivity
import com.nijhoomt.ntrental.model.ForgotPasswordCred
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        btn_request_password.setOnClickListener {
            validateForgottenEmail()
        }

        btn_login_forgotpassword.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("EMAIL_AFTER_RESET", text_view_retrieved_email.text)
            intent.putExtra("PASSWORD_AFTER_RESET", text_view_retrieved_password.text)
            startActivity(intent)
        }

    }

    private fun validateForgottenEmail() {
        val email = edit_text_email_forgotpassword

        if (email.text.toString() == "") {
            email.error = "Email field is required"
        } else {
            requestPassword(email.text.toString())
        }
    }

    private fun requestPassword(email: String) {

        val forgotPasswordCred =
            ForgotPasswordCred(email)

        val forgotPasswordViewModel =
            initializeForgotPasswordViewModel(forgotPasswordCred)

        forgotPasswordViewModel.forgotPasswordObject.observe(this, Observer{
            text_view_retrieved_email.text = it.useremail
            text_view_retrieved_password.text = it.userpassword
        })
    }

    private fun initializeForgotPasswordViewModel(forgotPasswordCred: ForgotPasswordCred): ForgotPasswordViewModel {
        val forgotPasswordViewModelFactory =
            ForgotPasswordViewModelFactory(
                forgotPasswordCred, application
            )

        return ViewModelProviders
            .of(this, forgotPasswordViewModelFactory)
            .get(ForgotPasswordViewModel::class.java)
    }
}

