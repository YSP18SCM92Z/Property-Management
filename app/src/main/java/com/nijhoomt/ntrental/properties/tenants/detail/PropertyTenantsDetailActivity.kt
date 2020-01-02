package com.nijhoomt.ntrental.properties.tenants.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
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
        tv_property_tenant_detail_email.text = "Email: ${parseEmail(chosenTenant.tenantEmail)}"
        tv_property_tenant_detail_mobile.text = "Mobile: ${parseMobile(chosenTenant.tenantMobile)}"

        val imageResourceId = getImageResourceId(chosenTenant.tenantName)
        Glide.with(this)
            .load(imageResourceId)
            .centerCrop()
            .into(iv_property_tenant_detail_image)

        // TODO Make a call feature using System Call App
        fab_property_tenants_detail_call_tenant.setOnClickListener { view ->

            Snackbar.make(view, "Calling ${chosenTenant.tenantName} ...", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = (Uri.parse("tel: +1 ${parseMobile(chosenTenant.tenantMobile)}" ))
            startActivity(callIntent)
        }
    }

    private fun getImageResourceId(tenantName: String): Int = with(tenantName) {
        when {
            contains("Varun") -> R.drawable.varun_gupta
            contains("Manisha") -> R.drawable.manisha_prasad
            contains("Ansari") -> R.drawable.ansari
            contains("Rahul") -> R.drawable.rahul_khurana
            contains("Trump") -> R.drawable.donald_trump
            else -> R.drawable.unknown_person
        }
    }

    private fun parseMobile(tenantMobile: String): CharSequence? {
        if (tenantMobile.length != 10) return "N/A"
        else {
            val mobileCharArr = tenantMobile.toCharArray()
            val stringBuilder = StringBuilder()
            stringBuilder.append('(')
            stringBuilder.append(mobileCharArr[0], mobileCharArr[1], mobileCharArr[2])
            stringBuilder.append(") ")
            stringBuilder.append(mobileCharArr[3], mobileCharArr[4], mobileCharArr[5])
            stringBuilder.append("-")
            stringBuilder.append(
                mobileCharArr[6],
                mobileCharArr[7],
                mobileCharArr[8],
                mobileCharArr[9]
            )
            return stringBuilder.toString()
        }
    }

    private fun parseEmail(tenantEmail: String): CharSequence? {
        val isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(tenantEmail).matches()
        return if (isEmailValid) tenantEmail else "N/A"
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
