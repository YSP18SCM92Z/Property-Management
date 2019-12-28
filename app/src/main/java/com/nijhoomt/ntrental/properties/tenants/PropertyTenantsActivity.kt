package com.nijhoomt.ntrental.properties.tenants

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.model.Tenant
import com.nijhoomt.ntrental.more.MoreActivity
import com.nijhoomt.ntrental.properties.tenants.detail.PropertyTenantsDetailActivity
import kotlinx.android.synthetic.main.activity_property_tenants.*
import kotlinx.android.synthetic.main.content_property_tenants.*

class PropertyTenantsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_tenants)

        setUpToolbar()

        // Show the list of properties the landlord currently have
        val myPref = getSharedPreferences("UserCred", Context.MODE_PRIVATE)
        val userId = myPref.getString("userId", "").toString()
        val userType = myPref.getString("userType", "").toString()


        val propertyTenantsViewModel =
            initializePropertyTenantsViewModel(userId)

        // ListAdapter is completely different from ListView (We normally compare ListView vs. RecyclerView)
        // We compare the performance of ListAdapter with the normal RecyclerView Adapter
        // ListAdapter has DiffUtil built-in >> Give us a better performance whenever we wanna show
        // a list of item.
        val propertiesTenantsListAdapter = PropertyTenantsListAdapter(application)
        recyclerview_property_tenants.adapter = propertiesTenantsListAdapter

        propertyTenantsViewModel.listOfTenantsOfChosenLandlord.observe(this, Observer {
            propertiesTenantsListAdapter.submitList(it.sortedByDescending { it.id })
        })

        propertiesTenantsListAdapter.setOnItemClickListener(object: PropertyTenantsListAdapter.OnItemClickListener{
            override fun onItemClick(tenant: Tenant) {
                val intent = Intent(applicationContext, PropertyTenantsDetailActivity::class.java)
                intent.putExtra("SELECTED_TENANT", tenant)
                startActivity(intent)
            }
        })

        fab_property_tenants_add_tenant.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun initializePropertyTenantsViewModel(landlordId: String): PropertyTenantsViewModel {
        val propertyTenantsViewModelFactory = PropertyTenantsViewModelFactory(
            landlordId = landlordId,
            application = application
        )

        return ViewModelProviders
            .of(this, propertyTenantsViewModelFactory)
            .get(PropertyTenantsViewModel::class.java)
    }

    private fun setUpToolbar() {
        val customToolbar = custom_tootbar_property_tenants as Toolbar
        customToolbar.title = "Tenants"
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
