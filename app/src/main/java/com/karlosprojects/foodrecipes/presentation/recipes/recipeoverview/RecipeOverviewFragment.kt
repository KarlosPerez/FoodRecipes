package com.karlosprojects.foodrecipes.presentation.recipes.recipeoverview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.load
import com.google.android.material.textview.MaterialTextView
import com.karlosprojects.foodrecipes.R
import com.karlosprojects.foodrecipes.core.utils.Constants.RECIPE_ARGUMENT
import com.karlosprojects.foodrecipes.databinding.FragmentRecipeOverviewBinding
import com.karlosprojects.foodrecipes.domain.model.Recipes
import com.karlosprojects.foodrecipes.presentation.extensions.getHtmlString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeOverviewFragment : Fragment() {

    private var _binding: FragmentRecipeOverviewBinding? = null
    private val binding get() = _binding!!

    private val recipe: Recipes?
        get() = arguments?.getParcelable(RECIPE_ARGUMENT)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeOverviewBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
    }

    private fun setViews() {
        with(binding) {
            recipe?.let {
                overviewImg.load(it.image)
                titleTextView.text = it.title
                likesTextView.text = it.aggregateLikes.toString()
                timeTextView.text = it.readyInMinutes.toString()
                summaryTextView.text = requireContext().getHtmlString(it.summary)

                it.vegetarian.setTypeColor(vegetarianImageView, vegetarianTextView)
                it.vegan.setTypeColor(veganImageView, veganTextView)
                it.glutenFree.setTypeColor(glutenFreeImageView, glutenFreeTextView)
                it.dairyFree.setTypeColor(dairyFreeImageView, dairyFreeTextView)
                it.veryHealthy.setTypeColor(healthyImageView, healthyTextView)
                it.cheap.setTypeColor(cheapImageView, cheapTextView)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun Boolean.setTypeColor(
        imageView: AppCompatImageView,
        textView: MaterialTextView
    ) {
        if (this) {
            imageView.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            textView.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }
    }

}
