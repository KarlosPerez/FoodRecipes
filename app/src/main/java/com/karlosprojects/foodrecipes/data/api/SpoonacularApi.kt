package com.karlosprojects.foodrecipes.data.api

import com.karlosprojects.foodrecipes.data.dto.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SpoonacularApi {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries: Map<String, String>
    ): RecipeResponse
}