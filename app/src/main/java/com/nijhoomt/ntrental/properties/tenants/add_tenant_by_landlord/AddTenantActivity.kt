package com.nijhoomt.ntrental.properties.tenants.add_tenant_by_landlord

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.model.AddTenantCred
import kotlinx.android.synthetic.main.activity_add_tenant.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class AddTenantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tenant)

        setupToolbar()

        btn_add_tenant_save.setOnClickListener {

            if (tiet_add_tenant_name.text.toString() == "" ||
                tiet_add_tenant_address.text.toString() == "" ||
                tiet_add_tenant_city.text.toString() == "" ||
                tiet_add_tenant_state.text.toString() == "" ||
                tiet_add_tenant_zipcode.text.toString() == "" ||
                tiet_add_tenant_email.text.toString() == "" ||
                tiet_add_tenant_phone_number.text.toString() == ""
            ) {

                if (tiet_add_tenant_name.text.toString() == "" || tiet_add_tenant_name == null) {

                    tiet_add_tenant_name.error = "Name is required"
                }

                if (tiet_add_tenant_address.text.toString() == "" || tiet_add_tenant_address == null) {

                    tiet_add_tenant_address.error = "Address is required"
                }

                if (tiet_add_tenant_city.text.toString() == "" || tiet_add_tenant_city == null) {

                    tiet_add_tenant_city.error = "City is required"
                }

                if (tiet_add_tenant_state.text.toString() == "" || tiet_add_tenant_state == null) {

                    tiet_add_tenant_state.error = "State is required"
                }

                if (tiet_add_tenant_zipcode.text.toString() == "" || tiet_add_tenant_zipcode == null) {

                    tiet_add_tenant_zipcode.error = "Zipcode is required"
                }

                if (tiet_add_tenant_country.text.toString() == "" || tiet_add_tenant_country == null) {

                    tiet_add_tenant_country.error = "Country is required"
                }

                if (tiet_add_tenant_email.text.toString() == "" || tiet_add_tenant_email == null) {

                    tiet_add_tenant_email.error = "Email is required"
                }

                if (tiet_add_tenant_phone_number.text.toString() == "" || tiet_add_tenant_phone_number == null) {

                    tiet_add_tenant_phone_number.error = "Phone number is required"
                }

            } else {

                val myPref1 = getSharedPreferences("PropertyId", Context.MODE_PRIVATE)
                val myPref2 = getSharedPreferences("UserCred", Context.MODE_PRIVATE)
                val propertyId = myPref1.getString("PropertyId", "")!!
                val landlordId = myPref2.getString("userId", "")!!

                val addTenantCred = AddTenantCred(
                    name = tiet_add_tenant_name.text.toString(),
                    address = tiet_add_tenant_address.text.toString(),
                    email = tiet_add_tenant_email.text.toString(),
                    landlordid = landlordId,
                    mobile = tiet_add_tenant_phone_number.text.toString(),
                    propertyid = propertyId
                )

                proceedAddingTenant(addTenantCred)
            }
        }
    }

    private fun proceedAddingTenant(addTenantCred: AddTenantCred) {

        // val addTenantCred: AddTenantCred = AddTenantCred(name = tiet_add_tenant_name.text.toString(), address = "", email = "", landlordid = "", mobile = "", propertyid = "")

        val addTenantViewModelFactory = AddTenantViewModelFactory(addTenantCred, application)

        val addTenantViewModel = ViewModelProviders.of(this, addTenantViewModelFactory)
            .get(AddTenantViewModel::class.java)

        addTenantViewModel.responseMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun setupToolbar() {
        val toolbar = custom_toolbar
        toolbar.title = "New Tenant"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}
