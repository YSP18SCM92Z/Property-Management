package com.nijhoomt.ntrental.model

import com.google.gson.annotations.SerializedName

/**
 * Created by N & T on 12/28/2019.
 * Under instructions of Varun, Manisha, Ansari, & Rahul
 */
data class TenantObject(
    @SerializedName("Tenants") val listOfTenantsOfChosenLandlord: List<Tenant>
)

data class Tenant(
    val id: String,
    @SerializedName("tenantname") val tenantName: String,
    @SerializedName("tenantemail") val tenantEmail: String,
    @SerializedName("tenantaddress") val tenantAddress: String,
    @SerializedName("tenantmobile") val tenantMobile: String,
    @SerializedName("propertyid") val propertyId: String,
    @SerializedName("landlordid") val landlordId: String
)
