package com.nijhoomt.ntrental

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
import com.nijhoomt.ntrental.model.LoginCredential
import com.nijhoomt.ntrental.more.MoreActivity
import com.nijhoomt.ntrental.network.LoginObject
import kotlinx.android.synthetic.main.custom_toolbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)




        setUpToolbar()


        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_dashboard,
                R.id.navigation_reports,
                R.id.navigation_tenants,
                R.id.navigation_documents
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val loginCredential = intent.getSerializableExtra("LoginObject") as LoginObject
        Toast.makeText(this, """
            ${loginCredential.userEmail}
        """.trimIndent(), Toast.LENGTH_LONG).show()

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
