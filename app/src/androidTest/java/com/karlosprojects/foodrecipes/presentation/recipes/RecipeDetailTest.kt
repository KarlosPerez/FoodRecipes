package com.karlosprojects.foodrecipes.presentation.recipes

import android.os.SystemClock
import androidx.core.os.bundleOf
import androidx.test.espresso.accessibility.AccessibilityChecks
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckResultUtils.matchesViews
import com.karlosprojects.foodrecipes.R
import com.karlosprojects.foodrecipes.base.BaseUITest
import com.karlosprojects.foodrecipes.domain.model.Recipes
import com.karlosprojects.foodrecipes.presentation.recipes.recipeoverview.RecipeOverviewFragment
import com.karlosprojects.foodrecipes.utils.checkViewIsDisplayedByText
import com.karlosprojects.foodrecipes.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.QueueDispatcher
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@HiltAndroidTest
internal class RecipeDetailTest : BaseUITest(dispatcher = QueueDispatcher()) {

    private val navArguments = Recipes(
        aggregateLikes = 15,
        cheap = false,
        dairyFree = false,
        extendedIngredients = listOf(
            Recipes.Ingredients(
                amount = 1.0,
                consistency = "SOLID",
                image = "apple.jpg",
                name = "apples",
                original = "apples",
                unit = "serving"
            )
        ),
        glutenFree = true,
        id = 782600,
        image = "https://spoonacular.com/recipeImages/782600-312x231.jpg",
        readyInMinutes = 45,
        sourceName = "Food and Spice",
        sourceUrl = "http://foodandspice.blogspot.com/2016/02/quinoa-salad-with-vegetables-and-cashews.html",
        summary = "You can never have too many side dish recipes, so give Quinoa Salad with Vegetables and Cashews a try. This recipe makes 6 servings with <b>487 calories</b>, <b>9g of protein</b>, and <b>39g of fat</b> each. For <b>\$1.58 per serving</b>, this recipe <b>covers 24%</b> of your daily requirements of vitamins and minerals. This recipe from foodandspice.blogspot.com has 88 fans. If you have quinoa, sunflower seeds, sesame oil, and a few other ingredients on hand, you can make it. From preparation to the plate, this recipe takes approximately <b>45 minutes</b>. It is a good option if you're following a <b>gluten free and vegan</b> diet. All things considered, we decided this recipe <b>deserves a spoonacular score of 98%</b>. This score is tremendous. Try <a href=\\\"https://spoonacular.com/recipes/cashews-and-vegetables-114102\\\">Cashews and Vegetables</a>, <a href=\\\"https://spoonacular.com/recipes/lemony-quinoa-salad-with-shaved-vegetables-27876\\\">Lemony Quinoa Salad with Shaved Vegetables</a>, and <a href=\\\"https://spoonacular.com/recipes/grill-roasted-vegetables-and-quinoa-salad-757439\\\">Grill-Roasted Vegetables and Quinoa Salad</a> for similar recipes.\",\n",
        title = "Quinoa Salad with Vegetables and Cashews",
        vegan = true,
        vegetarian = true,
        veryHealthy = true,
    )

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private fun launchFragment() {
        launchFragmentInHiltContainer<RecipeOverviewFragment>(
            fragmentArgs = bundleOf(
                "recipe" to navArguments
            )
        )
    }

    @Before
    fun setUp() {
        launchFragment()
        hiltRule.inject()
    }

    @Test
    @SmallTest
    fun when_screen_is_created_should_show_title() {
        SystemClock.sleep(5000)
        checkViewIsDisplayedByText("Vegetarian")
    }

    companion object {

        @BeforeClass
        @JvmStatic
        fun enableAccessibilityChecks() {
            AccessibilityChecks.enable().apply {
                setRunChecksFromRootView(true)
                setThrowExceptionForErrors(false)
                setSuppressingResultMatcher(matchesViews(withId(R.id.vegetarian_textView)))

            }
        }
    }

}