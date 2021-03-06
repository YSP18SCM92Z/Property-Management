package com.nijhoomt.ntrental.properties

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.model.Property
import com.nijhoomt.ntrental.model.PropertyObject
import com.nijhoomt.ntrental.model.UserId
import com.nijhoomt.ntrental.more.MoreActivity
import com.nijhoomt.ntrental.properties.add.AddPropertyActivity
import com.nijhoomt.ntrental.properties.detail.PropertyDetailActivity
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_properties.*
import java.util.concurrent.TimeUnit

class PropertiesActivity : AppCompatActivity() {

    val disposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_properties)

        setUpToolbar()

        // Show the list of properties the landlord currently have
        val myPref = getSharedPreferences("UserCred", Context.MODE_PRIVATE)
        val userId = myPref.getString("userId", "").toString()
        val userType = myPref.getString("userType", "").toString()

        //Initialize userID Model
        val userCred = UserId(userId, userType)

        val propertyViewModel =
            initializePropertyViewModel(userCred)

        // ListAdapter is completely different from ListView (We normally compare ListView vs. RecyclerView)
        // We compare the performance of ListAdapter with the normal RecyclerView Adapter
        // ListAdapter has DiffUtil built-in >> Give us a better performance whenever we wanna show
        // a list of item.
        val propertiesListAdapter = PropertiesListAdapter(application)
        recyclerview_properties.adapter = propertiesListAdapter

        propertyViewModel.propertyList.observe(this, Observer {
            propertiesListAdapter.submitList(it.sortedByDescending { it.id })
        })

        propertyViewModel.setUserId(userCred)

        propertiesListAdapter.setOnItemClickListener(object: PropertiesListAdapter.OnItemClickListener{
            override fun onItemClick(property: Property) {

                Observable.just(property).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .switchMap(object: Function<Property, ObservableSource<Property>>{
                        override fun apply(t: Property): ObservableSource<Property> {
                            return Observable.just(t).delay(1, TimeUnit.MILLISECONDS)
                        }

                    }).subscribe(object: io.reactivex.Observer<Property>{
                        override fun onComplete() {
                            Log.d("nijhoom", "Completed")
                        }

                        override fun onSubscribe(d: Disposable) {
                            Log.d("nijhoom", "OnSubscribe")
                        }

                        override fun onNext(t: Property) {
                            val intent = Intent(applicationContext, PropertyDetailActivity::class.java)
                            intent.putExtra("SELECTED_PROPERTY", property)
                            startActivity(intent)
                        }

                        override fun onError(e: Throwable) {

                        }

                    })
                /*val intent = Intent(applicationContext, PropertyDetailActivity::class.java)
                intent.putExtra("SELECTED_PROPERTY", property)
                startActivity(intent)*/
            }
        })

        fab_properties.setOnClickListener {
            startActivity(Intent(this, AddPropertyActivity::class.java))
        }
    }

    private fun initializePropertyViewModel(userCred: UserId): PropertyViewModel {
        val propertyViewModelFactory = PropertyViewModelFactory(
            userId = userCred,
            application = application
        )

        return ViewModelProviders
            .of(this, propertyViewModelFactory)
            .get(PropertyViewModel::class.java)
    }

    private fun setUpToolbar() {
        val customToolbar = properties_custom_toolbar as Toolbar
        customToolbar.title = "Your Properties"
        setSupportActionBar(customToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.more_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.btn_more -> {
                startActivity(Intent(this, MoreActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
