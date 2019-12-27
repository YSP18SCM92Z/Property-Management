package com.nijhoomt.ntrental.properties.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.model.Property
import com.nijhoomt.ntrental.more.MoreActivity
import kotlinx.android.synthetic.main.activity_property_detail.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class PropertyDetailActivity : AppCompatActivity() {

    private lateinit var chosenProperty: Property

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_detail)

        chosenProperty = intent.getSerializableExtra("SELECTED_PROPERTY") as Property
        setUpToolbar(chosenProperty)

        Toast.makeText(this, """
            $chosenProperty
        """.trimIndent(), Toast.LENGTH_LONG).show()

        tv_property_detail_id.text = "Id: ${chosenProperty.id}"
        tv_property_detail_address.text = "Address: \n${chosenProperty.propertyaddress}\n${chosenProperty.propertycity}, ${chosenProperty.propertystate} ${chosenProperty.propertycountry}"

        if (chosenProperty.propertypurchaseprice.isNotEmpty()) {
            tv_property_detail_price.text = "$%,.2f".format(chosenProperty.propertypurchaseprice.toDouble())
        }
        else {
            tv_property_detail_price.text = "N/A"
        }
    }

    private fun setUpToolbar(chosenProperty: Property) {
        val customToolbar = custom_toolbar as Toolbar
        customToolbar.title = chosenProperty.propertyaddress
        setSupportActionBar(customToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.btn_delete -> {
                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
