package com.karlosprojects.foodrecipes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.karlosprojects.foodrecipes.data.local.converters.RecipeTypeConverter
import com.karlosprojects.foodrecipes.data.local.dao.RecipeDao
import com.karlosprojects.foodrecipes.data.local.entities.RecipeEntity

@Database(
    version = 1,
    entities = [RecipeEntity::class],
    exportSchema = false
)
@TypeConverters(RecipeTypeConverter::class)
abstract class RecipesDatabase: RoomDatabase() {

    abstract fun recipesDao(): RecipeDao
}