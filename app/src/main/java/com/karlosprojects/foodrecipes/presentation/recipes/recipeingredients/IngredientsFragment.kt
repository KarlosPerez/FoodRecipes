package com.karlosprojects.foodrecipes.presentation.recipes.recipeingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.karlosprojects.foodrecipes.core.utils.Constants
import com.karlosprojects.foodrecipes.databinding.FragmentIngredientsBinding
import com.karlosprojects.foodrecipes.domain.model.Recipes

class IngredientsFragment : Fragment() {

    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!

    private val ingredientsAdapter: IngredientsAdapter by lazy { IngredientsAdapter() }

    private val recipe: Recipes?
        get() = arguments?.getParcelable(Constants.RECIPE_ARGUMENT)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
    }

    private fun setupRecycler() {
        binding.ingredientsRecycler.apply {
            adapter = ingredientsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        recipe?.let { ingredientsAdapter.submitList(it.extendedIngredients) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}