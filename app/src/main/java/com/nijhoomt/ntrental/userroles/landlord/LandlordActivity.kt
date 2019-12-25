package com.nijhoomt.ntrental.userroles.landlord

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.model.LoginCredential
import com.nijhoomt.ntrental.more.MoreActivity
import com.nijhoomt.ntrental.network.LoginObject
import com.nijhoomt.ntrental.properties.PropertiesActivity
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.fragment_landlord_dashboard.*

class LandlordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landlord)
        val navView: BottomNavigationView = findViewById(R.id.landlord_bottom_nav)

        setUpToolbar()

        val navController = findNavController(R.id.landlord_nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_landlord_dashboard,
                R.id.navigation_landlord_reports,
                R.id.navigation_landlord_todo,
                R.id.navigation_landlord_documents
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        val loginCredential = intent.getSerializableExtra("LoginObject") as LoginObject
//        Toast.makeText(this, """
//            ${loginCredential.userEmail}
//        """.trimIndent(), Toast.LENGTH_LONG).show()

//        val mcv_dashboard = findViewById(R.id.mcv_dashboard_property) as MaterialCardView

//        mcv_dashboard.setOnClickListener{
//
//            val intent_property = Intent(this, PropertiesActivity::class.java)
//            intent_property.putExtra("LoginObject", loginCredential)
//            startActivity(intent_property)
//        }

    }

    private fun setUpToolbar() {
        val customToolbar = custom_toolbar as Toolbar
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
