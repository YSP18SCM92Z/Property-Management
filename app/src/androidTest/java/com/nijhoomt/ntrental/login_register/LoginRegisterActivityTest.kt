package com.nijhoomt.ntrental.login_register

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.nijhoomt.ntrental.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginRegisterActivityTest {
    /**
     * This is a JUnit Test Rule, we want to launch LoginRegisterActivity before each test method
     * The Rule will make sure to launch the LoginRegisterActivity directly. THis means that when
     * testing an n-layer activity, you don't need to do all the steps to start it. Simply define
     * that in the rule and you are ready to go
     */
    @get:Rule
    var activityRule = ActivityTestRule(LoginRegisterActivity::class.java)

    @Before
    fun setUp() {
    }

    @Test
    fun navigateToLoginActivity() {
        onView(ViewMatchers.withId(R.id.btn_login_register_login))
            .perform(ViewActions.click())
    }

    @After
    fun tearDown() {
    }
}