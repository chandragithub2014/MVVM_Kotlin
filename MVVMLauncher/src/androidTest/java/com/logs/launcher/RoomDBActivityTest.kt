package com.logs.launcher


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class RoomDBActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LauncherActivity::class.java)

    @Test
    fun roomDBActivityTest() {
        val recyclerView = onView(
            allOf(
                withId(R.id.launcher_list),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

 /*       val frameLayout = onView(
            allOf(IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java), isDisplayed())
        )
        frameLayout.check(matches(isDisplayed()))

        val frameLayout2 = onView(
            allOf(
                withId(R.id.container_fragment),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        frameLayout2.check(matches(isDisplayed()))
*/
        val actionMenuItemView = onView(
            allOf(
                withId(R.id.action_settings), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.toolBar),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        actionMenuItemView.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.ed_name),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container_fragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("zz1"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.ed_password),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container_fragment),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("zzw"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.ed_age),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container_fragment),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("32"), closeSoftKeyboard())

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.ed_age), withText("32"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container_fragment),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(pressImeActionButton())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.phone),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container_fragment),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatEditText5.perform(replaceText("98856505422"), closeSoftKeyboard())

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.ed_city),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container_fragment),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatEditText6.perform(replaceText("hyd"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.save), withText("SaveUser"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container_fragment),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

      /*  val appCompatTextView = onView(
            allOf(
                withId(R.id.ok_tv), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.design_bottom_sheet),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatTextView.perform(click())*/
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
