package com.nijhoomt.ntrental.properties

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.nijhoomt.ntrental.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PropertiesActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(PropertiesActivity::class.java)

    @Before
    fun setUp() {
    }

    @Test
    fun toolbarTitle() {
        onView(
            allOf(
                // The view itself is a TextView
                isAssignableFrom(TextView::class.java),
                // Its parent is the Toolbar
                withParent(isAssignableFrom(Toolbar::class.java))
            )
        ).check(matches(withText("Your Properties")))
    }

    @Test
    fun toolbarTitleWithCustomMatcher() {
        val title = InstrumentationRegistry.getInstrumentation()
            .targetContext.getString(R.string.property_title_toolbar)

        onView(isAssignableFrom(Toolbar::class.java))
            .check(matches(withToolbarTitle(title)))
    }

    private fun withToolbarTitle(expectedTitle: CharSequence): Matcher<View> {
        return object : BoundedMatcher<View, Toolbar>(Toolbar::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("with toolbar title: $expectedTitle")
            }

            override fun matchesSafely(toolbar: Toolbar?): Boolean {
                return expectedTitle == toolbar?.title
            }
        }
    }

    @After
    fun tearDown() {
    }
}