package com.karlosprojects.foodrecipes.presentation.recipes.recipelist

import com.karlosprojects.foodrecipes.domain.model.Recipes

data class RecipesUiState(
    val isLoading: Boolean = false,
    val recipes: List<Recipes> = listOf()
)
