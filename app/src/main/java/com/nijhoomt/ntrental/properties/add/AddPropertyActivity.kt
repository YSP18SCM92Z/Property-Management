package com.nijhoomt.ntrental.properties.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.model.LatLngObject
import com.nijhoomt.ntrental.model.Property
import com.nijhoomt.ntrental.more.MoreActivity
import kotlinx.android.synthetic.main.activity_add_property.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class AddPropertyActivity : AppCompatActivity() {

    private lateinit var lagLngObject: LatLngObject
    private lateinit var addPropertyViewModel: AddPropertyViewModel
    private lateinit var property: Property

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_property)

        setUpToolbar()
        btn_add_property_save.isEnabled = false

        btn_add_property_check.setOnClickListener {

//        "id": "942"
//        val id: String,
//        How can we make sure the id is unique?
//        >> Server already takes care of the uniqueness but not validation
//        Same info still can be readded. Validation of the same property will be our responsibility


//        User Inputs
//        "propertystatus": "tenants",
//        "propertypurchaseprice": "12000",
//        "propertymortageinfo": "no",

            val address = tiet_add_property_address.text.toString()
            val city = tiet_add_property_city.text.toString()
            val state = tiet_add_property_state.text.toString()
            val zipcode = tiet_add_property_zipcode.text.toString()
            val country = tiet_add_property_country.text.toString()
            val purchasePrice = tiet_add_property_purchase_price.text.toString()

            if (address == "" || city == "" || state == "" ||
                zipcode == "" || country == "" || purchasePrice == ""
            ) {
                if (address == "") tiet_add_property_address.error = "Address field is required!"
                if (city == "") tiet_add_property_city.error = "City field is required!"
                if (state == "") tiet_add_property_state.error = "Password field is required!"
                if (zipcode == "") tiet_add_property_zipcode.error = "Zipcode field is required!"
                if (country == "") tiet_add_property_country.error = "Country field is required!"
                if (purchasePrice == "") tiet_add_property_purchase_price.error =
                    "Price field is required!"

            } else {

//                Construct Formatted Address so that we can send to Google API Geocoding for
//                getting the precise latitude & longitude
                val formattedString = constructFormattedAddress()

                val myPref = getSharedPreferences("UserCred", Context.MODE_PRIVATE)
                val userId = myPref.getString("userId", "").toString()
                val userType = myPref.getString("userType", "").toString()


//                After Converting the address to LatLng using Reverse Geocoding
//                While creating the ViewModel, we are also in the processing of gathering the correct LatLng
//                For the specified address
                initializeAddPropertyViewModel(formattedString)

                addPropertyViewModel.latLngObject.observe(this, Observer {
                    lagLngObject = it
                    Toast.makeText(
                        this,
                        "Latitude: ${lagLngObject.lat}, Longitude: ${lagLngObject.lng}",
                        Toast.LENGTH_LONG
                    ).show()

                    // Construct property instance with all the provided/found information
                    property = Property(
                        // API server responsibility
                        id = "",
                        // User Inputs
                        propertyaddress = address,
                        propertycity = city,
                        propertystate = state,
                        propertycountry = country,
                        propertypurchaseprice = purchasePrice,
                        // Session Info
                        propertyuserid = userId,
                        propertyusertype = userType,
                        // Geocoding
                        propertylatitude = lagLngObject.lat.toString(),
                        propertylongitude = lagLngObject.lng.toString()
                    )
                })
                btn_add_property_save.isEnabled = true
            }
        }

        btn_add_property_save.setOnClickListener {
            addPropertyViewModel.addProperty(property)
            addPropertyViewModel.hasAddProperty.observe(this, Observer {
                if (it == true) {
                    finish()
                } else {
                    Log.e("AddPropertyV", "Error: failed to add a property")
                    Toast.makeText(
                        this,
                        "Error: failed to add a property",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }

    private fun initializeAddPropertyViewModel(formattedString: String) {
        val addPropertyViewModelFactory =
            AddPropertyViewModelFactory(
                formattedAddress = formattedString,
                application = application
            )

        addPropertyViewModel = ViewModelProviders
            .of(this, addPropertyViewModelFactory)
            .get(AddPropertyViewModel::class.java)
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
