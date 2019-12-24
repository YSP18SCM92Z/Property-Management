package com.nijhoomt.ntrental.repository

import android.app.Application
import com.nijhoomt.ntrental.model.RegisterCredential
import com.nijhoomt.ntrental.network.PropertyManagementAPI
import retrofit2.Call

/**
 * Created by N & T on 12/23/2019.
 * Under instructions of Varun, Manisha, Ansari, & Rahul
 */
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

//    fun getSpecifiedProductsBasedOnSubCategoryId(subcatId: Int): Deferred<ProductObject> {
//        return GroceryApi.retrofitService.getSpecifiedProductsBasedOnSubCategoryIdAsync(subcatId)
//    }
//
//    fun getCategories() : Deferred<GroceryCategoryObject>{
//        return GroceryApi.retrofitService.getCategoriesAsync()
//    }
//
//    fun getSpecifiedSubCategoriesBasedOnCategoryId(catId: Int) : Deferred<GrocerySubCategoryObject> {
//        return GroceryApi.retrofitService.getSpecifiedSubCategoriesBasedOnCategoryIdAsync(catId)
//    }
}