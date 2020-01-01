package com.nijhoomt.ntrental.di.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class ViewModelProvidersFactory @Inject constructor(
    private val creators: MutableMap<Class<out ViewModel?>?, Provider<ViewModel?>?>?
) : ViewModelProvider.Factory {

    private val TAG = javaClass.simpleName

    /**
     * Creates a new instance of the given `Class`.
     *
     * @param modelClass a `Class` whose instance is requested
     * @param <T>        The type parameter for the ViewModel.
     * @return a newly created ViewModel
    </T> */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator = creators?.get(modelClass)
        // If the ViewModel has not been created
        if (creator == null) {

            // Loop through the allowable keys (aka allowed classes with the @ViewModelKey)
            for (entry in creators?.entries!!) {

                // If it's allowed, set the Provider<ViewModel>
                if (modelClass.isAssignableFrom(entry.key!!)) {
                    creator = entry.value
                    break
                }
            }
        }

        // If this is not one of the allowed keys, throw exception
        if (creator == null) {
            throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
        }

        // Return the provider
        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}