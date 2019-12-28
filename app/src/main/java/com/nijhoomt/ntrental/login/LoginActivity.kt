package com.nijhoomt.ntrental.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.forgotpassword.ForgotPasswordActivity
import com.nijhoomt.ntrental.model.LoginCredential
import com.nijhoomt.ntrental.userroles.landlord.LandlordActivity
import com.nijhoomt.ntrental.userroles.tenant.TenantActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login_login.setOnClickListener {
            validateLoginCredential()
        }
        
        text_view_forgot_your_password.setOnClickListener{
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateLoginCredential() {

        if (edit_text_email_login.text.toString() == "" || edit_text_password_login.text.toString() == "") {

            if (edit_text_email_login.text.toString() == "") {
                edit_text_email_login.error = "Email field is required"
            }

            if (edit_text_password_login.text.toString() == "") {
                edit_text_password_login.error = "Password field is required"
            }
        } else {
            processLogin()
        }
    }

    private fun processLogin() {

        val email = edit_text_email_login.text.toString()
        val password = edit_text_password_login.text.toString()
        val loginCredential = LoginCredential(email, password)

        val loginViewModel =
            initializeLoginViewModel(loginCredential)

        loginViewModel.loginObject.observe(this, Observer {

            when (it.userType) {
                "Landlord" -> {
                    val intent = Intent(this, LandlordActivity::class.java)
                    intent.putExtra("LoginObject", it)
                    startActivity(intent)

                    val myPref = getSharedPreferences("UserCred", Context.MODE_PRIVATE)
                    val editor = myPref.edit()
                    editor.putString("userId", it.userId)
                    editor.putString("userType", it.userType)

                    editor.apply()
                }
                "Property M" -> {}
                "Tenant" -> {
                    val intent = Intent(this, TenantActivity::class.java)
                    intent.putExtra("LoginObject", it)
                    startActivity(intent)
                }
                "Vendor" ->{}
            }
        })
    }

    private fun initializeLoginViewModel(loginCredential: LoginCredential): LoginViewModel {
        val loginViewModelFactory = LoginViewModelFactory(
            loginCredential = loginCredential,
            application = application
        )

        return ViewModelProviders
            .of(this, loginViewModelFactory)
            .get(LoginViewModel::class.java)
    }
}
