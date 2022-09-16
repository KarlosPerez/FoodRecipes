package com.karlosprojects.foodrecipes.data.api

import com.karlosprojects.foodrecipes.data.dto.RecipeResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val recipesApi: SpoonacularApi
) {

    suspend fun getRecipes(queries: Map<String, String>): RecipeResponse =
        recipesApi.getRecipes(queries)
}
