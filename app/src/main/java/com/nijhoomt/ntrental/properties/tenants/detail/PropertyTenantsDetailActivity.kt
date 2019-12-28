package com.nijhoomt.ntrental.properties.tenants.detail

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.model.Tenant

import kotlinx.android.synthetic.main.activity_property_tenants_detail.*

class PropertyTenantsDetailActivity : AppCompatActivity() {

    private lateinit var chosenTenant: Tenant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_tenants_detail)
        setSupportActionBar(custom_toolbar_property_tenant_detail)

//        chosenProperty = intent.getSerializableExtra("SELECTED_PROPERTY") as Property

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
