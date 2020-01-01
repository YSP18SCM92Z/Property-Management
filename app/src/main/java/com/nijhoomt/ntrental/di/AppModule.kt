package com.nijhoomt.ntrental.di

import android.app.Application
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nijhoomt.ntrental.R
import dagger.Module
import dagger.Provides

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
        @Provides
        fun provideRequestOptions() = RequestOptions
            .placeholderOf(R.drawable.logo)
            .error(R.drawable.logo)

        @JvmStatic
        @Provides
        fun provideGlideInstance(application: Application, requestOptions: RequestOptions) =
            Glide.with(application)
                .setDefaultRequestOptions(requestOptions)

        @JvmStatic
        @Provides
        fun provideAppLogoDrawable(application: Application) =
            ContextCompat.getDrawable(application, R.drawable.logo)!!
    }
}