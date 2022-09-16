package com.karlosprojects.foodrecipes.domain.usecases

import com.karlosprojects.foodrecipes.core.utils.Constants
import com.karlosprojects.foodrecipes.core.utils.Constants.DEFAULT_RECIPES_NUMBER
import com.karlosprojects.foodrecipes.domain.model.Recipes
import com.karlosprojects.foodrecipes.domain.repository.RecipesRepository

class SearchRecipes(
    private val repository: RecipesRepository
) {

    suspend operator fun invoke(searchQuery: String): Result<List<Recipes>> {
        return repository.getRecipes(applyQueries(searchQuery))
    }

    private fun applyQueries(searchQuery: String): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[Constants.QUERY_SEARCH] = searchQuery
        queries[Constants.QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[Constants.QUERY_API_KEY] = Constants.API_KEY
        queries[Constants.QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[Constants.QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }
}