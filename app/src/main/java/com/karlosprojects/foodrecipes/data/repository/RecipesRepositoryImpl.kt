package com.karlosprojects.foodrecipes.data.repository

import com.karlosprojects.foodrecipes.data.api.SpoonacularApi
import com.karlosprojects.foodrecipes.data.dto.RecipeDto
import com.karlosprojects.foodrecipes.data.local.dao.RecipeDao
import com.karlosprojects.foodrecipes.data.mappers.toDomainRecipe
import com.karlosprojects.foodrecipes.data.mappers.toLocalRecipe
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
            insertRecipesToDatabase(result.results)
            Result.success(result.results.map {
                it.toDomainRecipe()
            })
        } catch (e: Exception) {
            if (recipesDao.getRecipesCount() == 0) {
                Result.failure(e)
            } else {
                val result = recipesDao.getRecipes()
                    .map { it.recipe }
                Result.success(result)
            }
        }
    }

    override suspend fun searchRecipes(searchQuery: Map<String, String>): Result<List<Recipes>> {
        return try {
            val result = api.getRecipes(searchQuery)
            Result.success(result.results.map {
                it.toDomainRecipe()
            })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun insertRecipesToDatabase(results: List<RecipeDto>) {
        results.map { it.toLocalRecipe() }.map { recipe ->
            recipesDao.insertRecipe(recipe)
        }
    }
}