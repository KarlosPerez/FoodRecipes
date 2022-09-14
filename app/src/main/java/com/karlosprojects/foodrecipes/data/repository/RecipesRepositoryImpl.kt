package com.karlosprojects.foodrecipes.data.repository

import com.karlosprojects.foodrecipes.data.api.SpoonacularApi
import com.karlosprojects.foodrecipes.data.local.dao.RecipeDao
import com.karlosprojects.foodrecipes.data.mappers.toDomainRecipe
import com.karlosprojects.foodrecipes.domain.model.Recipes
import com.karlosprojects.foodrecipes.domain.repository.RecipesRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class RecipesRepositoryImpl @Inject constructor(
    private val api: SpoonacularApi,
    private val recipesDao: RecipeDao
) : RecipesRepository {

    override suspend fun getRecipes(queries: Map<String, String>): Result<List<Recipes>> {
        return try {
            val result = api.getRecipes(queries)
            Result.success(result.results.map {
                it.toDomainRecipe()
            })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}