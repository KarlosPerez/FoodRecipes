package com.karlosprojects.foodrecipes.presentation.recipes

import androidx.test.espresso.accessibility.AccessibilityChecks
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.karlosprojects.foodrecipes.R
import com.karlosprojects.foodrecipes.base.BaseUITest
import com.karlosprojects.foodrecipes.idleresources.waitUntilViewIsNotDisplayed
import com.karlosprojects.foodrecipes.network.FILE_SUCCESS_RECIPES_RESPONSE
import com.karlosprojects.foodrecipes.network.mockResponse
import com.karlosprojects.foodrecipes.presentation.MainActivity
import com.karlosprojects.foodrecipes.utils.checkViewInRecyclerWithIdAndTextIsDisplayed
import com.karlosprojects.foodrecipes.utils.checkViewIsDisplayedByText
import com.karlosprojects.foodrecipes.utils.performClickByViewId
import com.karlosprojects.foodrecipes.utils.performClickInViewPositionRecyclerView
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.QueueDispatcher
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4ClassRunner::class)
@HiltAndroidTest
internal class RecipesFragmentTest : BaseUITest(dispatcher = QueueDispatcher()) {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    private val successRecipesResponse: MockResponse
        get() = mockResponse(FILE_SUCCESS_RECIPES_RESPONSE, HttpURLConnection.HTTP_OK)

    @Test
    @SmallTest
    fun when_screen_is_created_should_show_bottom_navigation() {
        enqueueResponses(successRecipesResponse)
        waitUntilViewIsNotDisplayed(withId(R.id.recipesLoading))
        performClickByViewId(R.id.recipesFab)
        checkViewIsDisplayedByText(viewText = "Main Course")
    }

    @Test
    @SmallTest
    fun when_recipes_is_displayed_should_show_info() {
        enqueueResponses(successRecipesResponse)
        waitUntilViewIsNotDisplayed(withId(R.id.recipesLoading))
        checkViewInRecyclerWithIdAndTextIsDisplayed(
            viewId = R.id.recipesRecycler,
            viewText = "Summer Berry Salad",
            position = 1
        )
        checkViewInRecyclerWithIdAndTextIsDisplayed(
            viewId = R.id.recipesRecycler,
            viewText = "45",
            position = 2
        )
        checkViewInRecyclerWithIdAndTextIsDisplayed(
            viewId = R.id.recipesRecycler,
            viewText = "5518",
            position = 2
        )
        performClickInViewPositionRecyclerView(
            viewId = R.id.recipesRecycler,
            childViewId = R.id.itemRecipeImgDetails,
            position = 1
        )
    }

    @Test
    @SmallTest
    fun when_recipe_is_selected_should_show_details() {
        enqueueResponses(successRecipesResponse)
        waitUntilViewIsNotDisplayed(withId(R.id.recipesLoading))
        checkViewInRecyclerWithIdAndTextIsDisplayed(
            viewId = R.id.recipesRecycler,
            viewText = "Summer Berry Salad",
            position = 1
        )
        performClickInViewPositionRecyclerView(
            viewId = R.id.recipesRecycler,
            childViewId = R.id.itemRecipeImgDetails,
            position = 1
        )
    }

    companion object {

        @BeforeClass
        @JvmStatic
        fun enableAccessibilityChecks() {
            AccessibilityChecks.enable().apply {
                setRunChecksFromRootView(true)
            }
        }
    }

}