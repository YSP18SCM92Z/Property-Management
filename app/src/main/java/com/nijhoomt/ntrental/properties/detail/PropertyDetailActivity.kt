package com.nijhoomt.ntrental.properties.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.properties.Property
import kotlinx.android.synthetic.main.activity_property_detail.*

class PropertyDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_detail)

        val chosenProperty = intent.getSerializableExtra("SELECTED_PROPERTY") as Property
        Toast.makeText(this, """
            $chosenProperty
        """.trimIndent(), Toast.LENGTH_LONG).show()

        tv_property_detail_id.text = "Id: ${chosenProperty.id}"
        tv_property_detail_address.text = "Address: \n${chosenProperty.propertyaddress}\n${chosenProperty.propertycity}, ${chosenProperty.propertystate} ${chosenProperty.propertycountry}"
        tv_property_detail_price.text = "$%,.2f".format(chosenProperty.propertypurchaseprice.toDouble())
    }
}
