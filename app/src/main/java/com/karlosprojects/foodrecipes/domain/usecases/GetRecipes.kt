package com.karlosprojects.foodrecipes.domain.usecases

import com.karlosprojects.foodrecipes.core.utils.Constants.API_KEY
import com.karlosprojects.foodrecipes.core.utils.Constants.QUERY_ADD_RECIPE_INFORMATION
import com.karlosprojects.foodrecipes.core.utils.Constants.QUERY_API_KEY
import com.karlosprojects.foodrecipes.core.utils.Constants.QUERY_DIET
import com.karlosprojects.foodrecipes.core.utils.Constants.QUERY_FILL_INGREDIENTS
import com.karlosprojects.foodrecipes.core.utils.Constants.QUERY_NUMBER
import com.karlosprojects.foodrecipes.core.utils.Constants.QUERY_TYPE
import com.karlosprojects.foodrecipes.data.local.datastore.DataStoreParameters
import com.karlosprojects.foodrecipes.domain.model.Recipes
import com.karlosprojects.foodrecipes.domain.repository.RecipesRepository

class GetRecipes(
    private val repository: RecipesRepository
) {

    suspend operator fun invoke(preferences: DataStoreParameters): Result<List<Recipes>> {
        return repository.getRecipes(applyQueries(preferences))
    }

    private fun applyQueries(preferences: DataStoreParameters): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_NUMBER] = "50"
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = preferences.mealType
        queries[QUERY_DIET] = preferences.dietType
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }
}