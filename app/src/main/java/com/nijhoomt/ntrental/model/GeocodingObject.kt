package com.nijhoomt.ntrental.model

/**
 * Created by N & T on 12/26/2019.
 * Under instructions of Varun, Manisha, Ansari, & Rahul
 */
data class GeocodingObject (
    val results: List<AddressDetail>
)

data class AddressDetail (
    val address_components: List<AddressComponent>,
    val formatted_address: String,
    val geometry: LocationObject
)

data class LocationObject (
    val location: LatLngObject
)

data class LatLngObject (
    val lat: Double,
    val lng: Double
)

data class AddressComponent (
    val long_name: String,
    val short_name: String
)
