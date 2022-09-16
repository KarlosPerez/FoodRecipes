package com.karlosprojects.foodrecipes.domain.repository

import com.karlosprojects.foodrecipes.domain.model.Recipes

interface RecipesRepository {

    suspend fun getRecipes(queries: Map<String, String>): Result<List<Recipes>>

    suspend fun searchRecipes(searchQuery: Map<String, String>): Result<List<Recipes>>
}