package com.nijhoomt.ntrental.properties.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.model.LatLngObject
import com.nijhoomt.ntrental.more.MoreActivity
import kotlinx.android.synthetic.main.activity_add_property.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class AddPropertyActivity : AppCompatActivity() {

    private lateinit var lagLngObject: LatLngObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_property)

        setUpToolbar()

        btn_add_property_save.setOnClickListener {

//        "id": "942"
//        val id: String,
//        How can we make sure the id is unique?
//        >> Server already takes care of the uniqueness but not validation
//        Same info still can be readded. Validation of the same property will be your responsibility


//        User Inputs
//        "propertystatus": "tenants",
//        "propertypurchaseprice": "12000",
//        "propertymortageinfo": "no",
            val formattedString = constructFormattedAddress()


//        User Credentials after Login
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

            val addPropertyViewModelFactory = AddPropertyViewModelFactory(
                formattedAddress = formattedString,
                application = application
            )

            val addPropertyViewModel = ViewModelProviders
                .of(this, addPropertyViewModelFactory)
                .get(AddPropertyViewModel::class.java)

            addPropertyViewModel.latLngObject.observe(this, Observer {
                lagLngObject = it
                Toast.makeText(
                    this,
                    "Latitude: ${lagLngObject.lat}, Longitude: ${lagLngObject.lng}",
                    Toast.LENGTH_LONG
                ).show()
            })
        }


    }

    private fun constructFormattedAddress(): String {
        val sb = StringBuilder()

        val address = tiet_add_property_address.text.toString()
        val city = tiet_add_property_city.text.toString()
        val state = tiet_add_property_state.text.toString()
        val zipcode = tiet_add_property_zipcode.text.toString()
        val country = tiet_add_property_country.text.toString()

        // 1600+Amphitheatre+Parkway,
        sb.append("$address".trim().replace(' ', '+') + ",")
        sb.append("+" + "$city".trim().replace(' ', '+') + ",")
        sb.append("+" + "$state".trim() + '+')
        sb.append("$zipcode".trim() + ',')
        sb.append("+" + "$country".trim())
        //        "propertyaddress": "fla1234",
        //        "propertycity": "Noida",
        //        "propertystate": "UP",
        //        "propertycountry": "India",


        return sb.toString()
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
