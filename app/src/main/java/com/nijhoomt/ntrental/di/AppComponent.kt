package com.nijhoomt.ntrental.di

import android.app.Application
import com.nijhoomt.ntrental.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 * "@Component"
 * Annotates an interface or abstract class for which a fully-formed, dependency-injected
 * implementation is to be generated from a set of {@linkplain #modules}. The generated class will
 * have the name of the type annotated with {@code @Component} prepended with {@code Dagger}. For
 * example, {@code @Component interface MyComponent {...}} will produce an implementation named
 * {@code DaggerMyComponent}.
 * ...
 *
 * "modules"
 * A list of classes annotated with {@link Module} whose bindings are used to generate the
 * component implementation. Note that through the use of {@link Module#includes} the full set of
 * modules used to implement the component may include more modules that just those listed here.
 *
 * "AndroidSupportInjectionModule"
 * This module no longer provides any value beyond what is provided in {@link AndroidInjectionModule}
 * and is just an alias. It will be removed in a future release.
 */

/**
 * Performs members-injection for a concrete subtype of a <a
 * href="https://developer.android.com/guide/components/">core Android type</a> (e.g., {@link
 * android.app.Activity} or {@link android.app.Fragment}).
 *
 * <p>Commonly implemented by {@link dagger.Subcomponent}-annotated types whose {@link
 * dagger.Subcomponent.Factory} extends {@link Factory}.
 *
 * @param <T> a concrete subtype of a core Android type
 * @see AndroidInjection
 * @see DispatchingAndroidInjector
 * @see ContributesAndroidInjector
 */

@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        AppModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        fun application(@BindsInstance application: Application): Builder
        fun build(): AppComponent
    }
}