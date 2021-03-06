package com.nijhoomt.ntrental.login

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.di.viewmodels.ViewModelProvidersFactory
import com.nijhoomt.ntrental.forgotpassword.ForgotPasswordActivity
import com.nijhoomt.ntrental.userroles.landlord.LandlordActivity
import com.nijhoomt.ntrental.userroles.tenant.TenantActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {

    private val TAG = javaClass.simpleName
    private lateinit var loginViewModel: LoginViewModel

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var providerFactory: ViewModelProvidersFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setLogo()

        val emailAfterReset = intent.getStringExtra("EMAIL_AFTER_RESET")
        val passwordAfterReset = intent.getStringExtra("PASSWORD_AFTER_RESET")
        if (emailAfterReset != null || passwordAfterReset != null){
            edit_text_email_login.setText(emailAfterReset)
            edit_text_password_login.setText(passwordAfterReset)
        }

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

        // Dagger will inject ViewModelProvidersFactory into providerFactory @Runtime
        // & make it available so that we can pass it in here to initilize loginViewModel
        loginViewModel = ViewModelProviders
            .of(this, providerFactory)
            .get(LoginViewModel::class.java)

        loginViewModel.initiateLogin(email, password)

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
                "Property M" -> {

                }
                "Tenant" -> {

                    val intent = Intent(this, TenantActivity::class.java)
                    intent.putExtra("LoginObject", it)
                    startActivity(intent)

                    val myPref = getSharedPreferences("UserCred", Context.MODE_PRIVATE)
                    val editor = myPref.edit()
                    editor.putString("userType", it.userType)
                    editor.apply()
                }
                "Vendor" ->{}
            }
        })
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(iv_login_logo)
    }
}
