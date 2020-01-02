package com.nijhoomt.ntrental

import com.nijhoomt.ntrental.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

//        1st way
//        return DaggerAppComponent.builder().application(this).build()

//        2nd way
        return DaggerAppComponent.factory().create(this)
    }
}