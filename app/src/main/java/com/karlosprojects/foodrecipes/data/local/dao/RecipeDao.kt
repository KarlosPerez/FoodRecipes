package com.karlosprojects.foodrecipes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karlosprojects.foodrecipes.data.local.entities.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipeEntity: RecipeEntity)

    @Query("SELECT * FROM recipes ORDER BY id ASC")
    fun getRecipes(): Flow<List<RecipeEntity>>
}