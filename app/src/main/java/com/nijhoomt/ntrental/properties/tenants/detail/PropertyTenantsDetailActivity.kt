package com.nijhoomt.ntrental.properties.tenants.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.model.Tenant
import com.nijhoomt.ntrental.more.MoreActivity
import kotlinx.android.synthetic.main.activity_property_tenants_detail.*
import kotlinx.android.synthetic.main.content_property_tenants_detail.*

class PropertyTenantsDetailActivity : AppCompatActivity() {

    private lateinit var chosenTenant: Tenant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_tenants_detail)

        chosenTenant = intent.getSerializableExtra("SELECTED_TENANT") as Tenant
        setUpToolbar(chosenTenant.tenantName)

        tv_property_tenant_detail_id.text = "Id: ${chosenTenant.id}"
        tv_property_tenant_detail_name.text = "Name: ${chosenTenant.tenantName}"
        tv_property_tenant_detail_address.text = "Address: ${chosenTenant.tenantAddress}"
        tv_property_tenant_detail_email.text = "Email: ${chosenTenant.tenantEmail}"

        // TODO Format phone number or find lib to do so for us
        tv_property_tenant_detail_mobile.text = "Mobile: ${chosenTenant.tenantMobile}"

        // TODO Make a call feature using System Call App
        fab_property_tenants_detail_call_tenant.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun setUpToolbar(tenantName: String) {
        val customToolbar = custom_toolbar_property_tenant_detail as Toolbar
        customToolbar.title = "$tenantName"
        setSupportActionBar(customToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.more_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.btn_more -> {
                startActivity(Intent(this, MoreActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
