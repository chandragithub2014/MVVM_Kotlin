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
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MVVMLoginTest {

   /* @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LauncherActivity::class.java)*/
   @Rule @JvmField
   public val mActivityRule: ActivityTestRule<LauncherActivity> = ActivityTestRule(LauncherActivity::class.java)

    @Test
    fun mVVMLoginTest() {
        val recyclerView = onView(
            allOf(
                withId(R.id.launcher_list),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

   /*     val editText = onView(
            allOf(
                withId(R.id.userName_editText), withText("User Name"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container_fragment),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        editText.check(matches(withText("User Name")))

        val editText2 = onView(
            allOf(
                withId(R.id.userName_editText), withText("User Name"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container_fragment),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        editText2.check(matches(withText("User Name")))

        val editText3 = onView(
            allOf(
                withId(R.id.password_editText), withText("Password"),
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
        editText3.check(matches(withText("Password")))

        val editText4 = onView(
            allOf(
                withId(R.id.password_editText), withText("Password"),
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
        editText4.check(matches(withText("Password")))
*/
        val appCompatEditText = onView(
            allOf(
                withId(R.id.userName_editText),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container_fragment),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("admin"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.password_editText),
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
        appCompatEditText2.perform(replaceText("admin"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.login_btn), withText("Login"),
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
        appCompatButton.perform(click())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.userName_editText), withText("admin"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container_fragment),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("admi"))

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.userName_editText), withText("admi"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container_fragment),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(closeSoftKeyboard())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.password_editText), withText("admin"),
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
        appCompatEditText5.perform(replaceText("admi"))

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.password_editText), withText("admi"),
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
        appCompatEditText6.perform(closeSoftKeyboard())

        val appCompatEditText7 = onView(
            allOf(
                withId(R.id.password_editText), withText("admi"),
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
        appCompatEditText7.perform(pressImeActionButton())

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.login_btn), withText("Login"),
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
        appCompatButton2.perform(click())
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
