package com.nijhoomt.ntrental.di

import com.nijhoomt.ntrental.forgotpassword.ForgotPasswordActivity
import com.nijhoomt.ntrental.login.LoginActivity
import com.nijhoomt.ntrental.login_register.LoginRegisterActivity
import com.nijhoomt.ntrental.register.RegisterActivity
import com.nijhoomt.ntrental.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Annotates a class that contributes to the object graph.
 * We you potentially put all the activities in
 */
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeForgotPasswordActivity(): ForgotPasswordActivity

    @ContributesAndroidInjector
    abstract fun contributeRegisterActivity(): RegisterActivity

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun contributeLoginRegisterActivity(): LoginRegisterActivity

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity
}