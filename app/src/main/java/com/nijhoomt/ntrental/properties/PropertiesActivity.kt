package com.nijhoomt.ntrental.properties

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.more.MoreActivity
import com.nijhoomt.ntrental.network.LoginObject
import com.nijhoomt.ntrental.properties.detail.PropertyDetailActivity
import kotlinx.android.synthetic.main.activity_properties.*
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

        val propertiesListAdapter = PropertiesListAdapter(application)
        recyclerview_properties.adapter = propertiesListAdapter

        propertyViewModel.property.observe(this, Observer {
            propertiesListAdapter.submitList(it)
        })

        propertiesListAdapter.setOnItemClickListener(object: PropertiesListAdapter.OnItemClickListener{
            override fun onItemClick(property: Property) {
                val intent = Intent(applicationContext, PropertyDetailActivity::class.java)
                intent.putExtra("SELECTED_PROPERTY", property)
                startActivity(intent)
            }
        })

        // Just like to-do app, allow landlord to CRUD on the property right here

    }

    private fun setUpToolbar() {
        val customToolbar = custom_toolbar as Toolbar
        customToolbar.title = "Your Properties"
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
