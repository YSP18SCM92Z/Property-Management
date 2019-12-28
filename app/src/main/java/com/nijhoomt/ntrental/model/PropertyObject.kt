package com.nijhoomt.ntrental.model

import java.io.Serializable

data class PropertyObject(
    val Property: List<Property>
)

data class Property(
    val id: String,
    val propertyaddress: String,
    val propertycity: String,
    val propertystate: String,
    val propertycountry: String,
    val propertystatus: String = "Tenants",
    val propertypurchaseprice: String,
    val propertymortageinfo: String = "No",
    val propertyuserid: String,
    val propertyusertype: String,
    val propertylatitude: String,
    val propertylongitude: String
) : Serializable