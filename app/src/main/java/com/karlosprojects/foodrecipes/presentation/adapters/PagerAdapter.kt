package com.karlosprojects.foodrecipes.presentation.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.karlosprojects.foodrecipes.presentation.recipes.recipeingredients.IngredientsFragment
import com.karlosprojects.foodrecipes.presentation.recipes.recipeinstructions.InstructionsFragment
import com.karlosprojects.foodrecipes.presentation.recipes.recipeoverview.RecipeOverviewFragment

private const val NUM_PAGES = 3

class PagerAdapter(
    fragment: FragmentActivity,
    private val bundle: Bundle
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RecipeOverviewFragment().apply { arguments = bundle }
            1 -> IngredientsFragment().apply { arguments = bundle }
            2 -> InstructionsFragment()
            else -> RecipeOverviewFragment()
        }
    }
}