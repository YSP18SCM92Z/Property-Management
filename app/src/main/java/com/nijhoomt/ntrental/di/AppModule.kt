package com.nijhoomt.ntrental.di

import android.app.Application
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nijhoomt.ntrental.R
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Anything that can exist but will not change over the course of application's life time
 * Retrofit Instance
 * Glide Instance
 * ...
 */
@Module
abstract class AppModule {

    @Module
    companion object AppModule {

        @JvmStatic
        @Singleton
        @Provides
        fun provideRetrofitInstance() = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @JvmStatic
        @Singleton
        @Provides
        fun provideRequestOptions() = RequestOptions
            .placeholderOf(R.drawable.logo)
            .error(R.drawable.logo)

        @JvmStatic
        @Singleton
        @Provides
        fun provideGlideInstance(application: Application, requestOptions: RequestOptions) =
            Glide.with(application)
                .setDefaultRequestOptions(requestOptions)

        @JvmStatic
        @Singleton
        @Provides
        fun provideAppLogoDrawable(application: Application) =
            ContextCompat.getDrawable(application, R.drawable.logo)!!
    }
}