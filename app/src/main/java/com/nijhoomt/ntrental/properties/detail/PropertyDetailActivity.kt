package com.nijhoomt.ntrental.properties.detail

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.model.Property
import com.nijhoomt.ntrental.properties.tenants.PropertyTenantsActivity
import kotlinx.android.synthetic.main.activity_property_detail.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class PropertyDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private val REQUEST_LOCATION_PERMISSION = 1
    private lateinit var chosenProperty: Property
    private lateinit var propertyDetailViewModel: PropertyDetailViewModel
    private lateinit var map: GoogleMap
    private lateinit var chosenPropertyLatLng: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_detail)

        chosenProperty = intent.getSerializableExtra("SELECTED_PROPERTY") as Property

        setUpToolbar(chosenProperty)

        if (chosenProperty.propertylatitude.isEmpty())
            chosenProperty.propertylatitude = "41.903975"

        if (chosenProperty.propertylongitude.isEmpty())
            chosenProperty.propertylongitude = "-88.333125"

        // 41.903975, -88.333125
        chosenPropertyLatLng = LatLng(
            chosenProperty.propertylatitude.toDouble(),
            chosenProperty.propertylongitude.toDouble()
        )

        updateUIBasicInfoOfProperty()
        initializePropertyDetailViewModel()

        propertyDetailViewModel.hasDeleteProperty.observe(this, Observer {
            if (it == true) {
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Error: failed to remove a property",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        btn_property_detail_add_tenant.setOnClickListener {

            var myPref = getSharedPreferences("PropertyId", Context.MODE_PRIVATE)
            var editor = myPref.edit()

            editor.putString("PropertyId", chosenProperty.id)
            editor.commit()

            startActivity(Intent(this, PropertyTenantsActivity::class.java))


        }

        // Integrate Google Maps into this activity
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    private fun initializePropertyDetailViewModel() {
        val propertyDetailViewModelFactory = PropertyDetailViewModelFactory(
            chosenProperty.id,
            application = application
        )

        propertyDetailViewModel =
            ViewModelProviders
                .of(this, propertyDetailViewModelFactory)
                .get(PropertyDetailViewModel::class.java)
    }

    private fun updateUIBasicInfoOfProperty() {
        tv_property_detail_id.text = "Id: ${chosenProperty.id}"
        tv_property_detail_address.text =
            "Address: \n${chosenProperty.propertyaddress}\n${chosenProperty.propertycity}, ${chosenProperty.propertystate} ${chosenProperty.propertycountry}"

        if (chosenProperty.propertypurchaseprice.isNotEmpty()) {
            tv_property_detail_price.text =
                "Price: $%,.2f".format(chosenProperty.propertypurchaseprice.toDouble())
        } else {
            tv_property_detail_price.text = "Price: N/A"
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val zoomLevel = 17.5f

        // Add a marker in cho and move the camera
        map.addMarker(MarkerOptions().position(chosenPropertyLatLng).title(chosenProperty.propertyaddress))
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(chosenPropertyLatLng, zoomLevel))
        map.mapType = GoogleMap.MAP_TYPE_SATELLITE
        enableMyLocation()
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
                propertyDetailViewModel.removeProperty(chosenProperty.id)
                finish()
                return true
            }
            R.id.normal_map -> {
                map.mapType = GoogleMap.MAP_TYPE_NORMAL
                true
            }
            R.id.hybrid_map -> {
                map.mapType = GoogleMap.MAP_TYPE_HYBRID
                true
            }
            R.id.satellite_map -> {
                map.mapType = GoogleMap.MAP_TYPE_SATELLITE
                true
            }
            R.id.terrain_map -> {
                map.mapType = GoogleMap.MAP_TYPE_TERRAIN
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) === PackageManager.PERMISSION_GRANTED
    }

    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            map.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        // Check if locaditon permissions are granted and if so enable the location
        // data layer
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.isNotEmpty() && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                enableMyLocation()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
