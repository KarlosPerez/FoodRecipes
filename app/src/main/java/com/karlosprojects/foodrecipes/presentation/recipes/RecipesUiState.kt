package com.karlosprojects.foodrecipes.presentation.recipes

import com.karlosprojects.foodrecipes.domain.model.Recipes

data class RecipesUiState(
    val isLoading: Boolean = false,
    val recipes: List<Recipes> = listOf()
)
