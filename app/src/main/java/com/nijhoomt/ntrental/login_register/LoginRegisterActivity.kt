package com.nijhoomt.ntrental.login_register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.login.LoginActivity
import com.nijhoomt.ntrental.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login_register.*

class LoginRegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)
      
        btn_login_register_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btn_login_register_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
