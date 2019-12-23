package com.nijhoomt.ntrental.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nijhoomt.ntrental.MainActivity
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.model.LoginCredential
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login_login.setOnClickListener {

            val email = edit_text_email_login
            val password = edit_text_password_login

            if(edit_text_email_login.text.toString() == "" || edit_text_password_login.text.toString() == ""){
                if(edit_text_email_login.text.toString() == ""){

                    email.error = "Email field is required"
                }

                if(edit_text_password_login.text.toString() == ""){

                    password.error = "Password field is required"
                }
            }

           else {
                processLogin()
            }
        }


    }

    private fun processLogin() {
        // Assume you can get the data from edittext for email & password
        val email = edit_text_email_login.text.toString()
        val password = edit_text_password_login.text.toString()

        val loginCredential = LoginCredential(email, password)

        val loginViewModelFactory = LoginViewModelFactory(
            loginCredential = loginCredential,
            application = application
        )

        val loginViewModel =
            ViewModelProviders
                .of(this, loginViewModelFactory)
                .get(LoginViewModel::class.java)

        loginViewModel.loginObject.observe(this, Observer {

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("LoginObject", it)
            startActivity(intent)
        })
    }
}
