package com.nijhoomt.ntrental.userroles.tenant

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
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.model.LoginObject
import com.nijhoomt.ntrental.more.MoreActivity
import kotlinx.android.synthetic.main.custom_toolbar.*

class TenantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant)
        val navView: BottomNavigationView = findViewById(R.id.tenant_bottom_nav)

        setUpToolbar()

        val navController = findNavController(R.id.tenant_nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_tenant_dashboard,
                R.id.navigation_tenant_reports,
                R.id.navigation_tenant_todo,
                R.id.navigation_tenant_documents
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
