package com.nijhoomt.ntrental.properties.tenants

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.nijhoomt.ntrental.R
import kotlinx.android.synthetic.main.activity_property_tenants.*

class PropertyTenantsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_tenants)
        setSupportActionBar(custom_tootbar_property_tenants)
        fab_property_tenants_add_tenant.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}
