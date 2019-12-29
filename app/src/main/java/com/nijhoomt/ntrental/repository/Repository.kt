package com.nijhoomt.ntrental.repository

import android.app.Application
import com.nijhoomt.ntrental.model.ForgotPasswordCred
import com.nijhoomt.ntrental.model.ForgotPasswordObject
import com.nijhoomt.ntrental.model.*
import com.nijhoomt.ntrental.network.PropertyManagementAPI
import retrofit2.Call

/**
 * Created by N & T on 12/23/2019.
 * Under instructions of Varun, Manisha, Ansari, & Rahul
 */
private const val GEOCODING_API_KEY = "AIzaSyD8MNG7RMklDq15lfOYzAI4iz4bKb-_TS4"

class Repository(application: Application) {


// Local/Database Data Sources

//    private var cartDao: CartDAO
//    private var allCarts: LiveData<List<Cart>>
//    private var subTotal: LiveData<Sum>
//
//    init {
//        val database: CartDatabase = CartDatabase.getInstance(
//            application.applicationContext
//        )
//        cartDao = database.cartDao
//        allCarts = cartDao.getAllCarts()
//        subTotal = cartDao.getSubTotal()
//
//    }

// Remote Data Source

    fun createNewUser(registerCredential: RegisterCredential): Call<String> {
        return PropertyManagementAPI.retrofitRegisterService
            .postNewUserAsync(
                email = registerCredential.email,
                landlord_email = registerCredential.landlored_email,
                password = registerCredential.password,
                account_for = registerCredential.account_for
            )
    }

    fun signUserIn(loginCredential: LoginCredential): Call<LoginObject> {
        return PropertyManagementAPI
            .retrofitLoginService
            .postUserAsync(
                password = loginCredential.password,
                email = loginCredential.email
            )
    }

    fun getPropertyList(userId: UserId): Call<PropertyObject>{
        return PropertyManagementAPI
            .retrofitService
            .getPropertyListAsync(
                userid = userId.userid,
                usertype = userId.usertype
            )
    }

    fun getForgottenPassword(forgotPasswordCred: ForgotPasswordCred): Call<ForgotPasswordObject> {
       return PropertyManagementAPI
           .retrofitService
           .getForgottenPasswordAsync(
               email = forgotPasswordCred.useremail
           )
    }

    fun getLatLngObject(formattedAddress: String): Call<GeocodingObject> {
        return PropertyManagementAPI
            .retrofitGeocodingService
            .getLatLngObjectAsync(
                address = formattedAddress,
                key = GEOCODING_API_KEY
            )
    }

    fun addProperty(property: Property): Call<Message> {
        return PropertyManagementAPI
            .retrofitService
            .addPropertyAsync(
                address = property.propertyaddress,
                city = property.propertycity,
                state = property.propertystate,
                country = property.propertycountry,
                pro_status = property.propertystatus,
                purchase_price = property.propertypurchaseprice,
                mortage_info = property.propertymortageinfo,
                userid = property.propertyuserid,
                usertype = property.propertyusertype,
                latitude = property.propertylatitude,
                longitude = property.propertylongitude
            )
    }

    fun removeProperty(propertyId: String): Call<Message> {
        return PropertyManagementAPI
            .retrofitService
            .removePropertyAsync(propertyId)
    }

    fun getListOfTenantsOfChosenLandlord(landlordId: String): Call<TenantObject> {
        return PropertyManagementAPI
            .retrofitService
            .getListOfTenantsOfChosenLandlordAsync(landlordId)
    }


    fun getAllPropertiesForTenants(): Call<PropertyObject> {
        return PropertyManagementAPI
            .retrofitService
            .getAllPropertiesForTenantsAsync()

    fun addTenantsByLandlord(addTenantCred: AddTenantCred): Call<String>{
        return PropertyManagementAPI
            .retrofitAddTenantService
            .addTenantsByLandlordAsync(
                name = addTenantCred.name,
                email = addTenantCred.email,
                address = addTenantCred.address,
                mobile = addTenantCred.mobile,
                propertyid = addTenantCred.propertyid,
                landlordid = addTenantCred.landlordid

            )
    }
}