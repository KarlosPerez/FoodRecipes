package com.karlosprojects.foodrecipes.presentation.recipes.recipedetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.karlosprojects.foodrecipes.R
import com.karlosprojects.foodrecipes.core.utils.Constants.RECIPE_ARGUMENT
import com.karlosprojects.foodrecipes.databinding.ActivityRecipeDetailBinding
import com.karlosprojects.foodrecipes.presentation.adapters.PagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetail : AppCompatActivity() {

    var titles = intArrayOf(
        R.string.recipes_details_overview_title,
        R.string.recipes_details_ingredients_title,
        R.string.recipes_details_instructions_title
    )

    private lateinit var binding: ActivityRecipeDetailBinding
    private val args: RecipeDetailArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_detail)

        setToolbar()
        setupPager()
    }

    private fun setupPager() {
        val bundle = Bundle().apply { putParcelable(RECIPE_ARGUMENT, args.recipe) }
        val adapter = PagerAdapter(this, bundle)
        with(binding) {
            viewPager.adapter = adapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = getString(titles[position])
            }.attach()
        }

    }

    private fun setToolbar() {
        binding.toolbar.apply {
            setSupportActionBar(this)
            setTitleTextColor(ContextCompat.getColor(this@RecipeDetail, R.color.white))
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            setNavigationOnClickListener { finish() }
        }
    }

}