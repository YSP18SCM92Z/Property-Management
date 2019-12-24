package com.nijhoomt.ntrental.properties

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.more.MoreActivity
import kotlinx.android.synthetic.main.custom_toolbar.*

class PropertiesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_properties)

        setUpToolbar()

        // Show the list of properties the landlord currently have


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
