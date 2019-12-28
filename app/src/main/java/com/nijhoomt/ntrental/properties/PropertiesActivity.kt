package com.nijhoomt.ntrental.properties

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.model.Property
import com.nijhoomt.ntrental.model.UserId
import com.nijhoomt.ntrental.more.MoreActivity
import com.nijhoomt.ntrental.properties.add.AddPropertyActivity
import com.nijhoomt.ntrental.properties.detail.PropertyDetailActivity
import kotlinx.android.synthetic.main.activity_properties.*

class PropertiesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_properties)

        setUpToolbar()

        // Show the list of properties the landlord currently have
        val myPref = getSharedPreferences("UserCred", Context.MODE_PRIVATE)
        val userId = myPref.getString("userId", "").toString()
        val userType = myPref.getString("userType", "").toString()

        //Initialize userID Model
        val userCred = UserId(userId, userType)

        val propertyViewModel =
            initializePropertyViewModel(userCred)

        // ListAdapter is completely different from ListView (We normally compare ListView vs. RecyclerView)
        // We compare the performance of ListAdapter with the normal RecyclerView Adapter
        // ListAdapter has DiffUtil built-in >> Give us a better performance whenever we wanna show
        // a list of item.
        val propertiesListAdapter = PropertiesListAdapter(application)
        recyclerview_properties.adapter = propertiesListAdapter

        propertyViewModel.property.observe(this, Observer {
            propertiesListAdapter.submitList(it.sortedByDescending { it.id })
        })

        propertiesListAdapter.setOnItemClickListener(object: PropertiesListAdapter.OnItemClickListener{
            override fun onItemClick(property: Property) {
                val intent = Intent(applicationContext, PropertyDetailActivity::class.java)
                intent.putExtra("SELECTED_PROPERTY", property)
                startActivity(intent)
            }
        })

        fab_properties.setOnClickListener {
            startActivity(Intent(this, AddPropertyActivity::class.java))
        }
    }

    private fun initializePropertyViewModel(userCred: UserId): PropertyViewModel {
        val propertyViewModelFactory = PropertyViewModelFactory(
            userId = userCred,
            application = application
        )

        val propertyViewModel =
            ViewModelProviders
                .of(this, propertyViewModelFactory)
                .get(PropertyViewModel::class.java)
        return propertyViewModel
    }

    private fun setUpToolbar() {
        val customToolbar = properties_custom_toolbar as Toolbar
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
