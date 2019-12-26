package com.nijhoomt.ntrental.properties.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.more.MoreActivity
import kotlinx.android.synthetic.main.custom_toolbar.*

class AddPropertyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_property)

        setUpToolbar()

//        "id": "942"
//        val id: String,
//        How can we make sure the id is unique?
//        >> Server already takes care of the uniqueness but not validation
//        Same info still can be readded. Validation of the same property will be your responsibility

//        "propertyaddress": "fla1234",
//        "propertycity": "Noida",
//        "propertystate": "UP",
//        "propertycountry": "India",
//        "propertystatus": "tenants",
//        "propertypurchaseprice": "12000",
//        "propertymortageinfo": "no",


//        "propertyuserid": "3",
//        "propertyusertype": "landlord",
        val myPref = getSharedPreferences("UserCred", Context.MODE_PRIVATE)
        var userId = myPref.getString("userId", "").toString()
        var userType = myPref.getString("userType", "").toString()

//         After Converting the address to LatLng using Reverse Geocoding
//         Latitude
//         longitude
//        "propertylatitude": "12.4565656",
//        "propertylongitude": "3.5656565"

    }

    private fun setUpToolbar() {
        val customToolbar = custom_toolbar as Toolbar
        customToolbar.title = "Add Property"
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
