package com.nijhoomt.ntrental.userroles.tenant

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.more.MoreActivity
import kotlinx.android.synthetic.main.activity_contact_tenant.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class ContactTenantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_tenant)

        setUpToolbar()

        btn_contact_landlord_send.setOnClickListener{
            Snackbar.make(it, "Your message has been sent", Snackbar.LENGTH_LONG)
                .setAction(R.string.snackbar_text) {
                    startActivity(Intent(this, TenantActivity::class.java))
                }.show()
        }
    }

    private fun setUpToolbar() {
        val customToolbar = custom_toolbar as Toolbar
        customToolbar.title = "Contact Landlord"
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
