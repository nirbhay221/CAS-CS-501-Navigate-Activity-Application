package com.example.navigateactivitiesapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.navigateactivitiesapp", appContext.packageName)
    }

    @Test
    fun flingToNavigateActivityUp() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.imageView3)).perform(ViewActions.swipeUp())
        Thread.sleep(1000)
        activityScenario.close()
    }

    @Test
    fun flingToNavigateActivityDown() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.imageView3)).perform(ViewActions.swipeDown())
        Thread.sleep(1000)
        activityScenario.close()
    }

    @Test
    fun flingToNavigateActivityRight() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.imageView3)).perform(ViewActions.swipeRight())
        Thread.sleep(1000)
        activityScenario.close()
    }

    @Test
    fun flingToNavigateActivityLeft() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.imageView3)).perform(ViewActions.swipeLeft())
        Thread.sleep(1000)
        activityScenario.close()
    }
}