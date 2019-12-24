package com.nijhoomt.ntrental.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.login_register.LoginRegisterActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 2000 // 2 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({

            // This method will be executed once the timer is over
            // Start your app main activity
            startActivity(Intent(this, LoginRegisterActivity::class.java))
            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}
