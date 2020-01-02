package com.nijhoomt.ntrental.login

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.nijhoomt.ntrental.R
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(LoginActivity::class.java)

    @Before
    fun setUp() {
    }

    @Test
    fun testInitiateLogin() {

        // Expect edit_text_email_login is empty then type ...
        onView(ViewMatchers.withId(R.id.edit_text_email_login))
            .check(matches(withText("")))
            .perform(ViewActions.typeText("landlord@gmail.com"))

        // Expect edit_text_password_login is empty then type ...
        onView(ViewMatchers.withId(R.id.edit_text_password_login))
            .check(matches(withText("")))
            .perform(ViewActions.typeText("123456"))
            .perform(ViewActions.closeSoftKeyboard())

        Thread.sleep(1000)

        // Perform a click to login
        onView(ViewMatchers.withId(R.id.btn_login_login))
            .perform(ViewActions.click())

        Thread.sleep(1000)

        val title = InstrumentationRegistry.getInstrumentation()
            .targetContext.getString(R.string.title_dashboard)

        onView(
            CoreMatchers.allOf(
                // The view itself is a TextView
                ViewMatchers.isAssignableFrom(TextView::class.java),
                // Its parent is the Toolbar
                ViewMatchers.withParent(ViewMatchers.isAssignableFrom(Toolbar::class.java))
            )
        ).check(matches(withText(title)))
    }

    @After
    fun tearDown() {
    }
}