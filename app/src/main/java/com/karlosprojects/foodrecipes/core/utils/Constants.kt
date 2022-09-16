package com.karlosprojects.foodrecipes.core.utils

object Constants {

    const val BASE_URL = "https://api.spoonacular.com"
    const val API_KEY = "8d4f6726680948c7bbfe7393514653d6"

    // API Query Keys
    const val QUERY_SEARCH = "search"
    const val QUERY_NUMBER = "number"
    const val QUERY_API_KEY = "apiKey"
    const val QUERY_TYPE = "type"
    const val QUERY_DIET = "diet"
    const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
    const val QUERY_FILL_INGREDIENTS = "fillIngredients"

    // Room database
    const val DATABASE_NAME = "recipesDB"
    const val RECIPES_TABLE = "recipes"

    // Bottom Sheet and Preferences
    const val DEFAULT_RECIPES_NUMBER = "50"
    const val DEFAULT_MEAL_TYPE = "main course"
    const val DEFAULT_DIET_TYPE = "gluten free"
}