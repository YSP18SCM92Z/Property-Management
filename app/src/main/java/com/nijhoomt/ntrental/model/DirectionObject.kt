package com.nijhoomt.ntrental.model


/**
 * Created by N & T on 12/29/2019.
 * Under instructions of Varun, Manisha, Ansari, & Rahul
 */
data class DirectionObject (
    val geocoded_waypoints: List<GeocodedWayPoints>,
    val routes: List<Route>,
    val status: String
)

data class GeocodedWayPoints (
    val geocoder_status: String,
    val place_id: String
//    val types: List<StreetAddress>
)

//data class StreetAddress(
//
//)

data class Route (
//    val bounds: List<Bound>,
//    val copyrights: String,
    val legs: List<Leg>
//    val overview_polyline: List<OverviewPolyline>,
//    val summary: String,
//    val warnings: List<Warning>,
//    val waypoint_order: List<WaypointOrder>
)

data class Leg (
    val distance: Distance,
    val duration: Duration,
    val end_address: String,
    val start_address: String,
    val steps: List<Step>
    // ...
)

data class Distance (
    val text: String // Total Distance | e.g: "4.4 miles"
//    val value: Int  >> in meters
)

data class Duration (
    val text: String // Total time | e.g: "11 mins"
//    val value: Int  >> in seconds
)

data class Step (
    val distance: Distance,
    val duration: Duration,
    val end_location: LatLngObject,
    val html_instructions: String,
    val polyline: Polyline,
    val start_location: LatLngObject,
    val travel_mode: String
)

data class Polyline (
    val points: String
)