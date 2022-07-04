package com.example.test

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.test.ui.activity.MainActivity
import org.hamcrest.Matcher
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class PicturesListFragmentTest {

    /* Helping Func for RecyclerView Click */
    private fun clickOnViewChild(viewId: Int) = object : ViewAction {

        override fun getConstraints() = null

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: UiController, view: View) = ViewActions.click()
            .perform(uiController, view.findViewById(viewId))
    }
    /* Check RecyclerView On Item Click Listener Open the Detail Fragment */
    @Test
    fun navDetailScreen_fromMainActivityTest(){

        val firstActivity: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)
        firstActivity.launchActivity(Intent())

        onView(isRoot()).perform(waitFor(10000))

        onView(withId(R.id.rvPictures)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            clickOnViewChild(R.id.mainCard)))

        onView(withId(R.id.swipeView)).check(
            matches(
                withEffectiveVisibility(
                    Visibility.VISIBLE
                )
            )
        )

    }
    /* Check RecyclerView is Attached to the View */
    @Test
    fun checkRecyclerView_isAttached(){

        val firstActivity: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)
        firstActivity.launchActivity(Intent())

        onView(isRoot()).perform(waitFor(10000))

        onView(withId(R.id.rvPictures))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    /* Check on Back Pressed Pop the Fragment and Resume Main Activity */
    @Test
    fun navMainScreen_fromDetailScreenTest(){

        val firstActivity: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)
        firstActivity.launchActivity(Intent())


        onView(isRoot()).perform(waitFor(10000))

        onView(withId(R.id.rvPictures)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            clickOnViewChild(R.id.mainCard)))



        pressBack()

        onView(withId(R.id.swipeView)).check(
            matches(
                withEffectiveVisibility(
                    Visibility.VISIBLE
                )
            )
        )

    }
    /* Helping Func to Add Delay for Api Response*/
    private fun waitFor(delay: Long): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $delay milliseconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }

}