package com.nijhoomt.ntrental.properties.detail

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.model.Property
import com.nijhoomt.ntrental.properties.tenants.PropertyTenantsActivity
import kotlinx.android.synthetic.main.activity_property_detail.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class PropertyDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        val MY_PERMISSIONS_REQUEST_LOCATION = 99
    }

    private lateinit var chosenProperty: Property
    private lateinit var chosenPropertyLatLng: LatLng
    private lateinit var propertyDetailViewModel: PropertyDetailViewModel

    private lateinit var map: GoogleMap

    // FusedLocationProviderClient
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var mLocationRequest: LocationRequest
    private lateinit var mLastLocation: Location
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    internal var mCurLocationMarker: Marker? = null

    private lateinit var totalDistance: String
    private lateinit var totalDuration: String

    private var mLocationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            val locationList = locationResult?.locations
            locationList?.let {

                if (locationList.isNotEmpty()) {
                    // The last location in the list is the newest
                    val location = locationList.last()
//                    Toast.makeText(
//                        applicationContext,
//                        "Latitude: ${location.latitude} | Longitude: ${location.longitude}",
//                        Toast.LENGTH_LONG
//                    ).show()
                    mLastLocation = location
                    if (mCurLocationMarker != null) {
                        mCurLocationMarker!!.remove()
                    }

//                    // Place current location marker
//                    val latLng = LatLng(location.latitude, location.longitude)
//                    val markerOptions = MarkerOptions().apply {
//                        position(latLng)
//                        title("Current Position")
//                        icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
//                    }
//
//                    mCurLocationMarker = map.addMarker(markerOptions)
//
//                    // Move map camera
//                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11.0F))
                }
            }
        }
    }

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
            val myPref = getSharedPreferences("PropertyId", Context.MODE_PRIVATE)
            val editor = myPref.edit()
            editor.putString("PropertyId", chosenProperty.id)
            editor.apply()
            startActivity(Intent(this, PropertyTenantsActivity::class.java))
        }

        fab_property_detail_direction.setOnClickListener {
            // Origin Point for Directions API
            val mLastLocationLatLng = LatLng(mLastLocation.latitude, mLastLocation.longitude)
            val directionMode = "driving"
            val myLocation =
                constructLatLngBasedOnDirectionAPIParameter(mLastLocationLatLng)
            val chosenPropertyLocation =
                constructLatLngBasedOnDirectionAPIParameter(chosenPropertyLatLng)

            propertyDetailViewModel.getDirection(myLocation, chosenPropertyLocation, directionMode)

            propertyDetailViewModel.directionObject.observe(this, Observer { it1 ->


                val listOfLatLng = ArrayList<LatLng>()
                it1.routes.forEach { it2 ->
                    it2.legs.forEach { it3 ->

                        totalDistance = it3.distance.text
                        totalDuration = it3.duration.text

                        it3.steps.forEach { it4 ->
                            listOfLatLng.add(LatLng(it4.start_location.lat, it4.start_location.lng))
                        }
                    }
                }

                listOfLatLng.add(chosenPropertyLatLng)

                val polyLineOption = PolylineOptions().addAll(listOfLatLng).apply {
                    color(R.color.colorAccent)
                    width(12F)
                }
                map.addPolyline(polyLineOption)

                val builder = LatLngBounds.Builder()
                builder.include(mLastLocationLatLng).include(chosenPropertyLatLng)
                map.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100))

                tv_property_detail_total_distance.text = totalDistance
                tv_property_detail_duration.text = totalDuration
                linearlayout_property_detail_all.visibility = View.VISIBLE
            })
        }

        // Integrate FusedLocation
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Integrate Google Maps into this activity
        mapFragment = (supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?)!!
        mapFragment.getMapAsync(this)
    }

    private fun constructLatLngBasedOnDirectionAPIParameter(latLng: LatLng): String {
        val sb = StringBuilder()

        return sb.apply {
            append(latLng.latitude)
            append(",")
            append(latLng.longitude)
        }.toString()
    }

    override fun onPause() {
        super.onPause()
        // Stop location updates when activity's no longer active
        mFusedLocationClient.removeLocationUpdates(mLocationCallback)
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

        mLocationRequest = LocationRequest().apply {
            interval = 120000 // 2 minutes
            fastestInterval = 120000
            priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        }

        val zoomLevel = 17.5f

        // Add a marker in cho and move the camera
        map.addMarker(MarkerOptions().position(chosenPropertyLatLng).title(chosenProperty.propertyaddress))
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(chosenPropertyLatLng, zoomLevel))
        map.mapType = GoogleMap.MAP_TYPE_NORMAL
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

    private fun enableMyLocation() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isPermissionGranted()) {
                mFusedLocationClient.requestLocationUpdates(
                    mLocationRequest,
                    mLocationCallback,
                    Looper.myLooper()
                )
                map.isMyLocationEnabled = true
            } else {
                // Request Location Permission
                checkLocationPermission()
            }
        } else {
            mFusedLocationClient.requestLocationUpdates(
                mLocationRequest,
                mLocationCallback,
                Looper.myLooper()
            )
            map.isMyLocationEnabled = true
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder(this)
                    .setTitle("Location Permission Needed")
                    .setMessage("This app needs the Location permission, please accept to use location functionality")
                    .setPositiveButton(
                        "OK"
                    ) { _, _ ->
                        //Prompt the user once explanation has been shown
                        ActivityCompat.requestPermissions(
                            this@PropertyDetailActivity,
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            MY_PERMISSIONS_REQUEST_LOCATION
                        )
                    }
                    .create()
                    .show()

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {

                        mFusedLocationClient.requestLocationUpdates(
                            mLocationRequest,
                            mLocationCallback,
                            Looper.myLooper()
                        )
                        map.isMyLocationEnabled = true
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
        // other 'case' lines to check for other
        // permissions this app might request
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
}
