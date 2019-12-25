package com.nijhoomt.ntrental.properties

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.more.MoreActivity
import com.nijhoomt.ntrental.network.LoginObject
import kotlinx.android.synthetic.main.custom_toolbar.*

class PropertiesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_properties)

        setUpToolbar()

        // Show the list of properties the landlord currently have
        val myPref = getSharedPreferences("UserCred", Context.MODE_PRIVATE)
        var userId = myPref.getString("userId", "").toString()
        var userType = myPref.getString("userType", "").toString()


        //Initialize userID Model
        val userCred = UserId(userId, userType)

        val propertyViewModelFactory = PropertyViewModelFactory(
            userId = userCred,
            application = application
        )

        val propertyViewModel =
            ViewModelProviders
                .of(this, propertyViewModelFactory)
                .get(PropertyViewModel::class.java)

        propertyViewModel.property.observe(this, Observer {
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
        })


        // Just like to-do app, allow landlord to CRUD on the property right here

    }

    private fun setUpToolbar() {
        val customToolbar = custom_toolbar as Toolbar
        customToolbar.title = "Property"
        setSupportActionBar(customToolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.more_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btn_more -> {
                startActivity(Intent(this, MoreActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
