package com.nijhoomt.ntrental.userroles.tenant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.userroles.tenant.dashboard.DashboardFragment
import kotlinx.android.synthetic.main.activity_contact_tenant.*

class ContactTenantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_tenant)

        btn_contact_landlord_send.setOnClickListener{

            Snackbar.make(it, "Your message has been sent", Snackbar.LENGTH_LONG).setAction(R.string.snackbar_text,
                View.OnClickListener {

               startActivity(Intent(this, TenantActivity::class.java))
            }).show()
        }
    }
}
